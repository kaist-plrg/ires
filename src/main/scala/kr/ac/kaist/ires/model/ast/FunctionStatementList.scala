package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait FunctionStatementList extends AST {
  val kind: String = "FunctionStatementList"
}

case class FunctionStatementList0(x0: Option[StatementList], parserParams: List[Boolean]) extends FunctionStatementList {
  x0.foreach((m) => m.parent = Some(this))
  val name: String = "FunctionStatementList0"
  override def toString: String = {
    s"${x0.getOrElse("")}"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Option[StatementList]", x0, Nil).reverse
  val info: ASTInfo = FunctionStatementList0
}
object FunctionStatementList0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::FunctionStatementList[0,0].LexicallyDeclaredNames`,
    "LexicallyDeclaredNames1" -> `AL::FunctionStatementList[0,1].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::FunctionStatementList[0,0].LexicallyScopedDeclarations`,
    "LexicallyScopedDeclarations1" -> `AL::FunctionStatementList[0,1].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::FunctionStatementList[0,0].VarDeclaredNames`,
    "VarDeclaredNames1" -> `AL::FunctionStatementList[0,1].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::FunctionStatementList[0,0].VarScopedDeclarations`,
    "VarScopedDeclarations1" -> `AL::FunctionStatementList[0,1].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::FunctionStatementList[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::FunctionStatementList[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::FunctionStatementList[0,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::FunctionStatementList[0,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::FunctionStatementList[0,0].HasCallInTailPosition`,
  )
}
