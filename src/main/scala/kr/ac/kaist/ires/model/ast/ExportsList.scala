package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ExportsList extends AST {
  val kind: String = "ExportsList"
}
object ExportsList extends ASTHelper {
  def apply(v: JsValue): ExportsList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ExportsList0(ExportSpecifier(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      ExportsList1(ExportsList(x0), ExportSpecifier(x2), params)
    case _ => throw InvalidAST
  }
}

case class ExportsList0(x0: ExportSpecifier, parserParams: List[Boolean]) extends ExportsList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ExportSpecifier", x0, Nil).reverse
  val info: ASTInfo = ExportsList0
}
object ExportsList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class ExportsList1(x0: ExportsList, x2: ExportSpecifier, parserParams: List[Boolean]) extends ExportsList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("ExportSpecifier", x2, l("ExportsList", x0, Nil)).reverse
  val info: ASTInfo = ExportsList1
}
object ExportsList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ExportedBindings0" -> `AL::ExportsList[1,0].ExportedBindings`,
    "ExportedNames0" -> `AL::ExportsList[1,0].ExportedNames`,
    "ExportEntriesForModule0" -> `AL::ExportsList[1,0].ExportEntriesForModule`,
    "ReferencedBindings0" -> `AL::ExportsList[1,0].ReferencedBindings`,
  )
}
