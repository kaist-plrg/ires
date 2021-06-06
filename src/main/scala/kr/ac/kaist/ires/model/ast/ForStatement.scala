package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ForStatement extends AST {
  val kind: String = "ForStatement"
}

case class ForStatement0(x3: Option[Expression], x5: Option[Expression], x7: Option[Expression], x9: Statement, parserParams: List[Boolean]) extends ForStatement {
  x3.foreach((m) => m.parent = Some(this))
  x5.foreach((m) => m.parent = Some(this))
  x7.foreach((m) => m.parent = Some(this))
  x9.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"for ( ${x3.getOrElse("")} ; ${x5.getOrElse("")} ; ${x7.getOrElse("")} ) $x9"
  }
  val k: Int = d(x9, d(x7, d(x5, d(x3, 0))))
  val fullList: List[(String, Value)] = l("Statement", x9, l("Option[Expression]2", x7, l("Option[Expression]1", x5, l("Option[Expression]0", x3, Nil)))).reverse
  val info: ASTInfo = ForStatement0
}
object ForStatement0 extends ASTInfo {
  val maxK: Int = 7
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames7" -> `AL::ForStatement[0,7].VarDeclaredNames`,
    "VarScopedDeclarations7" -> `AL::ForStatement[0,7].VarScopedDeclarations`,
    "ContainsDuplicateLabels7" -> `AL::ForStatement[0,7].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget7" -> `AL::ForStatement[0,7].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget7" -> `AL::ForStatement[0,7].ContainsUndefinedContinueTarget`,
    "ForLoopEvaluation7" -> `AL::ForStatement[0,7].ForLoopEvaluation`,
    "HasCallInTailPosition7" -> `AL::ForStatement[0,7].HasCallInTailPosition`,
    "EarlyErrors7" -> `AL::ForStatement[0,7].EarlyErrors`,
  )
}

case class ForStatement1(x3: VariableDeclarationList, x5: Option[Expression], x7: Option[Expression], x9: Statement, parserParams: List[Boolean]) extends ForStatement {
  x3.parent = Some(this)
  x5.foreach((m) => m.parent = Some(this))
  x7.foreach((m) => m.parent = Some(this))
  x9.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"for ( var $x3 ; ${x5.getOrElse("")} ; ${x7.getOrElse("")} ) $x9"
  }
  val k: Int = d(x9, d(x7, d(x5, d(x3, 0))))
  val fullList: List[(String, Value)] = l("Statement", x9, l("Option[Expression]1", x7, l("Option[Expression]0", x5, l("VariableDeclarationList", x3, Nil)))).reverse
  val info: ASTInfo = ForStatement1
}
object ForStatement1 extends ASTInfo {
  val maxK: Int = 3
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames3" -> `AL::ForStatement[1,3].VarDeclaredNames`,
    "VarScopedDeclarations3" -> `AL::ForStatement[1,3].VarScopedDeclarations`,
    "ContainsDuplicateLabels3" -> `AL::ForStatement[1,3].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget3" -> `AL::ForStatement[1,3].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget3" -> `AL::ForStatement[1,3].ContainsUndefinedContinueTarget`,
    "ForLoopEvaluation3" -> `AL::ForStatement[1,3].ForLoopEvaluation`,
    "HasCallInTailPosition3" -> `AL::ForStatement[1,3].HasCallInTailPosition`,
    "EarlyErrors3" -> `AL::ForStatement[1,3].EarlyErrors`,
  )
}

case class ForStatement2(x2: LexicalDeclaration, x3: Option[Expression], x5: Option[Expression], x7: Statement, parserParams: List[Boolean]) extends ForStatement {
  x2.parent = Some(this)
  x3.foreach((m) => m.parent = Some(this))
  x5.foreach((m) => m.parent = Some(this))
  x7.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"for ( $x2 ${x3.getOrElse("")} ; ${x5.getOrElse("")} ) $x7"
  }
  val k: Int = d(x7, d(x5, d(x3, d(x2, 0))))
  val fullList: List[(String, Value)] = l("Statement", x7, l("Option[Expression]1", x5, l("Option[Expression]0", x3, l("LexicalDeclaration", x2, Nil)))).reverse
  val info: ASTInfo = ForStatement2
}
object ForStatement2 extends ASTInfo {
  val maxK: Int = 3
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames3" -> `AL::ForStatement[2,3].VarDeclaredNames`,
    "VarScopedDeclarations3" -> `AL::ForStatement[2,3].VarScopedDeclarations`,
    "ContainsDuplicateLabels3" -> `AL::ForStatement[2,3].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget3" -> `AL::ForStatement[2,3].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget3" -> `AL::ForStatement[2,3].ContainsUndefinedContinueTarget`,
    "ForLoopEvaluation3" -> `AL::ForStatement[2,3].ForLoopEvaluation`,
    "HasCallInTailPosition3" -> `AL::ForStatement[2,3].HasCallInTailPosition`,
    "EarlyErrors3" -> `AL::ForStatement[2,3].EarlyErrors`,
    "EarlyErrors3" -> `AL::ForStatement[2,3].EarlyErrors`,
  )
}
