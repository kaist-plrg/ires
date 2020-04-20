package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.{ AST, ASTInfo, Lexical }
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AssignmentPattern extends AST {
  val kind: String = "AssignmentPattern"
}
case class AssignmentPattern0(x0: ObjectAssignmentPattern, parserParams: List[Boolean]) extends AssignmentPattern {
  x0.parent = Some(this)
  val name: String = "AssignmentPattern0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ObjectAssignmentPattern", x0, Nil).reverse
  val info: ASTInfo = AssignmentPattern0
}
object AssignmentPattern0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Func] = Map()
}
case class AssignmentPattern1(x0: ArrayAssignmentPattern, parserParams: List[Boolean]) extends AssignmentPattern {
  x0.parent = Some(this)
  val name: String = "AssignmentPattern1"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ArrayAssignmentPattern", x0, Nil).reverse
  val info: ASTInfo = AssignmentPattern1
}
object AssignmentPattern1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Func] = Map()
}
