package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AsyncArrowFunction extends AST {
  val kind: String = "AsyncArrowFunction"
}
object AsyncArrowFunction extends ASTHelper {
  def apply(v: JsValue): AsyncArrowFunction = v match {
    case JsSeq(JsInt(0), JsSeq(x2, x5), JsBoolSeq(params), JsSpan(span)) =>
      AsyncArrowFunction0(AsyncArrowBindingIdentifier(x2), AsyncConciseBody(x5), params)
    case JsSeq(JsInt(1), JsSeq(x0, x3), JsBoolSeq(params), JsSpan(span)) =>
      AsyncArrowFunction1(CoverCallExpressionAndAsyncArrowHead(x0), AsyncConciseBody(x3), params)
    case _ => throw InvalidAST
  }
}

case class AsyncArrowFunction0(x2: AsyncArrowBindingIdentifier, x5: AsyncConciseBody, parserParams: List[Boolean]) extends AsyncArrowFunction {
  x2.parent = Some(this)
  x5.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"async $x2 => $x5"
  }
  val k: Int = d(x5, d(x2, 0))
  val fullList: List[(String, Value)] = l("AsyncConciseBody", x5, l("AsyncArrowBindingIdentifier", x2, Nil)).reverse
  val info: ASTInfo = AsyncArrowFunction0
}
object AsyncArrowFunction0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "HasName0" -> `AL::AsyncArrowFunction[0,0].HasName`,
    "NamedEvaluation0" -> `AL::AsyncArrowFunction[0,0].NamedEvaluation`,
    "Contains0" -> `AL::AsyncArrowFunction[0,0].Contains`,
    "InstantiateAsyncArrowFunctionExpression0" -> `AL::AsyncArrowFunction[0,0].InstantiateAsyncArrowFunctionExpression`,
    "Evaluation0" -> `AL::AsyncArrowFunction[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::AsyncArrowFunction[0,0].EarlyErrors`,
  )
}

case class AsyncArrowFunction1(x0: CoverCallExpressionAndAsyncArrowHead, x3: AsyncConciseBody, parserParams: List[Boolean]) extends AsyncArrowFunction {
  x0.parent = Some(this)
  x3.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 => $x3"
  }
  val k: Int = d(x3, d(x0, 0))
  val fullList: List[(String, Value)] = l("AsyncConciseBody", x3, l("CoverCallExpressionAndAsyncArrowHead", x0, Nil)).reverse
  val info: ASTInfo = AsyncArrowFunction1
}
object AsyncArrowFunction1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "HasName0" -> `AL::AsyncArrowFunction[1,0].HasName`,
    "NamedEvaluation0" -> `AL::AsyncArrowFunction[1,0].NamedEvaluation`,
    "Contains0" -> `AL::AsyncArrowFunction[1,0].Contains`,
    "InstantiateAsyncArrowFunctionExpression0" -> `AL::AsyncArrowFunction[1,0].InstantiateAsyncArrowFunctionExpression`,
    "Evaluation0" -> `AL::AsyncArrowFunction[1,0].Evaluation`,
    "EarlyErrors0" -> `AL::AsyncArrowFunction[1,0].EarlyErrors`,
  )
}
