package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait FunctionStatementList extends AST {
  val kind: String = "FunctionStatementList"
}
object FunctionStatementList extends ASTHelper {
  def apply(v: JsValue): FunctionStatementList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      FunctionStatementList0(opt(x0, StatementList.apply), params)
    case _ => throw InvalidAST
  }
}

case class FunctionStatementList0(x0: Option[StatementList], parserParams: List[Boolean]) extends FunctionStatementList {
  x0.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"${x0.getOrElse("")}"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Option[StatementList]", x0, Nil).reverse
  val info: ASTInfo = FunctionStatementList0
}
object FunctionStatementList0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::FunctionStatementList[0,0].LexicallyDeclaredNames`,
    "LexicallyDeclaredNames1" -> `AL::FunctionStatementList[0,1].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::FunctionStatementList[0,0].LexicallyScopedDeclarations`,
    "LexicallyScopedDeclarations1" -> `AL::FunctionStatementList[0,1].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::FunctionStatementList[0,0].VarDeclaredNames`,
    "VarDeclaredNames1" -> `AL::FunctionStatementList[0,1].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::FunctionStatementList[0,0].VarScopedDeclarations`,
    "VarScopedDeclarations1" -> `AL::FunctionStatementList[0,1].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::FunctionStatementList[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::FunctionStatementList[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::FunctionStatementList[0,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::FunctionStatementList[0,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::FunctionStatementList[0,0].HasCallInTailPosition`,
  )
}
