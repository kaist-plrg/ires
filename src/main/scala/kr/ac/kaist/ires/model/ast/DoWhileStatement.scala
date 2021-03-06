package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait DoWhileStatement extends AST {
  val kind: String = "DoWhileStatement"
}
object DoWhileStatement extends ASTHelper {
  def apply(v: JsValue): DoWhileStatement = v match {
    case JsSeq(JsInt(0), JsSeq(x1, x4), JsBoolSeq(params), JsSpan(span)) =>
      DoWhileStatement0(Statement(x1), Expression(x4), params, span)
    case _ => throw InvalidAST
  }
}

case class DoWhileStatement0(x1: Statement, x4: Expression, parserParams: List[Boolean], span: Span) extends DoWhileStatement {
  x1.parent = Some(this)
  x4.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"do $x1 while ( $x4 ) ;"
  }
  val k: Int = d(x4, d(x1, 0))
  val fullList: List[(String, Value)] = l("Expression", x4, l("Statement", x1, Nil)).reverse
  val info: ASTInfo = DoWhileStatement0
}
object DoWhileStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::DoWhileStatement[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::DoWhileStatement[0,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::DoWhileStatement[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::DoWhileStatement[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::DoWhileStatement[0,0].ContainsUndefinedContinueTarget`,
    "DoWhileLoopEvaluation0" -> `AL::DoWhileStatement[0,0].DoWhileLoopEvaluation`,
    "HasCallInTailPosition0" -> `AL::DoWhileStatement[0,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::DoWhileStatement[0,0].EarlyErrors`,
  )
}
