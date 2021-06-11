package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ImportedBinding extends AST {
  val kind: String = "ImportedBinding"
}
object ImportedBinding extends ASTHelper {
  def apply(v: JsValue): ImportedBinding = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ImportedBinding0(BindingIdentifier(x0), params)
    case _ => throw InvalidAST
  }
}

case class ImportedBinding0(x0: BindingIdentifier, parserParams: List[Boolean]) extends ImportedBinding {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingIdentifier", x0, Nil).reverse
  val info: ASTInfo = ImportedBinding0
}
object ImportedBinding0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
