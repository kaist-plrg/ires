package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait SubstitutionTemplate extends AST {
  val kind: String = "SubstitutionTemplate"
}

case class SubstitutionTemplate0(x0: Lexical, x1: Expression, x2: TemplateSpans, parserParams: List[Boolean]) extends SubstitutionTemplate {
  x0.parent = Some(this)
  x1.parent = Some(this)
  x2.parent = Some(this)
  val name: String = "SubstitutionTemplate0"
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
