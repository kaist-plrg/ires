package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ElementList extends AST {
  val kind: String = "ElementList"
}
object ElementList extends ASTHelper {
  def apply(v: JsValue): ElementList = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      ElementList0(opt(x0, Elision.apply), AssignmentExpression(x1), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      ElementList1(opt(x0, Elision.apply), SpreadElement(x1), params, span)
    case JsSeq(JsInt(2), JsSeq(x0, x2, x3), JsBoolSeq(params), JsSpan(span)) =>
      ElementList2(ElementList(x0), opt(x2, Elision.apply), AssignmentExpression(x3), params, span)
    case JsSeq(JsInt(3), JsSeq(x0, x2, x3), JsBoolSeq(params), JsSpan(span)) =>
      ElementList3(ElementList(x0), opt(x2, Elision.apply), SpreadElement(x3), params, span)
    case _ => throw InvalidAST
  }
}

case class ElementList0(x0: Option[Elision], x1: AssignmentExpression, parserParams: List[Boolean], span: Span) extends ElementList {
  x0.foreach((m) => m.parent = Some(this))
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"${x0.getOrElse("")} $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentExpression", x1, l("Option[Elision]", x0, Nil)).reverse
  val info: ASTInfo = ElementList0
}
object ElementList0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "ArrayAccumulation1" -> `AL::ElementList[0,1].ArrayAccumulation`,
  )
}

case class ElementList1(x0: Option[Elision], x1: SpreadElement, parserParams: List[Boolean], span: Span) extends ElementList {
  x0.foreach((m) => m.parent = Some(this))
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"${x0.getOrElse("")} $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("SpreadElement", x1, l("Option[Elision]", x0, Nil)).reverse
  val info: ASTInfo = ElementList1
}
object ElementList1 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "ArrayAccumulation1" -> `AL::ElementList[1,1].ArrayAccumulation`,
  )
}

case class ElementList2(x0: ElementList, x2: Option[Elision], x3: AssignmentExpression, parserParams: List[Boolean], span: Span) extends ElementList {
  x0.parent = Some(this)
  x2.foreach((m) => m.parent = Some(this))
  x3.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0 , ${x2.getOrElse("")} $x3"
  }
  val k: Int = d(x3, d(x2, d(x0, 0)))
  val fullList: List[(String, Value)] = l("AssignmentExpression", x3, l("Option[Elision]", x2, l("ElementList", x0, Nil))).reverse
  val info: ASTInfo = ElementList2
}
object ElementList2 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "ArrayAccumulation1" -> `AL::ElementList[2,1].ArrayAccumulation`,
  )
}

case class ElementList3(x0: ElementList, x2: Option[Elision], x3: SpreadElement, parserParams: List[Boolean], span: Span) extends ElementList {
  x0.parent = Some(this)
  x2.foreach((m) => m.parent = Some(this))
  x3.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0 , ${x2.getOrElse("")} $x3"
  }
  val k: Int = d(x3, d(x2, d(x0, 0)))
  val fullList: List[(String, Value)] = l("SpreadElement", x3, l("Option[Elision]", x2, l("ElementList", x0, Nil))).reverse
  val info: ASTInfo = ElementList3
}
object ElementList3 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "ArrayAccumulation1" -> `AL::ElementList[3,1].ArrayAccumulation`,
  )
}
