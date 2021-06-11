package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait BreakStatement extends AST {
  val kind: String = "BreakStatement"
}
object BreakStatement extends ASTHelper {
  def apply(v: JsValue): BreakStatement = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      BreakStatement0(params)
    case JsSeq(JsInt(1), JsSeq(x2), JsBoolSeq(params), JsSpan(span)) =>
      BreakStatement1(LabelIdentifier(x2), params)
    case _ => throw InvalidAST
  }
}

case class BreakStatement0(parserParams: List[Boolean]) extends BreakStatement {
  val idx: Int = 0
  override def toString: String = {
    s"break ;"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = BreakStatement0
}
object BreakStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ContainsUndefinedBreakTarget0" -> `AL::BreakStatement[0,0].ContainsUndefinedBreakTarget`,
    "Evaluation0" -> `AL::BreakStatement[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::BreakStatement[0,0].EarlyErrors`,
  )
}

case class BreakStatement1(x2: LabelIdentifier, parserParams: List[Boolean]) extends BreakStatement {
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"break $x2 ;"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("LabelIdentifier", x2, Nil).reverse
  val info: ASTInfo = BreakStatement1
}
object BreakStatement1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ContainsUndefinedBreakTarget0" -> `AL::BreakStatement[1,0].ContainsUndefinedBreakTarget`,
    "Evaluation0" -> `AL::BreakStatement[1,0].Evaluation`,
  )
}
