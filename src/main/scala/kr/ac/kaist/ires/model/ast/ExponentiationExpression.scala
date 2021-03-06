package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ExponentiationExpression extends AST {
  val kind: String = "ExponentiationExpression"
}
object ExponentiationExpression extends ASTHelper {
  def apply(v: JsValue): ExponentiationExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ExponentiationExpression0(UnaryExpression(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      ExponentiationExpression1(UpdateExpression(x0), ExponentiationExpression(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class ExponentiationExpression0(x0: UnaryExpression, parserParams: List[Boolean], span: Span) extends ExponentiationExpression {
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

case class ExponentiationExpression1(x0: UpdateExpression, x2: ExponentiationExpression, parserParams: List[Boolean], span: Span) extends ExponentiationExpression {
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
