package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AssignmentExpression extends AST {
  val kind: String = "AssignmentExpression"
}

case class AssignmentExpression0(x0: ConditionalExpression, parserParams: List[Boolean]) extends AssignmentExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ConditionalExpression", x0, Nil).reverse
  val info: ASTInfo = AssignmentExpression0
}
object AssignmentExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentExpression1(x0: YieldExpression, parserParams: List[Boolean]) extends AssignmentExpression {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("YieldExpression", x0, Nil).reverse
  val info: ASTInfo = AssignmentExpression1
}
object AssignmentExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::AssignmentExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::AssignmentExpression[1,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::AssignmentExpression[1,0].HasCallInTailPosition`,
  )
}

case class AssignmentExpression2(x0: ArrowFunction, parserParams: List[Boolean]) extends AssignmentExpression {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ArrowFunction", x0, Nil).reverse
  val info: ASTInfo = AssignmentExpression2
}
object AssignmentExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::AssignmentExpression[2,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::AssignmentExpression[2,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::AssignmentExpression[2,0].HasCallInTailPosition`,
  )
}

case class AssignmentExpression3(x0: AsyncArrowFunction, parserParams: List[Boolean]) extends AssignmentExpression {
  x0.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AsyncArrowFunction", x0, Nil).reverse
  val info: ASTInfo = AssignmentExpression3
}
object AssignmentExpression3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::AssignmentExpression[3,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::AssignmentExpression[3,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::AssignmentExpression[3,0].HasCallInTailPosition`,
  )
}

case class AssignmentExpression4(x0: LeftHandSideExpression, x2: AssignmentExpression, parserParams: List[Boolean]) extends AssignmentExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"$x0 = $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentExpression", x2, l("LeftHandSideExpression", x0, Nil)).reverse
  val info: ASTInfo = AssignmentExpression4
}
object AssignmentExpression4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::AssignmentExpression[4,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::AssignmentExpression[4,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::AssignmentExpression[4,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::AssignmentExpression[4,0].HasCallInTailPosition`,
  )
}

case class AssignmentExpression5(x0: LeftHandSideExpression, x1: AssignmentOperator, x2: AssignmentExpression, parserParams: List[Boolean]) extends AssignmentExpression {
  x0.parent = Some(this)
  x1.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 5
  override def toString: String = {
    s"$x0 $x1 $x2"
  }
  val k: Int = d(x2, d(x1, d(x0, 0)))
  val fullList: List[(String, Value)] = l("AssignmentExpression", x2, l("AssignmentOperator", x1, l("LeftHandSideExpression", x0, Nil))).reverse
  val info: ASTInfo = AssignmentExpression5
}
object AssignmentExpression5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::AssignmentExpression[5,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::AssignmentExpression[5,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::AssignmentExpression[5,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::AssignmentExpression[5,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::AssignmentExpression[5,0].EarlyErrors`,
  )
}

case class AssignmentExpression6(x0: LeftHandSideExpression, x2: AssignmentExpression, parserParams: List[Boolean]) extends AssignmentExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 6
  override def toString: String = {
    s"$x0 &&= $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentExpression", x2, l("LeftHandSideExpression", x0, Nil)).reverse
  val info: ASTInfo = AssignmentExpression6
}
object AssignmentExpression6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::AssignmentExpression[6,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::AssignmentExpression[6,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::AssignmentExpression[6,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::AssignmentExpression[6,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::AssignmentExpression[6,0].EarlyErrors`,
  )
}

case class AssignmentExpression7(x0: LeftHandSideExpression, x2: AssignmentExpression, parserParams: List[Boolean]) extends AssignmentExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 7
  override def toString: String = {
    s"$x0 ||= $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentExpression", x2, l("LeftHandSideExpression", x0, Nil)).reverse
  val info: ASTInfo = AssignmentExpression7
}
object AssignmentExpression7 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::AssignmentExpression[7,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::AssignmentExpression[7,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::AssignmentExpression[7,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::AssignmentExpression[7,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::AssignmentExpression[7,0].EarlyErrors`,
  )
}

case class AssignmentExpression8(x0: LeftHandSideExpression, x2: AssignmentExpression, parserParams: List[Boolean]) extends AssignmentExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 8
  override def toString: String = {
    s"$x0 ??= $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentExpression", x2, l("LeftHandSideExpression", x0, Nil)).reverse
  val info: ASTInfo = AssignmentExpression8
}
object AssignmentExpression8 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::AssignmentExpression[8,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::AssignmentExpression[8,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::AssignmentExpression[8,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::AssignmentExpression[8,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::AssignmentExpression[8,0].EarlyErrors`,
  )
}
