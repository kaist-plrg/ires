package kr.ac.kaist.ires

import java.io._
import kr.ac.kaist.ires.error._
import kr.ac.kaist.ires.phase._
import kr.ac.kaist.ires.util.Useful._
import org.scalatest._
import scala.Console.{ CYAN, GREEN, YELLOW, RED, RESET }
import scala.io.Source
import scala.util.{ Try, Success, Failure }
import spray.json._

abstract class IRESTest extends FunSuite with BeforeAndAfterAll {
  // results
  trait Result
  case object Pass extends Result
  case class Yet(msg: String) extends Result
  case object Fail extends Result
  protected var resMap: Map[String, Result] = Map()
  implicit object ResultFormat extends RootJsonFormat[Result] {
    override def read(json: JsValue): Result = json match {
      case JsString(text) => Yet(text)
      case JsBoolean(bool) => if (bool) Pass else Fail
      case v => deserializationError(s"unknown Result: $v")
    }

    override def write(result: Result): JsValue = result match {
      case Pass => JsTrue
      case Fail => JsFalse
      case Yet(msg) => JsString(msg)
    }
  }

  // count tests
  protected var count: Int = 0

  // check result
  def check[T](name: String, tester: => T): Unit = {
    count += 1
    test(s"[$tag] $name") {
      try {
        tester
        resMap += name -> Pass
      } catch {
        case e @ NotSupported(msg) =>
          resMap += name -> Yet(msg)
        case e: Throwable =>
          resMap += name -> Fail
          throw e
      }
    }
  }

  // get score
  def getScore(res: Map[String, Result]): (Int, Int) = (
    res.count { case (k, r) => r == Pass },
    res.size
  )

  // tag name
  val category: String
  lazy val tag: String = s"$category.$this"

  // check backward-compatibility after all tests
  override def afterAll(): Unit = {
    import DefaultJsonProtocol._

    // check backward-compatibility
    var breakCount = 0
    def error(msg: String): Unit = {
      breakCount += 1
      scala.Console.err.println(s"[Backward-Compatibility] $msg")
    }

    // show abstract result
    val filename = s"$TEST_DIR/result/$category/$this.json"
    for {
      str <- optional(readFile(filename))
      json = str.parseJson
      map = json.convertTo[Map[String, Result]]
      (name, result) <- map.toSeq.sortBy(_._1)
    } (resMap.get(name), result) match {
      case (None, _) => error(s"'[$tag] $name' test is removed")
      case (Some(Fail), Yet(_) | Pass) => error(s"'[$tag] $name' test becomes failed")
      case _ =>
    }

    // save abstract result if backward-compatible
    if (breakCount == 0) {
      val dirname = s"$TEST_DIR/result/$category"
      mkdir(dirname)
      val pw = getPrintWriter(s"$dirname/$this")
      val (x, y) = getScore(resMap)
      pw.println(s"$tag: $x / $y")
      pw.close()

      val jpw = getPrintWriter(filename)
      jpw.println(resMap.toJson.sortedPrint)
      jpw.close()
    }
  }

  // show failure message
  def failMsg(msg: String): Unit = printlnColor(RED)("[FAIL] " + msg)
  def warnMsg(msg: String): Unit = printlnColor(YELLOW)("[WARN] " + msg)
  def passMsg(msg: String): Unit = printlnColor(GREEN)("[PASS] " + msg)

  // test name
  val name: String

  // registration
  def init: Unit
}
