package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait CoalesceExpressionHead extends AST {
  val kind: String = "CoalesceExpressionHead"
}
object CoalesceExpressionHead extends ASTHelper {
  def apply(v: JsValue): CoalesceExpressionHead = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      CoalesceExpressionHead0(CoalesceExpression(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      CoalesceExpressionHead1(BitwiseORExpression(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class CoalesceExpressionHead0(x0: CoalesceExpression, parserParams: List[Boolean], span: Span) extends CoalesceExpressionHead {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("CoalesceExpression", x0, Nil).reverse
  val info: ASTInfo = CoalesceExpressionHead0
}
object CoalesceExpressionHead0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class CoalesceExpressionHead1(x0: BitwiseORExpression, parserParams: List[Boolean], span: Span) extends CoalesceExpressionHead {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BitwiseORExpression", x0, Nil).reverse
  val info: ASTInfo = CoalesceExpressionHead1
}
object CoalesceExpressionHead1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
