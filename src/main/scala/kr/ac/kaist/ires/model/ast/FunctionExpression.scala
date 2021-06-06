package kr.ac.kaist.ires.model

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
  val idx: Int = 0
  override def toString: String = {
    s"function ${x1.getOrElse("")} ( $x3 ) { $x6 }"
  }
  val k: Int = d(x6, d(x3, d(x1, 0)))
  val fullList: List[(String, Value)] = l("FunctionBody", x6, l("FormalParameters", x3, l("Option[BindingIdentifier]", x1, Nil))).reverse
  val info: ASTInfo = FunctionExpression0
}
object FunctionExpression0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "HasName0" -> `AL::FunctionExpression[0,0].HasName`,
    "HasName1" -> `AL::FunctionExpression[0,1].HasName`,
    "IsFunctionDefinition1" -> `AL::FunctionExpression[0,1].IsFunctionDefinition`,
    "NamedEvaluation0" -> `AL::FunctionExpression[0,0].NamedEvaluation`,
    "Contains1" -> `AL::FunctionExpression[0,1].Contains`,
    "InstantiateOrdinaryFunctionExpression0" -> `AL::FunctionExpression[0,0].InstantiateOrdinaryFunctionExpression`,
    "InstantiateOrdinaryFunctionExpression1" -> `AL::FunctionExpression[0,1].InstantiateOrdinaryFunctionExpression`,
    "Evaluation1" -> `AL::FunctionExpression[0,1].Evaluation`,
    "EarlyErrors1" -> `AL::FunctionExpression[0,1].EarlyErrors`,
  )
}
