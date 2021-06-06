package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait LeftHandSideExpression extends AST {
  val kind: String = "LeftHandSideExpression"
}

case class LeftHandSideExpression0(x0: NewExpression, parserParams: List[Boolean]) extends LeftHandSideExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("NewExpression", x0, Nil).reverse
  val info: ASTInfo = LeftHandSideExpression0
}
object LeftHandSideExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class LeftHandSideExpression1(x0: CallExpression, parserParams: List[Boolean]) extends LeftHandSideExpression {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("CallExpression", x0, Nil).reverse
  val info: ASTInfo = LeftHandSideExpression1
}
object LeftHandSideExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::LeftHandSideExpression[1,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::LeftHandSideExpression[1,0].IsIdentifierRef`,
    "IsDestructuring0" -> `AL::LeftHandSideExpression[1,0].IsDestructuring`,
  )
}

case class LeftHandSideExpression2(x0: OptionalExpression, parserParams: List[Boolean]) extends LeftHandSideExpression {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("OptionalExpression", x0, Nil).reverse
  val info: ASTInfo = LeftHandSideExpression2
}
object LeftHandSideExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::LeftHandSideExpression[2,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::LeftHandSideExpression[2,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::LeftHandSideExpression[2,0].AssignmentTargetType`,
    "IsDestructuring0" -> `AL::LeftHandSideExpression[2,0].IsDestructuring`,
  )
}
