package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait MemberExpression extends AST {
  val kind: String = "MemberExpression"
}

case class MemberExpression0(x0: PrimaryExpression, parserParams: List[Boolean]) extends MemberExpression {
  x0.parent = Some(this)
  val name: String = "MemberExpression0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("PrimaryExpression", x0, Nil).reverse
  val info: ASTInfo = MemberExpression0
}
object MemberExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsDestructuring0" -> `AL::MemberExpression[0,0].IsDestructuring`,
  )
}

case class MemberExpression1(x0: MemberExpression, x2: Expression, parserParams: List[Boolean]) extends MemberExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val name: String = "MemberExpression1"
  override def toString: String = {
    s"$x0 [ $x2 ]"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("Expression", x2, l("MemberExpression", x0, Nil)).reverse
  val info: ASTInfo = MemberExpression1
}
object MemberExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MemberExpression[1,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::MemberExpression[1,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::MemberExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::MemberExpression[1,0].Evaluation`,
    "IsDestructuring0" -> `AL::MemberExpression[1,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::MemberExpression[1,0].HasCallInTailPosition`,
  )
}

case class MemberExpression2(x0: MemberExpression, x2: Lexical, parserParams: List[Boolean]) extends MemberExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val name: String = "MemberExpression2"
  override def toString: String = {
    s"$x0 . $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("Lexical", x2, l("MemberExpression", x0, Nil)).reverse
  val info: ASTInfo = MemberExpression2
}
object MemberExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MemberExpression[2,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::MemberExpression[2,0].IsIdentifierRef`,
    "Contains0" -> `AL::MemberExpression[2,0].Contains`,
    "AssignmentTargetType0" -> `AL::MemberExpression[2,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::MemberExpression[2,0].Evaluation`,
    "IsDestructuring0" -> `AL::MemberExpression[2,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::MemberExpression[2,0].HasCallInTailPosition`,
  )
}

case class MemberExpression3(x0: MemberExpression, x1: TemplateLiteral, parserParams: List[Boolean]) extends MemberExpression {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val name: String = "MemberExpression3"
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("TemplateLiteral", x1, l("MemberExpression", x0, Nil)).reverse
  val info: ASTInfo = MemberExpression3
}
object MemberExpression3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MemberExpression[3,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::MemberExpression[3,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::MemberExpression[3,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::MemberExpression[3,0].Evaluation`,
    "IsDestructuring0" -> `AL::MemberExpression[3,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::MemberExpression[3,0].HasCallInTailPosition`,
  )
}

case class MemberExpression4(x0: SuperProperty, parserParams: List[Boolean]) extends MemberExpression {
  x0.parent = Some(this)
  val name: String = "MemberExpression4"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("SuperProperty", x0, Nil).reverse
  val info: ASTInfo = MemberExpression4
}
object MemberExpression4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MemberExpression[4,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::MemberExpression[4,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::MemberExpression[4,0].AssignmentTargetType`,
    "IsDestructuring0" -> `AL::MemberExpression[4,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::MemberExpression[4,0].HasCallInTailPosition`,
  )
}

case class MemberExpression5(x0: MetaProperty, parserParams: List[Boolean]) extends MemberExpression {
  x0.parent = Some(this)
  val name: String = "MemberExpression5"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("MetaProperty", x0, Nil).reverse
  val info: ASTInfo = MemberExpression5
}
object MemberExpression5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MemberExpression[5,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::MemberExpression[5,0].IsIdentifierRef`,
    "IsDestructuring0" -> `AL::MemberExpression[5,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::MemberExpression[5,0].HasCallInTailPosition`,
  )
}

case class MemberExpression6(x1: MemberExpression, x2: Arguments, parserParams: List[Boolean]) extends MemberExpression {
  x1.parent = Some(this)
  x2.parent = Some(this)
  val name: String = "MemberExpression6"
  override def toString: String = {
    s"new $x1 $x2"
  }
  val k: Int = d(x2, d(x1, 0))
  val fullList: List[(String, Value)] = l("Arguments", x2, l("MemberExpression", x1, Nil)).reverse
  val info: ASTInfo = MemberExpression6
}
object MemberExpression6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MemberExpression[6,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::MemberExpression[6,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::MemberExpression[6,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::MemberExpression[6,0].Evaluation`,
    "IsDestructuring0" -> `AL::MemberExpression[6,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::MemberExpression[6,0].HasCallInTailPosition`,
  )
}
