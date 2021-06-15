package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ImportedDefaultBinding extends AST {
  val kind: String = "ImportedDefaultBinding"
}
object ImportedDefaultBinding extends ASTHelper {
  def apply(v: JsValue): ImportedDefaultBinding = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ImportedDefaultBinding0(ImportedBinding(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class ImportedDefaultBinding0(x0: ImportedBinding, parserParams: List[Boolean], span: Span) extends ImportedDefaultBinding {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ImportedBinding", x0, Nil).reverse
  val info: ASTInfo = ImportedDefaultBinding0
}
object ImportedDefaultBinding0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ImportEntriesForModule0" -> `AL::ImportedDefaultBinding[0,0].ImportEntriesForModule`,
  )
}
