package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ConditionalExpression extends AST {
  val kind: String = "ConditionalExpression"
}

case class ConditionalExpression0(x0: ShortCircuitExpression, parserParams: List[Boolean]) extends ConditionalExpression {
  x0.parent = Some(this)
  val name: String = "ConditionalExpression0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ShortCircuitExpression", x0, Nil).reverse
  val info: ASTInfo = ConditionalExpression0
}
object ConditionalExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class ConditionalExpression1(x0: ShortCircuitExpression, x2: AssignmentExpression, x4: AssignmentExpression, parserParams: List[Boolean]) extends ConditionalExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  x4.parent = Some(this)
  val name: String = "ConditionalExpression1"
  override def toString: String = {
    s"$x0 ? $x2 : $x4"
  }
  val k: Int = d(x4, d(x2, d(x0, 0)))
  val fullList: List[(String, Value)] = l("AssignmentExpression1", x4, l("AssignmentExpression0", x2, l("ShortCircuitExpression", x0, Nil))).reverse
  val info: ASTInfo = ConditionalExpression1
}
object ConditionalExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::ConditionalExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::ConditionalExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::ConditionalExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::ConditionalExpression[1,0].HasCallInTailPosition`,
  )
}
