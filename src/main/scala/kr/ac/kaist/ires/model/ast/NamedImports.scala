package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait NamedImports extends AST {
  val kind: String = "NamedImports"
}
object NamedImports extends ASTHelper {
  def apply(v: JsValue): NamedImports = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      NamedImports0(params)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      NamedImports1(ImportsList(x1), params)
    case JsSeq(JsInt(2), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      NamedImports2(ImportsList(x1), params)
    case _ => throw InvalidAST
  }
}

case class NamedImports0(parserParams: List[Boolean]) extends NamedImports {
  val idx: Int = 0
  override def toString: String = {
    s"{ }"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = NamedImports0
}
object NamedImports0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::NamedImports[0,0].BoundNames`,
    "ImportEntriesForModule0" -> `AL::NamedImports[0,0].ImportEntriesForModule`,
  )
}

case class NamedImports1(x1: ImportsList, parserParams: List[Boolean]) extends NamedImports {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"{ $x1 }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ImportsList", x1, Nil).reverse
  val info: ASTInfo = NamedImports1
}
object NamedImports1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class NamedImports2(x1: ImportsList, parserParams: List[Boolean]) extends NamedImports {
  x1.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"{ $x1 , }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ImportsList", x1, Nil).reverse
  val info: ASTInfo = NamedImports2
}
object NamedImports2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
