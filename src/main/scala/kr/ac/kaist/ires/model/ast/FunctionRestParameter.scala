package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait FunctionRestParameter extends AST {
  val kind: String = "FunctionRestParameter"
}
object FunctionRestParameter extends ASTHelper {
  def apply(v: JsValue): FunctionRestParameter = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      FunctionRestParameter0(BindingRestElement(x0), params)
    case _ => throw InvalidAST
  }
}

case class FunctionRestParameter0(x0: BindingRestElement, parserParams: List[Boolean]) extends FunctionRestParameter {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingRestElement", x0, Nil).reverse
  val info: ASTInfo = FunctionRestParameter0
}
object FunctionRestParameter0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
