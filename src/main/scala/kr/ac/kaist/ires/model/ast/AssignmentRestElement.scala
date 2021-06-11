package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AssignmentRestElement extends AST {
  val kind: String = "AssignmentRestElement"
}
object AssignmentRestElement extends ASTHelper {
  def apply(v: JsValue): AssignmentRestElement = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentRestElement0(DestructuringAssignmentTarget(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class AssignmentRestElement0(x1: DestructuringAssignmentTarget, parserParams: List[Boolean], span: Span) extends AssignmentRestElement {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"... $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("DestructuringAssignmentTarget", x1, Nil).reverse
  val info: ASTInfo = AssignmentRestElement0
}
object AssignmentRestElement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IteratorDestructuringAssignmentEvaluation0" -> `AL::AssignmentRestElement[0,0].IteratorDestructuringAssignmentEvaluation`,
  )
}
