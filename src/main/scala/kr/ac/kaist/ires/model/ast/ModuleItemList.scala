package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ModuleItemList extends AST {
  val kind: String = "ModuleItemList"
}
object ModuleItemList extends ASTHelper {
  def apply(v: JsValue): ModuleItemList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ModuleItemList0(ModuleItem(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      ModuleItemList1(ModuleItemList(x0), ModuleItem(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class ModuleItemList0(x0: ModuleItem, parserParams: List[Boolean], span: Span) extends ModuleItemList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ModuleItem", x0, Nil).reverse
  val info: ASTInfo = ModuleItemList0
}
object ModuleItemList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ModuleRequests0" -> `AL::ModuleItemList[0,0].ModuleRequests`,
  )
}

case class ModuleItemList1(x0: ModuleItemList, x1: ModuleItem, parserParams: List[Boolean], span: Span) extends ModuleItemList {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("ModuleItem", x1, l("ModuleItemList", x0, Nil)).reverse
  val info: ASTInfo = ModuleItemList1
}
object ModuleItemList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::ModuleItemList[1,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::ModuleItemList[1,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::ModuleItemList[1,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ModuleItemList[1,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::ModuleItemList[1,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::ModuleItemList[1,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::ModuleItemList[1,0].ContainsUndefinedContinueTarget`,
    "ModuleRequests0" -> `AL::ModuleItemList[1,0].ModuleRequests`,
    "Evaluation0" -> `AL::ModuleItemList[1,0].Evaluation`,
    "ImportEntries0" -> `AL::ModuleItemList[1,0].ImportEntries`,
    "ExportedBindings0" -> `AL::ModuleItemList[1,0].ExportedBindings`,
    "ExportedNames0" -> `AL::ModuleItemList[1,0].ExportedNames`,
    "ExportEntries0" -> `AL::ModuleItemList[1,0].ExportEntries`,
  )
}
