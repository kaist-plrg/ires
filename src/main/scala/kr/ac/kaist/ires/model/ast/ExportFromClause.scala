package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ExportFromClause extends AST {
  val kind: String = "ExportFromClause"
}
object ExportFromClause extends ASTHelper {
  def apply(v: JsValue): ExportFromClause = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      ExportFromClause0(params, span)
    case JsSeq(JsInt(1), JsSeq(x2), JsBoolSeq(params), JsSpan(span)) =>
      ExportFromClause1(lex("IdentifierName", x2), params, span)
    case JsSeq(JsInt(2), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ExportFromClause2(NamedExports(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class ExportFromClause0(parserParams: List[Boolean], span: Span) extends ExportFromClause {
  val idx: Int = 0
  override def toString: String = {
    s"*"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = ExportFromClause0
}
object ExportFromClause0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ExportedNames0" -> `AL::ExportFromClause[0,0].ExportedNames`,
    "ExportEntriesForModule0" -> `AL::ExportFromClause[0,0].ExportEntriesForModule`,
  )
}

case class ExportFromClause1(x2: Lexical, parserParams: List[Boolean], span: Span) extends ExportFromClause {
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"* as $x2"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("Lexical", x2, Nil).reverse
  val info: ASTInfo = ExportFromClause1
}
object ExportFromClause1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ExportedNames0" -> `AL::ExportFromClause[1,0].ExportedNames`,
    "ExportEntriesForModule0" -> `AL::ExportFromClause[1,0].ExportEntriesForModule`,
  )
}

case class ExportFromClause2(x0: NamedExports, parserParams: List[Boolean], span: Span) extends ExportFromClause {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("NamedExports", x0, Nil).reverse
  val info: ASTInfo = ExportFromClause2
}
object ExportFromClause2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ExportedNames0" -> `AL::ExportFromClause[2,0].ExportedNames`,
  )
}
