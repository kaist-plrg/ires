package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait BindingList extends AST {
  val kind: String = "BindingList"
}
object BindingList extends ASTHelper {
  def apply(v: JsValue): BindingList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      BindingList0(LexicalBinding(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      BindingList1(BindingList(x0), LexicalBinding(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class BindingList0(x0: LexicalBinding, parserParams: List[Boolean], span: Span) extends BindingList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("LexicalBinding", x0, Nil).reverse
  val info: ASTInfo = BindingList0
}
object BindingList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class BindingList1(x0: BindingList, x2: LexicalBinding, parserParams: List[Boolean], span: Span) extends BindingList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("LexicalBinding", x2, l("BindingList", x0, Nil)).reverse
  val info: ASTInfo = BindingList1
}
object BindingList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::BindingList[1,0].BoundNames`,
    "Evaluation0" -> `AL::BindingList[1,0].Evaluation`,
  )
}
