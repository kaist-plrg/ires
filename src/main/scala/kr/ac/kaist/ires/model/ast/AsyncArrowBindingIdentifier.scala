package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AsyncArrowBindingIdentifier extends AST {
  val kind: String = "AsyncArrowBindingIdentifier"
}

case class AsyncArrowBindingIdentifier0(x0: BindingIdentifier, parserParams: List[Boolean]) extends AsyncArrowBindingIdentifier {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingIdentifier", x0, Nil).reverse
  val info: ASTInfo = AsyncArrowBindingIdentifier0
}
object AsyncArrowBindingIdentifier0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IteratorBindingInitialization0" -> `AL::AsyncArrowBindingIdentifier[0,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::AsyncArrowBindingIdentifier[0,0].ContainsExpression`,
    "IsSimpleParameterList0" -> `AL::AsyncArrowBindingIdentifier[0,0].IsSimpleParameterList`,
    "ExpectedArgumentCount0" -> `AL::AsyncArrowBindingIdentifier[0,0].ExpectedArgumentCount`,
  )
}
