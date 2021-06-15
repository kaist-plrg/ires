package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait NamedExports extends AST {
  val kind: String = "NamedExports"
}
object NamedExports extends ASTHelper {
  def apply(v: JsValue): NamedExports = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      NamedExports0(params, span)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      NamedExports1(ExportsList(x1), params, span)
    case JsSeq(JsInt(2), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      NamedExports2(ExportsList(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class NamedExports0(parserParams: List[Boolean], span: Span) extends NamedExports {
  val idx: Int = 0
  override def toString: String = {
    s"{ }"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = NamedExports0
}
object NamedExports0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ExportedBindings0" -> `AL::NamedExports[0,0].ExportedBindings`,
    "ExportedNames0" -> `AL::NamedExports[0,0].ExportedNames`,
    "ExportEntriesForModule0" -> `AL::NamedExports[0,0].ExportEntriesForModule`,
    "ReferencedBindings0" -> `AL::NamedExports[0,0].ReferencedBindings`,
  )
}

case class NamedExports1(x1: ExportsList, parserParams: List[Boolean], span: Span) extends NamedExports {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"{ $x1 }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ExportsList", x1, Nil).reverse
  val info: ASTInfo = NamedExports1
}
object NamedExports1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class NamedExports2(x1: ExportsList, parserParams: List[Boolean], span: Span) extends NamedExports {
  x1.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"{ $x1 , }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ExportsList", x1, Nil).reverse
  val info: ASTInfo = NamedExports2
}
object NamedExports2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
