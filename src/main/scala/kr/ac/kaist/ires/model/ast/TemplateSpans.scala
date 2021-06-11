package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait TemplateSpans extends AST {
  val kind: String = "TemplateSpans"
}
object TemplateSpans extends ASTHelper {
  def apply(v: JsValue): TemplateSpans = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      TemplateSpans0(lex("TemplateTail", x0), params)
    case JsSeq(JsInt(1), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      TemplateSpans1(TemplateMiddleList(x0), lex("TemplateTail", x1), params)
    case _ => throw InvalidAST
  }
}

case class TemplateSpans0(x0: Lexical, parserParams: List[Boolean]) extends TemplateSpans {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Lexical", x0, Nil).reverse
  val info: ASTInfo = TemplateSpans0
}
object TemplateSpans0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "TemplateStrings0" -> `AL::TemplateSpans[0,0].TemplateStrings`,
    "SubstitutionEvaluation0" -> `AL::TemplateSpans[0,0].SubstitutionEvaluation`,
    "Evaluation0" -> `AL::TemplateSpans[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::TemplateSpans[0,0].EarlyErrors`,
  )
}

case class TemplateSpans1(x0: TemplateMiddleList, x1: Lexical, parserParams: List[Boolean]) extends TemplateSpans {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Lexical", x1, l("TemplateMiddleList", x0, Nil)).reverse
  val info: ASTInfo = TemplateSpans1
}
object TemplateSpans1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "TemplateStrings0" -> `AL::TemplateSpans[1,0].TemplateStrings`,
    "SubstitutionEvaluation0" -> `AL::TemplateSpans[1,0].SubstitutionEvaluation`,
    "Evaluation0" -> `AL::TemplateSpans[1,0].Evaluation`,
  )
}
