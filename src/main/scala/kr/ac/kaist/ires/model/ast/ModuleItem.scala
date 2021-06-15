package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ModuleItem extends AST {
  val kind: String = "ModuleItem"
}
object ModuleItem extends ASTHelper {
  def apply(v: JsValue): ModuleItem = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ModuleItem0(ImportDeclaration(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ModuleItem1(ExportDeclaration(x0), params, span)
    case JsSeq(JsInt(2), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ModuleItem2(StatementListItem(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class ModuleItem0(x0: ImportDeclaration, parserParams: List[Boolean], span: Span) extends ModuleItem {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ImportDeclaration", x0, Nil).reverse
  val info: ASTInfo = ModuleItem0
}
object ModuleItem0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::ModuleItem[0,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::ModuleItem[0,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::ModuleItem[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ModuleItem[0,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::ModuleItem[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::ModuleItem[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::ModuleItem[0,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::ModuleItem[0,0].Evaluation`,
    "ExportedBindings0" -> `AL::ModuleItem[0,0].ExportedBindings`,
    "ExportedNames0" -> `AL::ModuleItem[0,0].ExportedNames`,
    "ExportEntries0" -> `AL::ModuleItem[0,0].ExportEntries`,
    "EarlyErrors0" -> `AL::ModuleItem[0,0].EarlyErrors`,
  )
}

case class ModuleItem1(x0: ExportDeclaration, parserParams: List[Boolean], span: Span) extends ModuleItem {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ExportDeclaration", x0, Nil).reverse
  val info: ASTInfo = ModuleItem1
}
object ModuleItem1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::ModuleItem[1,0].LexicallyDeclaredNames`,
    "VarDeclaredNames0" -> `AL::ModuleItem[1,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ModuleItem[1,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::ModuleItem[1,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::ModuleItem[1,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::ModuleItem[1,0].ContainsUndefinedContinueTarget`,
    "ImportEntries0" -> `AL::ModuleItem[1,0].ImportEntries`,
    "ExportedNames0" -> `AL::ModuleItem[1,0].ExportedNames`,
  )
}

case class ModuleItem2(x0: StatementListItem, parserParams: List[Boolean], span: Span) extends ModuleItem {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("StatementListItem", x0, Nil).reverse
  val info: ASTInfo = ModuleItem2
}
object ModuleItem2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::ModuleItem[2,0].LexicallyDeclaredNames`,
    "ModuleRequests0" -> `AL::ModuleItem[2,0].ModuleRequests`,
    "ImportEntries0" -> `AL::ModuleItem[2,0].ImportEntries`,
    "ExportedBindings0" -> `AL::ModuleItem[2,0].ExportedBindings`,
    "ExportedNames0" -> `AL::ModuleItem[2,0].ExportedNames`,
    "ExportEntries0" -> `AL::ModuleItem[2,0].ExportEntries`,
  )
}
