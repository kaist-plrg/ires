package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait Catch extends AST {
  val kind: String = "Catch"
}

case class Catch0(x2: CatchParameter, x4: Block, parserParams: List[Boolean]) extends Catch {
  x2.parent = Some(this)
  x4.parent = Some(this)
  val name: String = "Catch0"
  override def toString: String = {
    s"catch ( $x2 ) $x4"
  }
  val k: Int = d(x4, d(x2, 0))
  val fullList: List[(String, Value)] = l("Block", x4, l("CatchParameter", x2, Nil)).reverse
  val info: ASTInfo = Catch0
}
object Catch0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::Catch[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::Catch[0,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::Catch[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::Catch[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::Catch[0,0].ContainsUndefinedContinueTarget`,
    "CatchClauseEvaluation0" -> `AL::Catch[0,0].CatchClauseEvaluation`,
    "HasCallInTailPosition0" -> `AL::Catch[0,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::Catch[0,0].EarlyErrors`,
  )
}

case class Catch1(x1: Block, parserParams: List[Boolean]) extends Catch {
  x1.parent = Some(this)
  val name: String = "Catch1"
  override def toString: String = {
    s"catch $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Block", x1, Nil).reverse
  val info: ASTInfo = Catch1
}
object Catch1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "CatchClauseEvaluation0" -> `AL::Catch[1,0].CatchClauseEvaluation`,
  )
}
