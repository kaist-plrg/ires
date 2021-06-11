package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait BreakableStatement extends AST {
  val kind: String = "BreakableStatement"
}
object BreakableStatement extends ASTHelper {
  def apply(v: JsValue): BreakableStatement = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      BreakableStatement0(IterationStatement(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      BreakableStatement1(SwitchStatement(x0), params)
    case _ => throw InvalidAST
  }
}

case class BreakableStatement0(x0: IterationStatement, parserParams: List[Boolean]) extends BreakableStatement {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("IterationStatement", x0, Nil).reverse
  val info: ASTInfo = BreakableStatement0
}
object BreakableStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ContainsUndefinedContinueTarget0" -> `AL::BreakableStatement[0,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::BreakableStatement[0,0].Evaluation`,
    "LabelledEvaluation0" -> `AL::BreakableStatement[0,0].LabelledEvaluation`,
  )
}

case class BreakableStatement1(x0: SwitchStatement, parserParams: List[Boolean]) extends BreakableStatement {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("SwitchStatement", x0, Nil).reverse
  val info: ASTInfo = BreakableStatement1
}
object BreakableStatement1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::BreakableStatement[1,0].Evaluation`,
    "LabelledEvaluation0" -> `AL::BreakableStatement[1,0].LabelledEvaluation`,
  )
}
