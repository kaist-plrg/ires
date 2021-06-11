package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait BindingProperty extends AST {
  val kind: String = "BindingProperty"
}
object BindingProperty extends ASTHelper {
  def apply(v: JsValue): BindingProperty = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      BindingProperty0(SingleNameBinding(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      BindingProperty1(PropertyName(x0), BindingElement(x2), params)
    case _ => throw InvalidAST
  }
}

case class BindingProperty0(x0: SingleNameBinding, parserParams: List[Boolean]) extends BindingProperty {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("SingleNameBinding", x0, Nil).reverse
  val info: ASTInfo = BindingProperty0
}
object BindingProperty0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "PropertyBindingInitialization0" -> `AL::BindingProperty[0,0].PropertyBindingInitialization`,
  )
}

case class BindingProperty1(x0: PropertyName, x2: BindingElement, parserParams: List[Boolean]) extends BindingProperty {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 : $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("BindingElement", x2, l("PropertyName", x0, Nil)).reverse
  val info: ASTInfo = BindingProperty1
}
object BindingProperty1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::BindingProperty[1,0].BoundNames`,
    "PropertyBindingInitialization0" -> `AL::BindingProperty[1,0].PropertyBindingInitialization`,
    "ContainsExpression0" -> `AL::BindingProperty[1,0].ContainsExpression`,
  )
}
