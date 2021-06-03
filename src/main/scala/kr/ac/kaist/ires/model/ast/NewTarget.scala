package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait NewTarget extends AST {
  val kind: String = "NewTarget"
}

case class NewTarget0(parserParams: List[Boolean]) extends NewTarget {
  val name: String = "NewTarget0"
  override def toString: String = {
    s"new . target"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = NewTarget0
}
object NewTarget0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "AssignmentTargetType0" -> `AL::NewTarget[0,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::NewTarget[0,0].Evaluation`,
  )
}
