package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait Block extends AST {
  val kind: String = "Block"
}
object Block extends ASTHelper {
  def apply(v: JsValue): Block = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      Block0(opt(x1, StatementList.apply), params, span)
    case _ => throw InvalidAST
  }
}

case class Block0(x1: Option[StatementList], parserParams: List[Boolean], span: Span) extends Block {
  x1.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"{ ${x1.getOrElse("")} }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Option[StatementList]", x1, Nil).reverse
  val info: ASTInfo = Block0
}
object Block0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::Block[0,0].LexicallyDeclaredNames`,
    "VarDeclaredNames0" -> `AL::Block[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::Block[0,0].VarScopedDeclarations`,
    "TopLevelLexicallyScopedDeclarations0" -> `AL::Block[0,0].TopLevelLexicallyScopedDeclarations`,
    "TopLevelVarDeclaredNames0" -> `AL::Block[0,0].TopLevelVarDeclaredNames`,
    "TopLevelVarScopedDeclarations0" -> `AL::Block[0,0].TopLevelVarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::Block[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::Block[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::Block[0,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::Block[0,0].Evaluation`,
    "Evaluation1" -> `AL::Block[0,1].Evaluation`,
    "HasCallInTailPosition0" -> `AL::Block[0,0].HasCallInTailPosition`,
    "EarlyErrors1" -> `AL::Block[0,1].EarlyErrors`,
  )
}
