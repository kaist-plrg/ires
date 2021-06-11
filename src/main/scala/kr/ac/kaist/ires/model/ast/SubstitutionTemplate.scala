package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait SubstitutionTemplate extends AST {
  val kind: String = "SubstitutionTemplate"
}
object SubstitutionTemplate extends ASTHelper {
  def apply(v: JsValue): SubstitutionTemplate = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x1, x2), JsBoolSeq(params), JsSpan(span)) =>
      SubstitutionTemplate0(lex("TemplateHead", x0), Expression(x1), TemplateSpans(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class SubstitutionTemplate0(x0: Lexical, x1: Expression, x2: TemplateSpans, parserParams: List[Boolean], span: Span) extends SubstitutionTemplate {
  x0.parent = Some(this)
  x1.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0 $x1 $x2"
  }
  val k: Int = d(x2, d(x1, d(x0, 0)))
  val fullList: List[(String, Value)] = l("TemplateSpans", x2, l("Expression", x1, l("Lexical", x0, Nil))).reverse
  val info: ASTInfo = SubstitutionTemplate0
}
object SubstitutionTemplate0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "TemplateStrings0" -> `AL::SubstitutionTemplate[0,0].TemplateStrings`,
    "Evaluation0" -> `AL::SubstitutionTemplate[0,0].Evaluation`,
    "ArgumentListEvaluation0" -> `AL::SubstitutionTemplate[0,0].ArgumentListEvaluation`,
    "EarlyErrors0" -> `AL::SubstitutionTemplate[0,0].EarlyErrors`,
  )
}
