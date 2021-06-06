package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait LogicalORExpression extends AST {
  val kind: String = "LogicalORExpression"
}

case class LogicalORExpression0(x0: LogicalANDExpression, parserParams: List[Boolean]) extends LogicalORExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("LogicalANDExpression", x0, Nil).reverse
  val info: ASTInfo = LogicalORExpression0
}
object LogicalORExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class LogicalORExpression1(x0: LogicalORExpression, x2: LogicalANDExpression, parserParams: List[Boolean]) extends LogicalORExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 || $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("LogicalANDExpression", x2, l("LogicalORExpression", x0, Nil)).reverse
  val info: ASTInfo = LogicalORExpression1
}
object LogicalORExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::LogicalORExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::LogicalORExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::LogicalORExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::LogicalORExpression[1,0].HasCallInTailPosition`,
  )
}
