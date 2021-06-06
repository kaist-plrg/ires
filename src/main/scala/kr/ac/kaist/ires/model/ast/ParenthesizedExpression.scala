package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ParenthesizedExpression extends AST {
  val kind: String = "ParenthesizedExpression"
}

case class ParenthesizedExpression0(x1: Expression, parserParams: List[Boolean]) extends ParenthesizedExpression {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"( $x1 )"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Expression", x1, Nil).reverse
  val info: ASTInfo = ParenthesizedExpression0
}
object ParenthesizedExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "NamedEvaluation0" -> `AL::ParenthesizedExpression[0,0].NamedEvaluation`,
    "Evaluation0" -> `AL::ParenthesizedExpression[0,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::ParenthesizedExpression[0,0].HasCallInTailPosition`,
  )
}
