package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AssignmentOperator extends AST {
  val kind: String = "AssignmentOperator"
}

case class AssignmentOperator0(parserParams: List[Boolean]) extends AssignmentOperator {
  val idx: Int = 0
  override def toString: String = {
    s"*="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator0
}
object AssignmentOperator0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator1(parserParams: List[Boolean]) extends AssignmentOperator {
  val idx: Int = 1
  override def toString: String = {
    s"/="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator1
}
object AssignmentOperator1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator2(parserParams: List[Boolean]) extends AssignmentOperator {
  val idx: Int = 2
  override def toString: String = {
    s"%="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator2
}
object AssignmentOperator2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator3(parserParams: List[Boolean]) extends AssignmentOperator {
  val idx: Int = 3
  override def toString: String = {
    s"+="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator3
}
object AssignmentOperator3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator4(parserParams: List[Boolean]) extends AssignmentOperator {
  val idx: Int = 4
  override def toString: String = {
    s"-="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator4
}
object AssignmentOperator4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator5(parserParams: List[Boolean]) extends AssignmentOperator {
  val idx: Int = 5
  override def toString: String = {
    s"<<="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator5
}
object AssignmentOperator5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator6(parserParams: List[Boolean]) extends AssignmentOperator {
  val idx: Int = 6
  override def toString: String = {
    s">>="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator6
}
object AssignmentOperator6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator7(parserParams: List[Boolean]) extends AssignmentOperator {
  val idx: Int = 7
  override def toString: String = {
    s">>>="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator7
}
object AssignmentOperator7 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator8(parserParams: List[Boolean]) extends AssignmentOperator {
  val idx: Int = 8
  override def toString: String = {
    s"&="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator8
}
object AssignmentOperator8 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator9(parserParams: List[Boolean]) extends AssignmentOperator {
  val idx: Int = 9
  override def toString: String = {
    s"^="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator9
}
object AssignmentOperator9 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator10(parserParams: List[Boolean]) extends AssignmentOperator {
  val idx: Int = 10
  override def toString: String = {
    s"|="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator10
}
object AssignmentOperator10 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentOperator11(parserParams: List[Boolean]) extends AssignmentOperator {
  val idx: Int = 11
  override def toString: String = {
    s"**="
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = AssignmentOperator11
}
object AssignmentOperator11 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
