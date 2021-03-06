package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait CaseBlock extends AST {
  val kind: String = "CaseBlock"
}
object CaseBlock extends ASTHelper {
  def apply(v: JsValue): CaseBlock = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      CaseBlock0(opt(x1, CaseClauses.apply), params, span)
    case JsSeq(JsInt(1), JsSeq(x1, x2, x3), JsBoolSeq(params), JsSpan(span)) =>
      CaseBlock1(opt(x1, CaseClauses.apply), DefaultClause(x2), opt(x3, CaseClauses.apply), params, span)
    case _ => throw InvalidAST
  }
}

case class CaseBlock0(x1: Option[CaseClauses], parserParams: List[Boolean], span: Span) extends CaseBlock {
  x1.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"{ ${x1.getOrElse("")} }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Option[CaseClauses]", x1, Nil).reverse
  val info: ASTInfo = CaseBlock0
}
object CaseBlock0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::CaseBlock[0,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::CaseBlock[0,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::CaseBlock[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::CaseBlock[0,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::CaseBlock[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::CaseBlock[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::CaseBlock[0,0].ContainsUndefinedContinueTarget`,
    "CaseBlockEvaluation0" -> `AL::CaseBlock[0,0].CaseBlockEvaluation`,
    "CaseBlockEvaluation1" -> `AL::CaseBlock[0,1].CaseBlockEvaluation`,
    "HasCallInTailPosition0" -> `AL::CaseBlock[0,0].HasCallInTailPosition`,
  )
}

case class CaseBlock1(x1: Option[CaseClauses], x2: DefaultClause, x3: Option[CaseClauses], parserParams: List[Boolean], span: Span) extends CaseBlock {
  x1.foreach((m) => m.parent = Some(this))
  x2.parent = Some(this)
  x3.foreach((m) => m.parent = Some(this))
  val idx: Int = 1
  override def toString: String = {
    s"{ ${x1.getOrElse("")} $x2 ${x3.getOrElse("")} }"
  }
  val k: Int = d(x3, d(x2, d(x1, 0)))
  val fullList: List[(String, Value)] = l("Option[CaseClauses]1", x3, l("DefaultClause", x2, l("Option[CaseClauses]0", x1, Nil))).reverse
  val info: ASTInfo = CaseBlock1
}
object CaseBlock1 extends ASTInfo {
  val maxK: Int = 3
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames3" -> `AL::CaseBlock[1,3].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations3" -> `AL::CaseBlock[1,3].LexicallyScopedDeclarations`,
    "VarDeclaredNames3" -> `AL::CaseBlock[1,3].VarDeclaredNames`,
    "VarScopedDeclarations3" -> `AL::CaseBlock[1,3].VarScopedDeclarations`,
    "ContainsDuplicateLabels3" -> `AL::CaseBlock[1,3].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget3" -> `AL::CaseBlock[1,3].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget3" -> `AL::CaseBlock[1,3].ContainsUndefinedContinueTarget`,
    "CaseBlockEvaluation3" -> `AL::CaseBlock[1,3].CaseBlockEvaluation`,
    "HasCallInTailPosition3" -> `AL::CaseBlock[1,3].HasCallInTailPosition`,
  )
}
