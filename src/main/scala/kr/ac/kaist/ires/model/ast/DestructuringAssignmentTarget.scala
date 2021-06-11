package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait DestructuringAssignmentTarget extends AST {
  val kind: String = "DestructuringAssignmentTarget"
}
object DestructuringAssignmentTarget extends ASTHelper {
  def apply(v: JsValue): DestructuringAssignmentTarget = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      DestructuringAssignmentTarget0(LeftHandSideExpression(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class DestructuringAssignmentTarget0(x0: LeftHandSideExpression, parserParams: List[Boolean], span: Span) extends DestructuringAssignmentTarget {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("LeftHandSideExpression", x0, Nil).reverse
  val info: ASTInfo = DestructuringAssignmentTarget0
}
object DestructuringAssignmentTarget0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
