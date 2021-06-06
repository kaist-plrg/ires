package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait RelationalExpression extends AST {
  val kind: String = "RelationalExpression"
}

case class RelationalExpression0(x0: ShiftExpression, parserParams: List[Boolean]) extends RelationalExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ShiftExpression", x0, Nil).reverse
  val info: ASTInfo = RelationalExpression0
}
object RelationalExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class RelationalExpression1(x0: RelationalExpression, x2: ShiftExpression, parserParams: List[Boolean]) extends RelationalExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 < $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("ShiftExpression", x2, l("RelationalExpression", x0, Nil)).reverse
  val info: ASTInfo = RelationalExpression1
}
object RelationalExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::RelationalExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::RelationalExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::RelationalExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::RelationalExpression[1,0].HasCallInTailPosition`,
  )
}

case class RelationalExpression2(x0: RelationalExpression, x2: ShiftExpression, parserParams: List[Boolean]) extends RelationalExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0 > $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("ShiftExpression", x2, l("RelationalExpression", x0, Nil)).reverse
  val info: ASTInfo = RelationalExpression2
}
object RelationalExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::RelationalExpression[2,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::RelationalExpression[2,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::RelationalExpression[2,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::RelationalExpression[2,0].HasCallInTailPosition`,
  )
}

case class RelationalExpression3(x0: RelationalExpression, x2: ShiftExpression, parserParams: List[Boolean]) extends RelationalExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0 <= $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("ShiftExpression", x2, l("RelationalExpression", x0, Nil)).reverse
  val info: ASTInfo = RelationalExpression3
}
object RelationalExpression3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::RelationalExpression[3,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::RelationalExpression[3,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::RelationalExpression[3,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::RelationalExpression[3,0].HasCallInTailPosition`,
  )
}

case class RelationalExpression4(x0: RelationalExpression, x2: ShiftExpression, parserParams: List[Boolean]) extends RelationalExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"$x0 >= $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("ShiftExpression", x2, l("RelationalExpression", x0, Nil)).reverse
  val info: ASTInfo = RelationalExpression4
}
object RelationalExpression4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::RelationalExpression[4,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::RelationalExpression[4,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::RelationalExpression[4,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::RelationalExpression[4,0].HasCallInTailPosition`,
  )
}

case class RelationalExpression5(x0: RelationalExpression, x2: ShiftExpression, parserParams: List[Boolean]) extends RelationalExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 5
  override def toString: String = {
    s"$x0 instanceof $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("ShiftExpression", x2, l("RelationalExpression", x0, Nil)).reverse
  val info: ASTInfo = RelationalExpression5
}
object RelationalExpression5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::RelationalExpression[5,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::RelationalExpression[5,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::RelationalExpression[5,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::RelationalExpression[5,0].HasCallInTailPosition`,
  )
}

case class RelationalExpression6(x0: RelationalExpression, x2: ShiftExpression, parserParams: List[Boolean]) extends RelationalExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 6
  override def toString: String = {
    s"$x0 in $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("ShiftExpression", x2, l("RelationalExpression", x0, Nil)).reverse
  val info: ASTInfo = RelationalExpression6
}
object RelationalExpression6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::RelationalExpression[6,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::RelationalExpression[6,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::RelationalExpression[6,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::RelationalExpression[6,0].HasCallInTailPosition`,
  )
}
