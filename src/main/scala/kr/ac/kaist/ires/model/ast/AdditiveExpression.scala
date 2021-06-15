package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AdditiveExpression extends AST {
  val kind: String = "AdditiveExpression"
}
object AdditiveExpression extends ASTHelper {
  def apply(v: JsValue): AdditiveExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      AdditiveExpression0(MultiplicativeExpression(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      AdditiveExpression1(AdditiveExpression(x0), MultiplicativeExpression(x2), params, span)
    case JsSeq(JsInt(2), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      AdditiveExpression2(AdditiveExpression(x0), MultiplicativeExpression(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class AdditiveExpression0(x0: MultiplicativeExpression, parserParams: List[Boolean], span: Span) extends AdditiveExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("MultiplicativeExpression", x0, Nil).reverse
  val info: ASTInfo = AdditiveExpression0
}
object AdditiveExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AdditiveExpression1(x0: AdditiveExpression, x2: MultiplicativeExpression, parserParams: List[Boolean], span: Span) extends AdditiveExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 + $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("MultiplicativeExpression", x2, l("AdditiveExpression", x0, Nil)).reverse
  val info: ASTInfo = AdditiveExpression1
}
object AdditiveExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::AdditiveExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::AdditiveExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::AdditiveExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::AdditiveExpression[1,0].HasCallInTailPosition`,
  )
}

case class AdditiveExpression2(x0: AdditiveExpression, x2: MultiplicativeExpression, parserParams: List[Boolean], span: Span) extends AdditiveExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0 - $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("MultiplicativeExpression", x2, l("AdditiveExpression", x0, Nil)).reverse
  val info: ASTInfo = AdditiveExpression2
}
object AdditiveExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::AdditiveExpression[2,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::AdditiveExpression[2,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::AdditiveExpression[2,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::AdditiveExpression[2,0].HasCallInTailPosition`,
  )
}
