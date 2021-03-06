package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ObjectBindingPattern extends AST {
  val kind: String = "ObjectBindingPattern"
}
object ObjectBindingPattern extends ASTHelper {
  def apply(v: JsValue): ObjectBindingPattern = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      ObjectBindingPattern0(params, span)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ObjectBindingPattern1(BindingRestProperty(x1), params, span)
    case JsSeq(JsInt(2), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ObjectBindingPattern2(BindingPropertyList(x1), params, span)
    case JsSeq(JsInt(3), JsSeq(x1, x3), JsBoolSeq(params), JsSpan(span)) =>
      ObjectBindingPattern3(BindingPropertyList(x1), opt(x3, BindingRestProperty.apply), params, span)
    case _ => throw InvalidAST
  }
}

case class ObjectBindingPattern0(parserParams: List[Boolean], span: Span) extends ObjectBindingPattern {
  val idx: Int = 0
  override def toString: String = {
    s"{ }"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = ObjectBindingPattern0
}
object ObjectBindingPattern0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ObjectBindingPattern[0,0].BoundNames`,
    "BindingInitialization0" -> `AL::ObjectBindingPattern[0,0].BindingInitialization`,
    "ContainsExpression0" -> `AL::ObjectBindingPattern[0,0].ContainsExpression`,
  )
}

case class ObjectBindingPattern1(x1: BindingRestProperty, parserParams: List[Boolean], span: Span) extends ObjectBindingPattern {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"{ $x1 }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("BindingRestProperty", x1, Nil).reverse
  val info: ASTInfo = ObjectBindingPattern1
}
object ObjectBindingPattern1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BindingInitialization0" -> `AL::ObjectBindingPattern[1,0].BindingInitialization`,
    "ContainsExpression0" -> `AL::ObjectBindingPattern[1,0].ContainsExpression`,
  )
}

case class ObjectBindingPattern2(x1: BindingPropertyList, parserParams: List[Boolean], span: Span) extends ObjectBindingPattern {
  x1.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"{ $x1 }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("BindingPropertyList", x1, Nil).reverse
  val info: ASTInfo = ObjectBindingPattern2
}
object ObjectBindingPattern2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BindingInitialization0" -> `AL::ObjectBindingPattern[2,0].BindingInitialization`,
  )
}

case class ObjectBindingPattern3(x1: BindingPropertyList, x3: Option[BindingRestProperty], parserParams: List[Boolean], span: Span) extends ObjectBindingPattern {
  x1.parent = Some(this)
  x3.foreach((m) => m.parent = Some(this))
  val idx: Int = 3
  override def toString: String = {
    s"{ $x1 , ${x3.getOrElse("")} }"
  }
  val k: Int = d(x3, d(x1, 0))
  val fullList: List[(String, Value)] = l("Option[BindingRestProperty]", x3, l("BindingPropertyList", x1, Nil)).reverse
  val info: ASTInfo = ObjectBindingPattern3
}
object ObjectBindingPattern3 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "BoundNames1" -> `AL::ObjectBindingPattern[3,1].BoundNames`,
    "BindingInitialization0" -> `AL::ObjectBindingPattern[3,0].BindingInitialization`,
    "BindingInitialization1" -> `AL::ObjectBindingPattern[3,1].BindingInitialization`,
    "ContainsExpression1" -> `AL::ObjectBindingPattern[3,1].ContainsExpression`,
  )
}
