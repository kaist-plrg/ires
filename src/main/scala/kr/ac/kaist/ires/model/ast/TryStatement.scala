package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait TryStatement extends AST {
  val kind: String = "TryStatement"
}
object TryStatement extends ASTHelper {
  def apply(v: JsValue): TryStatement = v match {
    case JsSeq(JsInt(0), JsSeq(x1, x2), JsBoolSeq(params), JsSpan(span)) =>
      TryStatement0(Block(x1), Catch(x2), params, span)
    case JsSeq(JsInt(1), JsSeq(x1, x2), JsBoolSeq(params), JsSpan(span)) =>
      TryStatement1(Block(x1), Finally(x2), params, span)
    case JsSeq(JsInt(2), JsSeq(x1, x2, x3), JsBoolSeq(params), JsSpan(span)) =>
      TryStatement2(Block(x1), Catch(x2), Finally(x3), params, span)
    case _ => throw InvalidAST
  }
}

case class TryStatement0(x1: Block, x2: Catch, parserParams: List[Boolean], span: Span) extends TryStatement {
  x1.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"try $x1 $x2"
  }
  val k: Int = d(x2, d(x1, 0))
  val fullList: List[(String, Value)] = l("Catch", x2, l("Block", x1, Nil)).reverse
  val info: ASTInfo = TryStatement0
}
object TryStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::TryStatement[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::TryStatement[0,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::TryStatement[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::TryStatement[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::TryStatement[0,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::TryStatement[0,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::TryStatement[0,0].HasCallInTailPosition`,
  )
}

case class TryStatement1(x1: Block, x2: Finally, parserParams: List[Boolean], span: Span) extends TryStatement {
  x1.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"try $x1 $x2"
  }
  val k: Int = d(x2, d(x1, 0))
  val fullList: List[(String, Value)] = l("Finally", x2, l("Block", x1, Nil)).reverse
  val info: ASTInfo = TryStatement1
}
object TryStatement1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::TryStatement[1,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::TryStatement[1,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::TryStatement[1,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::TryStatement[1,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::TryStatement[1,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::TryStatement[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::TryStatement[1,0].HasCallInTailPosition`,
  )
}

case class TryStatement2(x1: Block, x2: Catch, x3: Finally, parserParams: List[Boolean], span: Span) extends TryStatement {
  x1.parent = Some(this)
  x2.parent = Some(this)
  x3.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"try $x1 $x2 $x3"
  }
  val k: Int = d(x3, d(x2, d(x1, 0)))
  val fullList: List[(String, Value)] = l("Finally", x3, l("Catch", x2, l("Block", x1, Nil))).reverse
  val info: ASTInfo = TryStatement2
}
object TryStatement2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::TryStatement[2,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::TryStatement[2,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::TryStatement[2,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::TryStatement[2,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::TryStatement[2,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::TryStatement[2,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::TryStatement[2,0].HasCallInTailPosition`,
  )
}
