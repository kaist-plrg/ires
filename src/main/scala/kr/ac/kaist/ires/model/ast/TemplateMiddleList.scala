package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait TemplateMiddleList extends AST {
  val kind: String = "TemplateMiddleList"
}

case class TemplateMiddleList0(x0: Lexical, x1: Expression, parserParams: List[Boolean]) extends TemplateMiddleList {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Expression", x1, l("Lexical", x0, Nil)).reverse
  val info: ASTInfo = TemplateMiddleList0
}
object TemplateMiddleList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "TemplateStrings0" -> `AL::TemplateMiddleList[0,0].TemplateStrings`,
    "SubstitutionEvaluation0" -> `AL::TemplateMiddleList[0,0].SubstitutionEvaluation`,
    "Evaluation0" -> `AL::TemplateMiddleList[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::TemplateMiddleList[0,0].EarlyErrors`,
  )
}

case class TemplateMiddleList1(x0: TemplateMiddleList, x1: Lexical, x2: Expression, parserParams: List[Boolean]) extends TemplateMiddleList {
  x0.parent = Some(this)
  x1.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 $x1 $x2"
  }
  val k: Int = d(x2, d(x1, d(x0, 0)))
  val fullList: List[(String, Value)] = l("Expression", x2, l("Lexical", x1, l("TemplateMiddleList", x0, Nil))).reverse
  val info: ASTInfo = TemplateMiddleList1
}
object TemplateMiddleList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "TemplateStrings0" -> `AL::TemplateMiddleList[1,0].TemplateStrings`,
    "SubstitutionEvaluation0" -> `AL::TemplateMiddleList[1,0].SubstitutionEvaluation`,
    "Evaluation0" -> `AL::TemplateMiddleList[1,0].Evaluation`,
    "EarlyErrors0" -> `AL::TemplateMiddleList[1,0].EarlyErrors`,
  )
}
