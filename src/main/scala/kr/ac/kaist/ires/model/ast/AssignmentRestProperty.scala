package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AssignmentRestProperty extends AST {
  val kind: String = "AssignmentRestProperty"
}
object AssignmentRestProperty extends ASTHelper {
  def apply(v: JsValue): AssignmentRestProperty = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentRestProperty0(DestructuringAssignmentTarget(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class AssignmentRestProperty0(x1: DestructuringAssignmentTarget, parserParams: List[Boolean], span: Span) extends AssignmentRestProperty {
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
