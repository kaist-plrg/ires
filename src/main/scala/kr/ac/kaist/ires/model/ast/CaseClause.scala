package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait CaseClause extends AST {
  val kind: String = "CaseClause"
}

case class CaseClause0(x1: Expression, x3: Option[StatementList], parserParams: List[Boolean]) extends CaseClause {
  x1.parent = Some(this)
  x3.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"case $x1 : ${x3.getOrElse("")}"
  }
  val k: Int = d(x3, d(x1, 0))
  val fullList: List[(String, Value)] = l("Option[StatementList]", x3, l("Expression", x1, Nil)).reverse
  val info: ASTInfo = CaseClause0
}
object CaseClause0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames1" -> `AL::CaseClause[0,1].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations1" -> `AL::CaseClause[0,1].LexicallyScopedDeclarations`,
    "VarDeclaredNames1" -> `AL::CaseClause[0,1].VarDeclaredNames`,
    "VarScopedDeclarations1" -> `AL::CaseClause[0,1].VarScopedDeclarations`,
    "ContainsDuplicateLabels1" -> `AL::CaseClause[0,1].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget1" -> `AL::CaseClause[0,1].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget1" -> `AL::CaseClause[0,1].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::CaseClause[0,0].Evaluation`,
    "Evaluation1" -> `AL::CaseClause[0,1].Evaluation`,
    "HasCallInTailPosition1" -> `AL::CaseClause[0,1].HasCallInTailPosition`,
  )
}
