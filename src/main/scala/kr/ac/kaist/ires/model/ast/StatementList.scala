package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait StatementList extends AST {
  val kind: String = "StatementList"
}
object StatementList extends ASTHelper {
  def apply(v: JsValue): StatementList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      StatementList0(StatementListItem(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      StatementList1(StatementList(x0), StatementListItem(x1), params)
    case _ => throw InvalidAST
  }
}

case class StatementList0(x0: StatementListItem, parserParams: List[Boolean]) extends StatementList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("StatementListItem", x0, Nil).reverse
  val info: ASTInfo = StatementList0
}
object StatementList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class StatementList1(x0: StatementList, x1: StatementListItem, parserParams: List[Boolean]) extends StatementList {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("StatementListItem", x1, l("StatementList", x0, Nil)).reverse
  val info: ASTInfo = StatementList1
}
object StatementList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::StatementList[1,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::StatementList[1,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::StatementList[1,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::StatementList[1,0].VarScopedDeclarations`,
    "TopLevelLexicallyDeclaredNames0" -> `AL::StatementList[1,0].TopLevelLexicallyDeclaredNames`,
    "TopLevelLexicallyScopedDeclarations0" -> `AL::StatementList[1,0].TopLevelLexicallyScopedDeclarations`,
    "TopLevelVarDeclaredNames0" -> `AL::StatementList[1,0].TopLevelVarDeclaredNames`,
    "TopLevelVarScopedDeclarations0" -> `AL::StatementList[1,0].TopLevelVarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::StatementList[1,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::StatementList[1,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::StatementList[1,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::StatementList[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::StatementList[1,0].HasCallInTailPosition`,
  )
}
