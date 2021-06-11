package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait CoverCallExpressionAndAsyncArrowHead extends AST {
  val kind: String = "CoverCallExpressionAndAsyncArrowHead"
}
object CoverCallExpressionAndAsyncArrowHead extends ASTHelper {
  def apply(v: JsValue): CoverCallExpressionAndAsyncArrowHead = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      CoverCallExpressionAndAsyncArrowHead0(MemberExpression(x0), Arguments(x1), params)
    case _ => throw InvalidAST
  }
}

case class CoverCallExpressionAndAsyncArrowHead0(x0: MemberExpression, x1: Arguments, parserParams: List[Boolean]) extends CoverCallExpressionAndAsyncArrowHead {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Arguments", x1, l("MemberExpression", x0, Nil)).reverse
  val info: ASTInfo = CoverCallExpressionAndAsyncArrowHead0
}
object CoverCallExpressionAndAsyncArrowHead0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::CoverCallExpressionAndAsyncArrowHead[0,0].BoundNames`,
    "CoveredCallExpression0" -> `AL::CoverCallExpressionAndAsyncArrowHead[0,0].CoveredCallExpression`,
    "IsSimpleParameterList0" -> `AL::CoverCallExpressionAndAsyncArrowHead[0,0].IsSimpleParameterList`,
    "CoveredAsyncArrowHead0" -> `AL::CoverCallExpressionAndAsyncArrowHead[0,0].CoveredAsyncArrowHead`,
  )
}
