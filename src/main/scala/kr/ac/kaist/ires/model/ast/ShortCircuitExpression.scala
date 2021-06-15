package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ShortCircuitExpression extends AST {
  val kind: String = "ShortCircuitExpression"
}
object ShortCircuitExpression extends ASTHelper {
  def apply(v: JsValue): ShortCircuitExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ShortCircuitExpression0(LogicalORExpression(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ShortCircuitExpression1(CoalesceExpression(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class ShortCircuitExpression0(x0: LogicalORExpression, parserParams: List[Boolean], span: Span) extends ShortCircuitExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("LogicalORExpression", x0, Nil).reverse
  val info: ASTInfo = ShortCircuitExpression0
}
object ShortCircuitExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class ShortCircuitExpression1(x0: CoalesceExpression, parserParams: List[Boolean], span: Span) extends ShortCircuitExpression {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("CoalesceExpression", x0, Nil).reverse
  val info: ASTInfo = ShortCircuitExpression1
}
object ShortCircuitExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
