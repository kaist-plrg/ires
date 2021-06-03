package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AsyncGeneratorMethod extends AST {
  val kind: String = "AsyncGeneratorMethod"
}

case class AsyncGeneratorMethod0(x3: PropertyName, x5: UniqueFormalParameters, x8: AsyncGeneratorBody, parserParams: List[Boolean]) extends AsyncGeneratorMethod {
  x3.parent = Some(this)
  x5.parent = Some(this)
  x8.parent = Some(this)
  val name: String = "AsyncGeneratorMethod0"
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
