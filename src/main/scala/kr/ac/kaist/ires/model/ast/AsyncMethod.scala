package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AsyncMethod extends AST {
  val kind: String = "AsyncMethod"
}

case class AsyncMethod0(x2: PropertyName, x4: UniqueFormalParameters, x7: AsyncFunctionBody, parserParams: List[Boolean]) extends AsyncMethod {
  x2.parent = Some(this)
  x4.parent = Some(this)
  x7.parent = Some(this)
  val name: String = "AsyncMethod0"
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
