package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ImportsList extends AST {
  val kind: String = "ImportsList"
}
object ImportsList extends ASTHelper {
  def apply(v: JsValue): ImportsList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ImportsList0(ImportSpecifier(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      ImportsList1(ImportsList(x0), ImportSpecifier(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class ImportsList0(x0: ImportSpecifier, parserParams: List[Boolean], span: Span) extends ImportsList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ImportSpecifier", x0, Nil).reverse
  val info: ASTInfo = ImportsList0
}
object ImportsList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class ImportsList1(x0: ImportsList, x2: ImportSpecifier, parserParams: List[Boolean], span: Span) extends ImportsList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("ImportSpecifier", x2, l("ImportsList", x0, Nil)).reverse
  val info: ASTInfo = ImportsList1
}
object ImportsList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ImportsList[1,0].BoundNames`,
    "ImportEntriesForModule0" -> `AL::ImportsList[1,0].ImportEntriesForModule`,
  )
}
