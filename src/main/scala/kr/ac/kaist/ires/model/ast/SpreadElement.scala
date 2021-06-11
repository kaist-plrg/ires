package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait SpreadElement extends AST {
  val kind: String = "SpreadElement"
}
object SpreadElement extends ASTHelper {
  def apply(v: JsValue): SpreadElement = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      SpreadElement0(AssignmentExpression(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class SpreadElement0(x1: AssignmentExpression, parserParams: List[Boolean], span: Span) extends SpreadElement {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"... $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("AssignmentExpression", x1, Nil).reverse
  val info: ASTInfo = SpreadElement0
}
object SpreadElement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ArrayAccumulation0" -> `AL::SpreadElement[0,0].ArrayAccumulation`,
  )
}
