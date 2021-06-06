package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ExponentiationExpression extends AST {
  val kind: String = "ExponentiationExpression"
}

case class ExponentiationExpression0(x0: UnaryExpression, parserParams: List[Boolean]) extends ExponentiationExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x0, Nil).reverse
  val info: ASTInfo = ExponentiationExpression0
}
object ExponentiationExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class ExponentiationExpression1(x0: UpdateExpression, x2: ExponentiationExpression, parserParams: List[Boolean]) extends ExponentiationExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 ** $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("ExponentiationExpression", x2, l("UpdateExpression", x0, Nil)).reverse
  val info: ASTInfo = ExponentiationExpression1
}
object ExponentiationExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::ExponentiationExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::ExponentiationExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::ExponentiationExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::ExponentiationExpression[1,0].HasCallInTailPosition`,
  )
}
