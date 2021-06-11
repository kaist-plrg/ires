package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait Elision extends AST {
  val kind: String = "Elision"
}
object Elision extends ASTHelper {
  def apply(v: JsValue): Elision = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      Elision0(params)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Elision1(Elision(x0), params)
    case _ => throw InvalidAST
  }
}

case class Elision0(parserParams: List[Boolean]) extends Elision {
  val idx: Int = 0
  override def toString: String = {
    s","
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = Elision0
}
object Elision0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ArrayAccumulation0" -> `AL::Elision[0,0].ArrayAccumulation`,
    "IteratorDestructuringAssignmentEvaluation0" -> `AL::Elision[0,0].IteratorDestructuringAssignmentEvaluation`,
  )
}

case class Elision1(x0: Elision, parserParams: List[Boolean]) extends Elision {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 ,"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Elision", x0, Nil).reverse
  val info: ASTInfo = Elision1
}
object Elision1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ArrayAccumulation0" -> `AL::Elision[1,0].ArrayAccumulation`,
    "IteratorDestructuringAssignmentEvaluation0" -> `AL::Elision[1,0].IteratorDestructuringAssignmentEvaluation`,
  )
}
