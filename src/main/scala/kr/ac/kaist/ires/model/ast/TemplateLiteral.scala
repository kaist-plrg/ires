package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait TemplateLiteral extends AST {
  val kind: String = "TemplateLiteral"
}

case class TemplateLiteral0(x0: Lexical, parserParams: List[Boolean]) extends TemplateLiteral {
  x0.parent = Some(this)
  val name: String = "TemplateLiteral0"
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

case class TemplateLiteral1(x0: SubstitutionTemplate, parserParams: List[Boolean]) extends TemplateLiteral {
  x0.parent = Some(this)
  val name: String = "TemplateLiteral1"
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
