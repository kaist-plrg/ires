package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait NewExpression extends AST {
  val kind: String = "NewExpression"
}

case class NewExpression0(x0: MemberExpression, parserParams: List[Boolean]) extends NewExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("MemberExpression", x0, Nil).reverse
  val info: ASTInfo = NewExpression0
}
object NewExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class NewExpression1(x1: NewExpression, parserParams: List[Boolean]) extends NewExpression {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"new $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("NewExpression", x1, Nil).reverse
  val info: ASTInfo = NewExpression1
}
object NewExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::NewExpression[1,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::NewExpression[1,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::NewExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::NewExpression[1,0].Evaluation`,
    "IsDestructuring0" -> `AL::NewExpression[1,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::NewExpression[1,0].HasCallInTailPosition`,
  )
}
