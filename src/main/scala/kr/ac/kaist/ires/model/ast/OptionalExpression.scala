package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait OptionalExpression extends AST {
  val kind: String = "OptionalExpression"
}

case class OptionalExpression0(x0: MemberExpression, x1: OptionalChain, parserParams: List[Boolean]) extends OptionalExpression {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("OptionalChain", x1, l("MemberExpression", x0, Nil)).reverse
  val info: ASTInfo = OptionalExpression0
}
object OptionalExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::OptionalExpression[0,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::OptionalExpression[0,0].HasCallInTailPosition`,
  )
}

case class OptionalExpression1(x0: CallExpression, x1: OptionalChain, parserParams: List[Boolean]) extends OptionalExpression {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("OptionalChain", x1, l("CallExpression", x0, Nil)).reverse
  val info: ASTInfo = OptionalExpression1
}
object OptionalExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::OptionalExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::OptionalExpression[1,0].HasCallInTailPosition`,
  )
}

case class OptionalExpression2(x0: OptionalExpression, x1: OptionalChain, parserParams: List[Boolean]) extends OptionalExpression {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("OptionalChain", x1, l("OptionalExpression", x0, Nil)).reverse
  val info: ASTInfo = OptionalExpression2
}
object OptionalExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::OptionalExpression[2,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::OptionalExpression[2,0].HasCallInTailPosition`,
  )
}
