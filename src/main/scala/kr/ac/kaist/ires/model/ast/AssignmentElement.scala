package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AssignmentElement extends AST {
  val kind: String = "AssignmentElement"
}

case class AssignmentElement0(x0: DestructuringAssignmentTarget, x1: Option[Initializer], parserParams: List[Boolean]) extends AssignmentElement {
  x0.parent = Some(this)
  x1.foreach((m) => m.parent = Some(this))
  val name: String = "AssignmentElement0"
  override def toString: String = {
    s"$x0 ${x1.getOrElse("")}"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Option[Initializer]", x1, l("DestructuringAssignmentTarget", x0, Nil)).reverse
  val info: ASTInfo = AssignmentElement0
}
object AssignmentElement0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "IteratorDestructuringAssignmentEvaluation1" -> `AL::AssignmentElement[0,1].IteratorDestructuringAssignmentEvaluation`,
    "KeyedDestructuringAssignmentEvaluation1" -> `AL::AssignmentElement[0,1].KeyedDestructuringAssignmentEvaluation`,
  )
}
