package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AsyncFunctionExpression extends AST {
  val kind: String = "AsyncFunctionExpression"
}

case class AsyncFunctionExpression0(x3: Option[BindingIdentifier], x5: FormalParameters, x8: AsyncFunctionBody, parserParams: List[Boolean]) extends AsyncFunctionExpression {
  x3.foreach((m) => m.parent = Some(this))
  x5.parent = Some(this)
  x8.parent = Some(this)
  val name: String = "AsyncFunctionExpression0"
  override def toString: String = {
    s"async function ${x3.getOrElse("")} ( $x5 ) { $x8 }"
  }
  val k: Int = d(x8, d(x5, d(x3, 0)))
  val fullList: List[(String, Value)] = l("AsyncFunctionBody", x8, l("FormalParameters", x5, l("Option[BindingIdentifier]", x3, Nil))).reverse
  val info: ASTInfo = AsyncFunctionExpression0
}
object AsyncFunctionExpression0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "HasName0" -> `AL::AsyncFunctionExpression[0,0].HasName`,
    "HasName1" -> `AL::AsyncFunctionExpression[0,1].HasName`,
    "IsFunctionDefinition1" -> `AL::AsyncFunctionExpression[0,1].IsFunctionDefinition`,
    "NamedEvaluation0" -> `AL::AsyncFunctionExpression[0,0].NamedEvaluation`,
    "Contains1" -> `AL::AsyncFunctionExpression[0,1].Contains`,
    "InstantiateAsyncFunctionExpression0" -> `AL::AsyncFunctionExpression[0,0].InstantiateAsyncFunctionExpression`,
    "InstantiateAsyncFunctionExpression1" -> `AL::AsyncFunctionExpression[0,1].InstantiateAsyncFunctionExpression`,
    "Evaluation1" -> `AL::AsyncFunctionExpression[0,1].Evaluation`,
    "EarlyErrors1" -> `AL::AsyncFunctionExpression[0,1].EarlyErrors`,
  )
}
