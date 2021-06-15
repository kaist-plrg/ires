package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait CoalesceExpression extends AST {
  val kind: String = "CoalesceExpression"
}
object CoalesceExpression extends ASTHelper {
  def apply(v: JsValue): CoalesceExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      CoalesceExpression0(CoalesceExpressionHead(x0), BitwiseORExpression(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class CoalesceExpression0(x0: CoalesceExpressionHead, x2: BitwiseORExpression, parserParams: List[Boolean], span: Span) extends CoalesceExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0 ?? $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("BitwiseORExpression", x2, l("CoalesceExpressionHead", x0, Nil)).reverse
  val info: ASTInfo = CoalesceExpression0
}
object CoalesceExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::CoalesceExpression[0,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::CoalesceExpression[0,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::CoalesceExpression[0,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::CoalesceExpression[0,0].HasCallInTailPosition`,
  )
}
