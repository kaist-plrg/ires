package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait LogicalANDExpression extends AST {
  val kind: String = "LogicalANDExpression"
}

case class LogicalANDExpression0(x0: BitwiseORExpression, parserParams: List[Boolean]) extends LogicalANDExpression {
  x0.parent = Some(this)
  val name: String = "LogicalANDExpression0"
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
  val name: String = "LogicalANDExpression1"
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
