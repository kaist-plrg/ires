package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait LabelledStatement extends AST {
  val kind: String = "LabelledStatement"
}
object LabelledStatement extends ASTHelper {
  def apply(v: JsValue): LabelledStatement = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      LabelledStatement0(LabelIdentifier(x0), LabelledItem(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class LabelledStatement0(x0: LabelIdentifier, x2: LabelledItem, parserParams: List[Boolean], span: Span) extends LabelledStatement {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0 : $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("LabelledItem", x2, l("LabelIdentifier", x0, Nil)).reverse
  val info: ASTInfo = LabelledStatement0
}
object LabelledStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::LabelledStatement[0,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::LabelledStatement[0,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::LabelledStatement[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::LabelledStatement[0,0].VarScopedDeclarations`,
    "TopLevelLexicallyDeclaredNames0" -> `AL::LabelledStatement[0,0].TopLevelLexicallyDeclaredNames`,
    "TopLevelLexicallyScopedDeclarations0" -> `AL::LabelledStatement[0,0].TopLevelLexicallyScopedDeclarations`,
    "TopLevelVarDeclaredNames0" -> `AL::LabelledStatement[0,0].TopLevelVarDeclaredNames`,
    "TopLevelVarScopedDeclarations0" -> `AL::LabelledStatement[0,0].TopLevelVarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::LabelledStatement[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::LabelledStatement[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::LabelledStatement[0,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::LabelledStatement[0,0].Evaluation`,
    "LabelledEvaluation0" -> `AL::LabelledStatement[0,0].LabelledEvaluation`,
    "HasCallInTailPosition0" -> `AL::LabelledStatement[0,0].HasCallInTailPosition`,
  )
}
