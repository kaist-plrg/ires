package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ExportSpecifier extends AST {
  val kind: String = "ExportSpecifier"
}
object ExportSpecifier extends ASTHelper {
  def apply(v: JsValue): ExportSpecifier = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ExportSpecifier0(lex("IdentifierName", x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      ExportSpecifier1(lex("IdentifierName", x0), lex("IdentifierName", x2), params, span)
    case _ => throw InvalidAST
  }
}

case class ExportSpecifier0(x0: Lexical, parserParams: List[Boolean], span: Span) extends ExportSpecifier {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Lexical", x0, Nil).reverse
  val info: ASTInfo = ExportSpecifier0
}
object ExportSpecifier0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ExportedBindings0" -> `AL::ExportSpecifier[0,0].ExportedBindings`,
    "ExportedNames0" -> `AL::ExportSpecifier[0,0].ExportedNames`,
    "ExportEntriesForModule0" -> `AL::ExportSpecifier[0,0].ExportEntriesForModule`,
    "ReferencedBindings0" -> `AL::ExportSpecifier[0,0].ReferencedBindings`,
  )
}

case class ExportSpecifier1(x0: Lexical, x2: Lexical, parserParams: List[Boolean], span: Span) extends ExportSpecifier {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 as $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("Lexical1", x2, l("Lexical0", x0, Nil)).reverse
  val info: ASTInfo = ExportSpecifier1
}
object ExportSpecifier1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ExportedBindings0" -> `AL::ExportSpecifier[1,0].ExportedBindings`,
    "ExportedNames0" -> `AL::ExportSpecifier[1,0].ExportedNames`,
    "ExportEntriesForModule0" -> `AL::ExportSpecifier[1,0].ExportEntriesForModule`,
    "ReferencedBindings0" -> `AL::ExportSpecifier[1,0].ReferencedBindings`,
  )
}
