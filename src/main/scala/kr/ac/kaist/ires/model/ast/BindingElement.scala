package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait BindingElement extends AST {
  val kind: String = "BindingElement"
}
object BindingElement extends ASTHelper {
  def apply(v: JsValue): BindingElement = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      BindingElement0(SingleNameBinding(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      BindingElement1(BindingPattern(x0), opt(x1, Initializer.apply), params)
    case _ => throw InvalidAST
  }
}

case class BindingElement0(x0: SingleNameBinding, parserParams: List[Boolean]) extends BindingElement {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("SingleNameBinding", x0, Nil).reverse
  val info: ASTInfo = BindingElement0
}
object BindingElement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class BindingElement1(x0: BindingPattern, x1: Option[Initializer], parserParams: List[Boolean]) extends BindingElement {
  x0.parent = Some(this)
  x1.foreach((m) => m.parent = Some(this))
  val idx: Int = 1
  override def toString: String = {
    s"$x0 ${x1.getOrElse("")}"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Option[Initializer]", x1, l("BindingPattern", x0, Nil)).reverse
  val info: ASTInfo = BindingElement1
}
object BindingElement1 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "BoundNames1" -> `AL::BindingElement[1,1].BoundNames`,
    "IteratorBindingInitialization1" -> `AL::BindingElement[1,1].IteratorBindingInitialization`,
    "KeyedBindingInitialization1" -> `AL::BindingElement[1,1].KeyedBindingInitialization`,
    "ContainsExpression1" -> `AL::BindingElement[1,1].ContainsExpression`,
    "IsSimpleParameterList0" -> `AL::BindingElement[1,0].IsSimpleParameterList`,
    "IsSimpleParameterList1" -> `AL::BindingElement[1,1].IsSimpleParameterList`,
    "HasInitializer0" -> `AL::BindingElement[1,0].HasInitializer`,
    "HasInitializer1" -> `AL::BindingElement[1,1].HasInitializer`,
  )
}
