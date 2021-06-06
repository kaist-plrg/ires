package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AssignmentRestProperty extends AST {
  val kind: String = "AssignmentRestProperty"
}

case class AssignmentRestProperty0(x1: DestructuringAssignmentTarget, parserParams: List[Boolean]) extends AssignmentRestProperty {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"... $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("DestructuringAssignmentTarget", x1, Nil).reverse
  val info: ASTInfo = AssignmentRestProperty0
}
object AssignmentRestProperty0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "RestDestructuringAssignmentEvaluation0" -> `AL::AssignmentRestProperty[0,0].RestDestructuringAssignmentEvaluation`,
    "EarlyErrors0" -> `AL::AssignmentRestProperty[0,0].EarlyErrors`,
  )
}
