package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait WhileStatement extends AST {
  val kind: String = "WhileStatement"
}
object WhileStatement extends ASTHelper {
  def apply(v: JsValue): WhileStatement = v match {
    case JsSeq(JsInt(0), JsSeq(x2, x4), JsBoolSeq(params), JsSpan(span)) =>
      WhileStatement0(Expression(x2), Statement(x4), params)
    case _ => throw InvalidAST
  }
}

case class WhileStatement0(x2: Expression, x4: Statement, parserParams: List[Boolean]) extends WhileStatement {
  x2.parent = Some(this)
  x4.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"while ( $x2 ) $x4"
  }
  val k: Int = d(x4, d(x2, 0))
  val fullList: List[(String, Value)] = l("Statement", x4, l("Expression", x2, Nil)).reverse
  val info: ASTInfo = WhileStatement0
}
object WhileStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::WhileStatement[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::WhileStatement[0,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::WhileStatement[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::WhileStatement[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::WhileStatement[0,0].ContainsUndefinedContinueTarget`,
    "WhileLoopEvaluation0" -> `AL::WhileStatement[0,0].WhileLoopEvaluation`,
    "HasCallInTailPosition0" -> `AL::WhileStatement[0,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::WhileStatement[0,0].EarlyErrors`,
  )
}
