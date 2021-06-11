package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait StatementListItem extends AST {
  val kind: String = "StatementListItem"
}
object StatementListItem extends ASTHelper {
  def apply(v: JsValue): StatementListItem = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      StatementListItem0(Statement(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      StatementListItem1(Declaration(x0), params)
    case _ => throw InvalidAST
  }
}

case class StatementListItem0(x0: Statement, parserParams: List[Boolean]) extends StatementListItem {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Statement", x0, Nil).reverse
  val info: ASTInfo = StatementListItem0
}
object StatementListItem0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::StatementListItem[0,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::StatementListItem[0,0].LexicallyScopedDeclarations`,
    "TopLevelLexicallyDeclaredNames0" -> `AL::StatementListItem[0,0].TopLevelLexicallyDeclaredNames`,
    "TopLevelLexicallyScopedDeclarations0" -> `AL::StatementListItem[0,0].TopLevelLexicallyScopedDeclarations`,
    "TopLevelVarDeclaredNames0" -> `AL::StatementListItem[0,0].TopLevelVarDeclaredNames`,
    "TopLevelVarScopedDeclarations0" -> `AL::StatementListItem[0,0].TopLevelVarScopedDeclarations`,
  )
}

case class StatementListItem1(x0: Declaration, parserParams: List[Boolean]) extends StatementListItem {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Declaration", x0, Nil).reverse
  val info: ASTInfo = StatementListItem1
}
object StatementListItem1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::StatementListItem[1,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::StatementListItem[1,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::StatementListItem[1,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::StatementListItem[1,0].VarScopedDeclarations`,
    "TopLevelLexicallyDeclaredNames0" -> `AL::StatementListItem[1,0].TopLevelLexicallyDeclaredNames`,
    "TopLevelLexicallyScopedDeclarations0" -> `AL::StatementListItem[1,0].TopLevelLexicallyScopedDeclarations`,
    "TopLevelVarDeclaredNames0" -> `AL::StatementListItem[1,0].TopLevelVarDeclaredNames`,
    "TopLevelVarScopedDeclarations0" -> `AL::StatementListItem[1,0].TopLevelVarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::StatementListItem[1,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::StatementListItem[1,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::StatementListItem[1,0].ContainsUndefinedContinueTarget`,
    "HasCallInTailPosition0" -> `AL::StatementListItem[1,0].HasCallInTailPosition`,
  )
}
