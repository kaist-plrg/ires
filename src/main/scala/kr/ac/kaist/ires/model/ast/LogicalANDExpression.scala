package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait LogicalANDExpression extends AST {
  val kind: String = "LogicalANDExpression"
}
object LogicalANDExpression extends ASTHelper {
  def apply(v: JsValue): LogicalANDExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      LogicalANDExpression0(BitwiseORExpression(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      LogicalANDExpression1(LogicalANDExpression(x0), BitwiseORExpression(x2), params)
    case _ => throw InvalidAST
  }
}

case class LogicalANDExpression0(x0: BitwiseORExpression, parserParams: List[Boolean]) extends LogicalANDExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BitwiseORExpression", x0, Nil).reverse
  val info: ASTInfo = LogicalANDExpression0
}
object LogicalANDExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class LogicalANDExpression1(x0: LogicalANDExpression, x2: BitwiseORExpression, parserParams: List[Boolean]) extends LogicalANDExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 && $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("BitwiseORExpression", x2, l("LogicalANDExpression", x0, Nil)).reverse
  val info: ASTInfo = LogicalANDExpression1
}
object LogicalANDExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::LogicalANDExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::LogicalANDExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::LogicalANDExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::LogicalANDExpression[1,0].HasCallInTailPosition`,
  )
}
