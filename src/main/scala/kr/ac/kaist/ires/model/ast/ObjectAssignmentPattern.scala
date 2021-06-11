package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ObjectAssignmentPattern extends AST {
  val kind: String = "ObjectAssignmentPattern"
}
object ObjectAssignmentPattern extends ASTHelper {
  def apply(v: JsValue): ObjectAssignmentPattern = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      ObjectAssignmentPattern0(params, span)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ObjectAssignmentPattern1(AssignmentRestProperty(x1), params, span)
    case JsSeq(JsInt(2), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ObjectAssignmentPattern2(AssignmentPropertyList(x1), params, span)
    case JsSeq(JsInt(3), JsSeq(x1, x3), JsBoolSeq(params), JsSpan(span)) =>
      ObjectAssignmentPattern3(AssignmentPropertyList(x1), opt(x3, AssignmentRestProperty.apply), params, span)
    case _ => throw InvalidAST
  }
}

case class ObjectAssignmentPattern0(parserParams: List[Boolean], span: Span) extends ObjectAssignmentPattern {
  val idx: Int = 0
  override def toString: String = {
    s"{ }"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = ObjectAssignmentPattern0
}
object ObjectAssignmentPattern0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "DestructuringAssignmentEvaluation0" -> `AL::ObjectAssignmentPattern[0,0].DestructuringAssignmentEvaluation`,
  )
}

case class ObjectAssignmentPattern1(x1: AssignmentRestProperty, parserParams: List[Boolean], span: Span) extends ObjectAssignmentPattern {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"{ $x1 }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("AssignmentRestProperty", x1, Nil).reverse
  val info: ASTInfo = ObjectAssignmentPattern1
}
object ObjectAssignmentPattern1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "DestructuringAssignmentEvaluation0" -> `AL::ObjectAssignmentPattern[1,0].DestructuringAssignmentEvaluation`,
  )
}

case class ObjectAssignmentPattern2(x1: AssignmentPropertyList, parserParams: List[Boolean], span: Span) extends ObjectAssignmentPattern {
  x1.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"{ $x1 }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("AssignmentPropertyList", x1, Nil).reverse
  val info: ASTInfo = ObjectAssignmentPattern2
}
object ObjectAssignmentPattern2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "DestructuringAssignmentEvaluation0" -> `AL::ObjectAssignmentPattern[2,0].DestructuringAssignmentEvaluation`,
  )
}

case class ObjectAssignmentPattern3(x1: AssignmentPropertyList, x3: Option[AssignmentRestProperty], parserParams: List[Boolean], span: Span) extends ObjectAssignmentPattern {
  x1.parent = Some(this)
  x3.foreach((m) => m.parent = Some(this))
  val idx: Int = 3
  override def toString: String = {
    s"{ $x1 , ${x3.getOrElse("")} }"
  }
  val k: Int = d(x3, d(x1, 0))
  val fullList: List[(String, Value)] = l("Option[AssignmentRestProperty]", x3, l("AssignmentPropertyList", x1, Nil)).reverse
  val info: ASTInfo = ObjectAssignmentPattern3
}
object ObjectAssignmentPattern3 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "DestructuringAssignmentEvaluation0" -> `AL::ObjectAssignmentPattern[3,0].DestructuringAssignmentEvaluation`,
    "DestructuringAssignmentEvaluation1" -> `AL::ObjectAssignmentPattern[3,1].DestructuringAssignmentEvaluation`,
  )
}
