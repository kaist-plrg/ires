package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait IfStatement extends AST {
  val kind: String = "IfStatement"
}
object IfStatement extends ASTHelper {
  def apply(v: JsValue): IfStatement = v match {
    case JsSeq(JsInt(0), JsSeq(x2, x4, x6), JsBoolSeq(params), JsSpan(span)) =>
      IfStatement0(Expression(x2), Statement(x4), Statement(x6), params, span)
    case JsSeq(JsInt(1), JsSeq(x2, x4), JsBoolSeq(params), JsSpan(span)) =>
      IfStatement1(Expression(x2), Statement(x4), params, span)
    case _ => throw InvalidAST
  }
}

case class IfStatement0(x2: Expression, x4: Statement, x6: Statement, parserParams: List[Boolean], span: Span) extends IfStatement {
  x2.parent = Some(this)
  x4.parent = Some(this)
  x6.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"if ( $x2 ) $x4 else $x6"
  }
  val k: Int = d(x6, d(x4, d(x2, 0)))
  val fullList: List[(String, Value)] = l("Statement1", x6, l("Statement0", x4, l("Expression", x2, Nil))).reverse
  val info: ASTInfo = IfStatement0
}
object IfStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::IfStatement[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::IfStatement[0,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::IfStatement[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::IfStatement[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::IfStatement[0,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::IfStatement[0,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::IfStatement[0,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::IfStatement[0,0].EarlyErrors`,
  )
}

case class IfStatement1(x2: Expression, x4: Statement, parserParams: List[Boolean], span: Span) extends IfStatement {
  x2.parent = Some(this)
  x4.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"if ( $x2 ) $x4"
  }
  val k: Int = d(x4, d(x2, 0))
  val fullList: List[(String, Value)] = l("Statement", x4, l("Expression", x2, Nil)).reverse
  val info: ASTInfo = IfStatement1
}
object IfStatement1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::IfStatement[1,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::IfStatement[1,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::IfStatement[1,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::IfStatement[1,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::IfStatement[1,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::IfStatement[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::IfStatement[1,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::IfStatement[1,0].EarlyErrors`,
  )
}
