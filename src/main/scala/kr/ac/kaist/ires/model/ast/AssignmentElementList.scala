package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AssignmentElementList extends AST {
  val kind: String = "AssignmentElementList"
}

case class AssignmentElementList0(x0: AssignmentElisionElement, parserParams: List[Boolean]) extends AssignmentElementList {
  x0.parent = Some(this)
  val name: String = "AssignmentElementList0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AssignmentElisionElement", x0, Nil).reverse
  val info: ASTInfo = AssignmentElementList0
}
object AssignmentElementList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IteratorDestructuringAssignmentEvaluation0" -> `AL::AssignmentElementList[0,0].IteratorDestructuringAssignmentEvaluation`,
  )
}

case class AssignmentElementList1(x0: AssignmentElementList, x2: AssignmentElisionElement, parserParams: List[Boolean]) extends AssignmentElementList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val name: String = "AssignmentElementList1"
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentElisionElement", x2, l("AssignmentElementList", x0, Nil)).reverse
  val info: ASTInfo = AssignmentElementList1
}
object AssignmentElementList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IteratorDestructuringAssignmentEvaluation0" -> `AL::AssignmentElementList[1,0].IteratorDestructuringAssignmentEvaluation`,
  )
}
