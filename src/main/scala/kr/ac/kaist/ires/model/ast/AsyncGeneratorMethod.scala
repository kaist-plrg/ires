package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AsyncGeneratorMethod extends AST {
  val kind: String = "AsyncGeneratorMethod"
}
object AsyncGeneratorMethod extends ASTHelper {
  def apply(v: JsValue): AsyncGeneratorMethod = v match {
    case JsSeq(JsInt(0), JsSeq(x3, x5, x8), JsBoolSeq(params), JsSpan(span)) =>
      AsyncGeneratorMethod0(PropertyName(x3), UniqueFormalParameters(x5), AsyncGeneratorBody(x8), params, span)
    case _ => throw InvalidAST
  }
}

case class AsyncGeneratorMethod0(x3: PropertyName, x5: UniqueFormalParameters, x8: AsyncGeneratorBody, parserParams: List[Boolean], span: Span) extends AsyncGeneratorMethod {
  x3.parent = Some(this)
  x5.parent = Some(this)
  x8.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"async * $x3 ( $x5 ) { $x8 }"
  }
  val k: Int = d(x8, d(x5, d(x3, 0)))
  val fullList: List[(String, Value)] = l("AsyncGeneratorBody", x8, l("UniqueFormalParameters", x5, l("PropertyName", x3, Nil))).reverse
  val info: ASTInfo = AsyncGeneratorMethod0
}
object AsyncGeneratorMethod0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ComputedPropertyContains0" -> `AL::AsyncGeneratorMethod[0,0].ComputedPropertyContains`,
    "PropName0" -> `AL::AsyncGeneratorMethod[0,0].PropName`,
    "PropertyDefinitionEvaluation0" -> `AL::AsyncGeneratorMethod[0,0].PropertyDefinitionEvaluation`,
    "HasDirectSuper0" -> `AL::AsyncGeneratorMethod[0,0].HasDirectSuper`,
    "MethodDefinitionEvaluation0" -> `AL::AsyncGeneratorMethod[0,0].MethodDefinitionEvaluation`,
    "EarlyErrors0" -> `AL::AsyncGeneratorMethod[0,0].EarlyErrors`,
  )
}
