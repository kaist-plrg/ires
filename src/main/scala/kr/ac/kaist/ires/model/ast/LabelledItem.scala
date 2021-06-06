package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait LabelledItem extends AST {
  val kind: String = "LabelledItem"
}

case class LabelledItem0(x0: Statement, parserParams: List[Boolean]) extends LabelledItem {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Statement", x0, Nil).reverse
  val info: ASTInfo = LabelledItem0
}
object LabelledItem0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::LabelledItem[0,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::LabelledItem[0,0].LexicallyScopedDeclarations`,
    "TopLevelVarDeclaredNames0" -> `AL::LabelledItem[0,0].TopLevelVarDeclaredNames`,
    "TopLevelVarScopedDeclarations0" -> `AL::LabelledItem[0,0].TopLevelVarScopedDeclarations`,
  )
}

case class LabelledItem1(x0: FunctionDeclaration, parserParams: List[Boolean]) extends LabelledItem {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FunctionDeclaration", x0, Nil).reverse
  val info: ASTInfo = LabelledItem1
}
object LabelledItem1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::LabelledItem[1,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::LabelledItem[1,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::LabelledItem[1,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::LabelledItem[1,0].VarScopedDeclarations`,
    "TopLevelVarDeclaredNames0" -> `AL::LabelledItem[1,0].TopLevelVarDeclaredNames`,
    "TopLevelVarScopedDeclarations0" -> `AL::LabelledItem[1,0].TopLevelVarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::LabelledItem[1,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::LabelledItem[1,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::LabelledItem[1,0].ContainsUndefinedContinueTarget`,
    "LabelledEvaluation0" -> `AL::LabelledItem[1,0].LabelledEvaluation`,
    "HasCallInTailPosition0" -> `AL::LabelledItem[1,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::LabelledItem[1,0].EarlyErrors`,
  )
}
