package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait TemplateLiteral extends AST {
  val kind: String = "TemplateLiteral"
}
object TemplateLiteral extends ASTHelper {
  def apply(v: JsValue): TemplateLiteral = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      TemplateLiteral0(lex("NoSubstitutionTemplate", x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      TemplateLiteral1(SubstitutionTemplate(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class TemplateLiteral0(x0: Lexical, parserParams: List[Boolean], span: Span) extends TemplateLiteral {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Lexical", x0, Nil).reverse
  val info: ASTInfo = TemplateLiteral0
}
object TemplateLiteral0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "TemplateStrings0" -> `AL::TemplateLiteral[0,0].TemplateStrings`,
    "Evaluation0" -> `AL::TemplateLiteral[0,0].Evaluation`,
    "ArgumentListEvaluation0" -> `AL::TemplateLiteral[0,0].ArgumentListEvaluation`,
    "EarlyErrors0" -> `AL::TemplateLiteral[0,0].EarlyErrors`,
  )
}

case class TemplateLiteral1(x0: SubstitutionTemplate, parserParams: List[Boolean], span: Span) extends TemplateLiteral {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("SubstitutionTemplate", x0, Nil).reverse
  val info: ASTInfo = TemplateLiteral1
}
object TemplateLiteral1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ArgumentListEvaluation0" -> `AL::TemplateLiteral[1,0].ArgumentListEvaluation`,
    "EarlyErrors0" -> `AL::TemplateLiteral[1,0].EarlyErrors`,
  )
}
