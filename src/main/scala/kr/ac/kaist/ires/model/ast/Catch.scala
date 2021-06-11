package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait Catch extends AST {
  val kind: String = "Catch"
}
object Catch extends ASTHelper {
  def apply(v: JsValue): Catch = v match {
    case JsSeq(JsInt(0), JsSeq(x2, x4), JsBoolSeq(params), JsSpan(span)) =>
      Catch0(CatchParameter(x2), Block(x4), params, span)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      Catch1(Block(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class Catch0(x2: CatchParameter, x4: Block, parserParams: List[Boolean], span: Span) extends Catch {
  x2.parent = Some(this)
  x4.parent = Some(this)
  val idx: Int = 0
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

case class Catch1(x1: Block, parserParams: List[Boolean], span: Span) extends Catch {
  x1.parent = Some(this)
  val idx: Int = 1
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
