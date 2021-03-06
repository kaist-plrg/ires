package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ArrayBindingPattern extends AST {
  val kind: String = "ArrayBindingPattern"
}
object ArrayBindingPattern extends ASTHelper {
  def apply(v: JsValue): ArrayBindingPattern = v match {
    case JsSeq(JsInt(0), JsSeq(x1, x2), JsBoolSeq(params), JsSpan(span)) =>
      ArrayBindingPattern0(opt(x1, Elision.apply), opt(x2, BindingRestElement.apply), params, span)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ArrayBindingPattern1(BindingElementList(x1), params, span)
    case JsSeq(JsInt(2), JsSeq(x1, x3, x4), JsBoolSeq(params), JsSpan(span)) =>
      ArrayBindingPattern2(BindingElementList(x1), opt(x3, Elision.apply), opt(x4, BindingRestElement.apply), params, span)
    case _ => throw InvalidAST
  }
}

case class ArrayBindingPattern0(x1: Option[Elision], x2: Option[BindingRestElement], parserParams: List[Boolean], span: Span) extends ArrayBindingPattern {
  x1.foreach((m) => m.parent = Some(this))
  x2.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"[ ${x1.getOrElse("")} ${x2.getOrElse("")} ]"
  }
  val k: Int = d(x2, d(x1, 0))
  val fullList: List[(String, Value)] = l("Option[BindingRestElement]", x2, l("Option[Elision]", x1, Nil)).reverse
  val info: ASTInfo = ArrayBindingPattern0
}
object ArrayBindingPattern0 extends ASTInfo {
  val maxK: Int = 3
  val semMap: Map[String, Algo] = Map(
    "BoundNames2" -> `AL::ArrayBindingPattern[0,2].BoundNames`,
    "BoundNames3" -> `AL::ArrayBindingPattern[0,3].BoundNames`,
    "IteratorBindingInitialization0" -> `AL::ArrayBindingPattern[0,0].IteratorBindingInitialization`,
    "IteratorBindingInitialization2" -> `AL::ArrayBindingPattern[0,2].IteratorBindingInitialization`,
    "IteratorBindingInitialization3" -> `AL::ArrayBindingPattern[0,3].IteratorBindingInitialization`,
    "ContainsExpression2" -> `AL::ArrayBindingPattern[0,2].ContainsExpression`,
    "ContainsExpression3" -> `AL::ArrayBindingPattern[0,3].ContainsExpression`,
  )
}

case class ArrayBindingPattern1(x1: BindingElementList, parserParams: List[Boolean], span: Span) extends ArrayBindingPattern {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"[ $x1 ]"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("BindingElementList", x1, Nil).reverse
  val info: ASTInfo = ArrayBindingPattern1
}
object ArrayBindingPattern1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class ArrayBindingPattern2(x1: BindingElementList, x3: Option[Elision], x4: Option[BindingRestElement], parserParams: List[Boolean], span: Span) extends ArrayBindingPattern {
  x1.parent = Some(this)
  x3.foreach((m) => m.parent = Some(this))
  x4.foreach((m) => m.parent = Some(this))
  val idx: Int = 2
  override def toString: String = {
    s"[ $x1 , ${x3.getOrElse("")} ${x4.getOrElse("")} ]"
  }
  val k: Int = d(x4, d(x3, d(x1, 0)))
  val fullList: List[(String, Value)] = l("Option[BindingRestElement]", x4, l("Option[Elision]", x3, l("BindingElementList", x1, Nil))).reverse
  val info: ASTInfo = ArrayBindingPattern2
}
object ArrayBindingPattern2 extends ASTInfo {
  val maxK: Int = 3
  val semMap: Map[String, Algo] = Map(
    "BoundNames2" -> `AL::ArrayBindingPattern[2,2].BoundNames`,
    "BoundNames3" -> `AL::ArrayBindingPattern[2,3].BoundNames`,
    "IteratorBindingInitialization2" -> `AL::ArrayBindingPattern[2,2].IteratorBindingInitialization`,
    "IteratorBindingInitialization3" -> `AL::ArrayBindingPattern[2,3].IteratorBindingInitialization`,
    "ContainsExpression2" -> `AL::ArrayBindingPattern[2,2].ContainsExpression`,
    "ContainsExpression3" -> `AL::ArrayBindingPattern[2,3].ContainsExpression`,
  )
}
