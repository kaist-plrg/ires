package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait SuperCall extends AST {
  val kind: String = "SuperCall"
}
object SuperCall extends ASTHelper {
  def apply(v: JsValue): SuperCall = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      SuperCall0(Arguments(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class SuperCall0(x1: Arguments, parserParams: List[Boolean], span: Span) extends SuperCall {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"super $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Arguments", x1, Nil).reverse
  val info: ASTInfo = SuperCall0
}
object SuperCall0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::SuperCall[0,0].Evaluation`,
  )
}
