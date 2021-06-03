package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait UnaryExpression extends AST {
  val kind: String = "UnaryExpression"
}

case class UnaryExpression0(x0: UpdateExpression, parserParams: List[Boolean]) extends UnaryExpression {
  x0.parent = Some(this)
  val name: String = "UnaryExpression0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("UpdateExpression", x0, Nil).reverse
  val info: ASTInfo = UnaryExpression0
}
object UnaryExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class UnaryExpression1(x1: UnaryExpression, parserParams: List[Boolean]) extends UnaryExpression {
  x1.parent = Some(this)
  val name: String = "UnaryExpression1"
  override def toString: String = {
    s"delete $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression1
}
object UnaryExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[1,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression2(x1: UnaryExpression, parserParams: List[Boolean]) extends UnaryExpression {
  x1.parent = Some(this)
  val name: String = "UnaryExpression2"
  override def toString: String = {
    s"void $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression2
}
object UnaryExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[2,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[2,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[2,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[2,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression3(x1: UnaryExpression, parserParams: List[Boolean]) extends UnaryExpression {
  x1.parent = Some(this)
  val name: String = "UnaryExpression3"
  override def toString: String = {
    s"typeof $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression3
}
object UnaryExpression3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[3,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[3,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[3,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[3,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression4(x1: UnaryExpression, parserParams: List[Boolean]) extends UnaryExpression {
  x1.parent = Some(this)
  val name: String = "UnaryExpression4"
  override def toString: String = {
    s"+ $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression4
}
object UnaryExpression4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[4,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[4,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[4,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[4,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression5(x1: UnaryExpression, parserParams: List[Boolean]) extends UnaryExpression {
  x1.parent = Some(this)
  val name: String = "UnaryExpression5"
  override def toString: String = {
    s"- $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression5
}
object UnaryExpression5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[5,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[5,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[5,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[5,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression6(x1: UnaryExpression, parserParams: List[Boolean]) extends UnaryExpression {
  x1.parent = Some(this)
  val name: String = "UnaryExpression6"
  override def toString: String = {
    s"~ $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression6
}
object UnaryExpression6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[6,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[6,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[6,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[6,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression7(x1: UnaryExpression, parserParams: List[Boolean]) extends UnaryExpression {
  x1.parent = Some(this)
  val name: String = "UnaryExpression7"
  override def toString: String = {
    s"! $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression7
}
object UnaryExpression7 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[7,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[7,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[7,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[7,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression8(x0: AwaitExpression, parserParams: List[Boolean]) extends UnaryExpression {
  x0.parent = Some(this)
  val name: String = "UnaryExpression8"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AwaitExpression", x0, Nil).reverse
  val info: ASTInfo = UnaryExpression8
}
object UnaryExpression8 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[8,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[8,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[8,0].HasCallInTailPosition`,
  )
}
