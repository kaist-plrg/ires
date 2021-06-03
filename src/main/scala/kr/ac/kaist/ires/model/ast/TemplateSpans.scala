package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait TemplateSpans extends AST {
  val kind: String = "TemplateSpans"
}

case class TemplateSpans0(x0: Lexical, parserParams: List[Boolean]) extends TemplateSpans {
  x0.parent = Some(this)
  val name: String = "TemplateSpans0"
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
  val name: String = "TemplateSpans1"
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
