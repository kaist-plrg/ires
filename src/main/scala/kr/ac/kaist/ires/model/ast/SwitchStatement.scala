package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait SwitchStatement extends AST {
  val kind: String = "SwitchStatement"
}
object SwitchStatement extends ASTHelper {
  def apply(v: JsValue): SwitchStatement = v match {
    case JsSeq(JsInt(0), JsSeq(x2, x4), JsBoolSeq(params), JsSpan(span)) =>
      SwitchStatement0(Expression(x2), CaseBlock(x4), params, span)
    case _ => throw InvalidAST
  }
}

case class SwitchStatement0(x2: Expression, x4: CaseBlock, parserParams: List[Boolean], span: Span) extends SwitchStatement {
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
