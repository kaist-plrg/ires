package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait GeneratorMethod extends AST {
  val kind: String = "GeneratorMethod"
}
object GeneratorMethod extends ASTHelper {
  def apply(v: JsValue): GeneratorMethod = v match {
    case JsSeq(JsInt(0), JsSeq(x1, x3, x6), JsBoolSeq(params), JsSpan(span)) =>
      GeneratorMethod0(PropertyName(x1), UniqueFormalParameters(x3), GeneratorBody(x6), params)
    case _ => throw InvalidAST
  }
}

case class GeneratorMethod0(x1: PropertyName, x3: UniqueFormalParameters, x6: GeneratorBody, parserParams: List[Boolean]) extends GeneratorMethod {
  x1.parent = Some(this)
  x3.parent = Some(this)
  x6.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"* $x1 ( $x3 ) { $x6 }"
  }
  val k: Int = d(x6, d(x3, d(x1, 0)))
  val fullList: List[(String, Value)] = l("GeneratorBody", x6, l("UniqueFormalParameters", x3, l("PropertyName", x1, Nil))).reverse
  val info: ASTInfo = GeneratorMethod0
}
object GeneratorMethod0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ComputedPropertyContains0" -> `AL::GeneratorMethod[0,0].ComputedPropertyContains`,
    "PropName0" -> `AL::GeneratorMethod[0,0].PropName`,
    "PropertyDefinitionEvaluation0" -> `AL::GeneratorMethod[0,0].PropertyDefinitionEvaluation`,
    "HasDirectSuper0" -> `AL::GeneratorMethod[0,0].HasDirectSuper`,
    "MethodDefinitionEvaluation0" -> `AL::GeneratorMethod[0,0].MethodDefinitionEvaluation`,
    "EarlyErrors0" -> `AL::GeneratorMethod[0,0].EarlyErrors`,
  )
}
