package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ImportSpecifier extends AST {
  val kind: String = "ImportSpecifier"
}
object ImportSpecifier extends ASTHelper {
  def apply(v: JsValue): ImportSpecifier = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ImportSpecifier0(ImportedBinding(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      ImportSpecifier1(lex("IdentifierName", x0), ImportedBinding(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class ImportSpecifier0(x0: ImportedBinding, parserParams: List[Boolean], span: Span) extends ImportSpecifier {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ImportedBinding", x0, Nil).reverse
  val info: ASTInfo = ImportSpecifier0
}
object ImportSpecifier0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ImportEntriesForModule0" -> `AL::ImportSpecifier[0,0].ImportEntriesForModule`,
  )
}

case class ImportSpecifier1(x0: Lexical, x2: ImportedBinding, parserParams: List[Boolean], span: Span) extends ImportSpecifier {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 as $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("ImportedBinding", x2, l("Lexical", x0, Nil)).reverse
  val info: ASTInfo = ImportSpecifier1
}
object ImportSpecifier1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ImportSpecifier[1,0].BoundNames`,
    "ImportEntriesForModule0" -> `AL::ImportSpecifier[1,0].ImportEntriesForModule`,
  )
}
