package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait BindingElementList extends AST {
  val kind: String = "BindingElementList"
}
object BindingElementList extends ASTHelper {
  def apply(v: JsValue): BindingElementList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      BindingElementList0(BindingElisionElement(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      BindingElementList1(BindingElementList(x0), BindingElisionElement(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class BindingElementList0(x0: BindingElisionElement, parserParams: List[Boolean], span: Span) extends BindingElementList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingElisionElement", x0, Nil).reverse
  val info: ASTInfo = BindingElementList0
}
object BindingElementList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class BindingElementList1(x0: BindingElementList, x2: BindingElisionElement, parserParams: List[Boolean], span: Span) extends BindingElementList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("BindingElisionElement", x2, l("BindingElementList", x0, Nil)).reverse
  val info: ASTInfo = BindingElementList1
}
object BindingElementList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::BindingElementList[1,0].BoundNames`,
    "IteratorBindingInitialization0" -> `AL::BindingElementList[1,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::BindingElementList[1,0].ContainsExpression`,
  )
}
