package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait CaseClauses extends AST {
  val kind: String = "CaseClauses"
}

case class CaseClauses0(x0: CaseClause, parserParams: List[Boolean]) extends CaseClauses {
  x0.parent = Some(this)
  val name: String = "CaseClauses0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("CaseClause", x0, Nil).reverse
  val info: ASTInfo = CaseClauses0
}
object CaseClauses0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class CaseClauses1(x0: CaseClauses, x1: CaseClause, parserParams: List[Boolean]) extends CaseClauses {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val name: String = "CaseClauses1"
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("CaseClause", x1, l("CaseClauses", x0, Nil)).reverse
  val info: ASTInfo = CaseClauses1
}
object CaseClauses1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::CaseClauses[1,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::CaseClauses[1,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::CaseClauses[1,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::CaseClauses[1,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::CaseClauses[1,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::CaseClauses[1,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::CaseClauses[1,0].ContainsUndefinedContinueTarget`,
    "HasCallInTailPosition0" -> `AL::CaseClauses[1,0].HasCallInTailPosition`,
  )
}
