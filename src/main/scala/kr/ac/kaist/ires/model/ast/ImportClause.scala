package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ImportClause extends AST {
  val kind: String = "ImportClause"
}
object ImportClause extends ASTHelper {
  def apply(v: JsValue): ImportClause = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ImportClause0(ImportedDefaultBinding(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ImportClause1(NameSpaceImport(x0), params, span)
    case JsSeq(JsInt(2), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ImportClause2(NamedImports(x0), params, span)
    case JsSeq(JsInt(3), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      ImportClause3(ImportedDefaultBinding(x0), NameSpaceImport(x2), params, span)
    case JsSeq(JsInt(4), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      ImportClause4(ImportedDefaultBinding(x0), NamedImports(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class ImportClause0(x0: ImportedDefaultBinding, parserParams: List[Boolean], span: Span) extends ImportClause {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ImportedDefaultBinding", x0, Nil).reverse
  val info: ASTInfo = ImportClause0
}
object ImportClause0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class ImportClause1(x0: NameSpaceImport, parserParams: List[Boolean], span: Span) extends ImportClause {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("NameSpaceImport", x0, Nil).reverse
  val info: ASTInfo = ImportClause1
}
object ImportClause1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class ImportClause2(x0: NamedImports, parserParams: List[Boolean], span: Span) extends ImportClause {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("NamedImports", x0, Nil).reverse
  val info: ASTInfo = ImportClause2
}
object ImportClause2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class ImportClause3(x0: ImportedDefaultBinding, x2: NameSpaceImport, parserParams: List[Boolean], span: Span) extends ImportClause {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("NameSpaceImport", x2, l("ImportedDefaultBinding", x0, Nil)).reverse
  val info: ASTInfo = ImportClause3
}
object ImportClause3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ImportClause[3,0].BoundNames`,
    "ImportEntriesForModule0" -> `AL::ImportClause[3,0].ImportEntriesForModule`,
  )
}

case class ImportClause4(x0: ImportedDefaultBinding, x2: NamedImports, parserParams: List[Boolean], span: Span) extends ImportClause {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("NamedImports", x2, l("ImportedDefaultBinding", x0, Nil)).reverse
  val info: ASTInfo = ImportClause4
}
object ImportClause4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ImportClause[4,0].BoundNames`,
    "ImportEntriesForModule0" -> `AL::ImportClause[4,0].ImportEntriesForModule`,
  )
}
