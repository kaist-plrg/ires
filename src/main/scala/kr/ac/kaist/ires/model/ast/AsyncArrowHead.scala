package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AsyncArrowHead extends AST {
  val kind: String = "AsyncArrowHead"
}
object AsyncArrowHead extends ASTHelper {
  def apply(v: JsValue): AsyncArrowHead = v match {
    case JsSeq(JsInt(0), JsSeq(x2), JsBoolSeq(params), JsSpan(span)) =>
      AsyncArrowHead0(ArrowFormalParameters(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class AsyncArrowHead0(x2: ArrowFormalParameters, parserParams: List[Boolean], span: Span) extends AsyncArrowHead {
  x2.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"async $x2"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("ArrowFormalParameters", x2, Nil).reverse
  val info: ASTInfo = AsyncArrowHead0
}
object AsyncArrowHead0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
