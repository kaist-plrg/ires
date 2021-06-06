package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait SwitchStatement extends AST {
  val kind: String = "SwitchStatement"
}

case class SwitchStatement0(x2: Expression, x4: CaseBlock, parserParams: List[Boolean]) extends SwitchStatement {
  x2.parent = Some(this)
  x4.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"switch ( $x2 ) $x4"
  }
  val k: Int = d(x4, d(x2, 0))
  val fullList: List[(String, Value)] = l("CaseBlock", x4, l("Expression", x2, Nil)).reverse
  val info: ASTInfo = SwitchStatement0
}
object SwitchStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::SwitchStatement[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::SwitchStatement[0,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::SwitchStatement[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::SwitchStatement[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::SwitchStatement[0,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::SwitchStatement[0,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::SwitchStatement[0,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::SwitchStatement[0,0].EarlyErrors`,
  )
}
