package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AsyncMethod extends AST {
  val kind: String = "AsyncMethod"
}
object AsyncMethod extends ASTHelper {
  def apply(v: JsValue): AsyncMethod = v match {
    case JsSeq(JsInt(0), JsSeq(x2, x4, x7), JsBoolSeq(params), JsSpan(span)) =>
      AsyncMethod0(PropertyName(x2), UniqueFormalParameters(x4), AsyncFunctionBody(x7), params, span)
    case _ => throw InvalidAST
  }
}

case class AsyncMethod0(x2: PropertyName, x4: UniqueFormalParameters, x7: AsyncFunctionBody, parserParams: List[Boolean], span: Span) extends AsyncMethod {
  x2.parent = Some(this)
  x4.parent = Some(this)
  x7.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"async $x2 ( $x4 ) { $x7 }"
  }
  val k: Int = d(x7, d(x4, d(x2, 0)))
  val fullList: List[(String, Value)] = l("AsyncFunctionBody", x7, l("UniqueFormalParameters", x4, l("PropertyName", x2, Nil))).reverse
  val info: ASTInfo = AsyncMethod0
}
object AsyncMethod0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ComputedPropertyContains0" -> `AL::AsyncMethod[0,0].ComputedPropertyContains`,
    "PropName0" -> `AL::AsyncMethod[0,0].PropName`,
    "PropertyDefinitionEvaluation0" -> `AL::AsyncMethod[0,0].PropertyDefinitionEvaluation`,
    "HasDirectSuper0" -> `AL::AsyncMethod[0,0].HasDirectSuper`,
    "MethodDefinitionEvaluation0" -> `AL::AsyncMethod[0,0].MethodDefinitionEvaluation`,
    "EarlyErrors0" -> `AL::AsyncMethod[0,0].EarlyErrors`,
  )
}
