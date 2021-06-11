package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AssignmentProperty extends AST {
  val kind: String = "AssignmentProperty"
}
object AssignmentProperty extends ASTHelper {
  def apply(v: JsValue): AssignmentProperty = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentProperty0(IdentifierReference(x0), opt(x1, Initializer.apply), params)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentProperty1(PropertyName(x0), AssignmentElement(x2), params)
    case _ => throw InvalidAST
  }
}

case class AssignmentProperty0(x0: IdentifierReference, x1: Option[Initializer], parserParams: List[Boolean]) extends AssignmentProperty {
  x0.parent = Some(this)
  x1.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"$x0 ${x1.getOrElse("")}"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Option[Initializer]", x1, l("IdentifierReference", x0, Nil)).reverse
  val info: ASTInfo = AssignmentProperty0
}
object AssignmentProperty0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "PropertyDestructuringAssignmentEvaluation1" -> `AL::AssignmentProperty[0,1].PropertyDestructuringAssignmentEvaluation`,
    "EarlyErrors1" -> `AL::AssignmentProperty[0,1].EarlyErrors`,
  )
}

case class AssignmentProperty1(x0: PropertyName, x2: AssignmentElement, parserParams: List[Boolean]) extends AssignmentProperty {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 : $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentElement", x2, l("PropertyName", x0, Nil)).reverse
  val info: ASTInfo = AssignmentProperty1
}
object AssignmentProperty1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "PropertyDestructuringAssignmentEvaluation0" -> `AL::AssignmentProperty[1,0].PropertyDestructuringAssignmentEvaluation`,
  )
}
