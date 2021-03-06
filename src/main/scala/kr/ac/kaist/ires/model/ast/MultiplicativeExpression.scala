package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait MultiplicativeExpression extends AST {
  val kind: String = "MultiplicativeExpression"
}
object MultiplicativeExpression extends ASTHelper {
  def apply(v: JsValue): MultiplicativeExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      MultiplicativeExpression0(ExponentiationExpression(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x1, x2), JsBoolSeq(params), JsSpan(span)) =>
      MultiplicativeExpression1(MultiplicativeExpression(x0), MultiplicativeOperator(x1), ExponentiationExpression(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class MultiplicativeExpression0(x0: ExponentiationExpression, parserParams: List[Boolean], span: Span) extends MultiplicativeExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ExponentiationExpression", x0, Nil).reverse
  val info: ASTInfo = MultiplicativeExpression0
}
object MultiplicativeExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class MultiplicativeExpression1(x0: MultiplicativeExpression, x1: MultiplicativeOperator, x2: ExponentiationExpression, parserParams: List[Boolean], span: Span) extends MultiplicativeExpression {
  x0.parent = Some(this)
  x1.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 $x1 $x2"
  }
  val k: Int = d(x2, d(x1, d(x0, 0)))
  val fullList: List[(String, Value)] = l("ExponentiationExpression", x2, l("MultiplicativeOperator", x1, l("MultiplicativeExpression", x0, Nil))).reverse
  val info: ASTInfo = MultiplicativeExpression1
}
object MultiplicativeExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MultiplicativeExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::MultiplicativeExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::MultiplicativeExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::MultiplicativeExpression[1,0].HasCallInTailPosition`,
  )
}
