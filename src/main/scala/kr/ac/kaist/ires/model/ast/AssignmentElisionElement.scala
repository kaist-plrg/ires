package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AssignmentElisionElement extends AST {
  val kind: String = "AssignmentElisionElement"
}

case class AssignmentElisionElement0(x0: Option[Elision], x1: AssignmentElement, parserParams: List[Boolean]) extends AssignmentElisionElement {
  x0.foreach((m) => m.parent = Some(this))
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"${x0.getOrElse("")} $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentElement", x1, l("Option[Elision]", x0, Nil)).reverse
  val info: ASTInfo = AssignmentElisionElement0
}
object AssignmentElisionElement0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "IteratorDestructuringAssignmentEvaluation0" -> `AL::AssignmentElisionElement[0,0].IteratorDestructuringAssignmentEvaluation`,
    "IteratorDestructuringAssignmentEvaluation1" -> `AL::AssignmentElisionElement[0,1].IteratorDestructuringAssignmentEvaluation`,
  )
}
