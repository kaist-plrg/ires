package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait MetaProperty extends AST {
  val kind: String = "MetaProperty"
}
object MetaProperty extends ASTHelper {
  def apply(v: JsValue): MetaProperty = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      MetaProperty0(NewTarget(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      MetaProperty1(ImportMeta(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class MetaProperty0(x0: NewTarget, parserParams: List[Boolean], span: Span) extends MetaProperty {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("NewTarget", x0, Nil).reverse
  val info: ASTInfo = MetaProperty0
}
object MetaProperty0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class MetaProperty1(x0: ImportMeta, parserParams: List[Boolean], span: Span) extends MetaProperty {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ImportMeta", x0, Nil).reverse
  val info: ASTInfo = MetaProperty1
}
object MetaProperty1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
