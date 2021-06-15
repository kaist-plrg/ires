package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait LabelledItem extends AST {
  val kind: String = "LabelledItem"
}
object LabelledItem extends ASTHelper {
  def apply(v: JsValue): LabelledItem = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      LabelledItem0(Statement(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      LabelledItem1(FunctionDeclaration(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class LabelledItem0(x0: Statement, parserParams: List[Boolean], span: Span) extends LabelledItem {
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

case class LabelledItem1(x0: FunctionDeclaration, parserParams: List[Boolean], span: Span) extends LabelledItem {
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
