package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait FunctionBody extends AST {
  val kind: String = "FunctionBody"
}

case class FunctionBody0(x0: FunctionStatementList, parserParams: List[Boolean]) extends FunctionBody {
  x0.parent = Some(this)
  val name: String = "FunctionBody0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FunctionStatementList", x0, Nil).reverse
  val info: ASTInfo = FunctionBody0
}
object FunctionBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "EvaluateBody0" -> `AL::FunctionBody[0,0].EvaluateBody`,
    "FunctionBodyContainsUseStrict0" -> `AL::FunctionBody[0,0].FunctionBodyContainsUseStrict`,
    "EvaluateFunctionBody0" -> `AL::FunctionBody[0,0].EvaluateFunctionBody`,
    "EarlyErrors0" -> `AL::FunctionBody[0,0].EarlyErrors`,
  )
}
