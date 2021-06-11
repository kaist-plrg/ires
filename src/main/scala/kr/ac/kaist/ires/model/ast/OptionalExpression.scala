package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait OptionalExpression extends AST {
  val kind: String = "OptionalExpression"
}
object OptionalExpression extends ASTHelper {
  def apply(v: JsValue): OptionalExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      OptionalExpression0(MemberExpression(x0), OptionalChain(x1), params)
    case JsSeq(JsInt(1), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      OptionalExpression1(CallExpression(x0), OptionalChain(x1), params)
    case JsSeq(JsInt(2), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      OptionalExpression2(OptionalExpression(x0), OptionalChain(x1), params)
    case _ => throw InvalidAST
  }
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
