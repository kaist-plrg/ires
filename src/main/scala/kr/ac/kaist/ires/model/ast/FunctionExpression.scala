package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.{ AST, ASTInfo, Lexical }
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait FunctionExpression extends AST {
  val kind: String = "FunctionExpression"
}
case class FunctionExpression0(x1: Option[BindingIdentifier], x3: FormalParameters, x6: FunctionBody, parserParams: List[Boolean]) extends FunctionExpression {
  x1.foreach((m) => m.parent = Some(this))
  x3.parent = Some(this)
  x6.parent = Some(this)
  val name: String = "FunctionExpression0"
  override def toString: String = {
    s"function ${x1.getOrElse("")} ( $x3 ) { $x6 }"
  }
  val k: Int = d(x6, d(x3, d(x1, 0)))
  val fullList: List[(String, Value)] = l("FunctionBody", x6, l("FormalParameters", x3, l("Option[BindingIdentifier]", x1, Nil))).reverse
  val info: ASTInfo = FunctionExpression0
}
object FunctionExpression0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Func] = Map(
    "Contains1" -> FunctionExpression0Contains1.func,
    "Evaluation0" -> FunctionExpression0Evaluation0.func,
    "Evaluation1" -> FunctionExpression0Evaluation1.func,
    "HasName0" -> FunctionExpression0HasName0.func,
    "HasName1" -> FunctionExpression0HasName1.func,
    "IsFunctionDefinition0" -> FunctionExpression0IsFunctionDefinition0.func,
    "IsFunctionDefinition1" -> FunctionExpression0IsFunctionDefinition1.func,
    "NamedEvaluation0" -> FunctionExpression0NamedEvaluation0.func
  )
}
