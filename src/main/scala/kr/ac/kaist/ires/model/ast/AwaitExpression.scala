package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AwaitExpression extends AST {
  val kind: String = "AwaitExpression"
}
object AwaitExpression extends ASTHelper {
  def apply(v: JsValue): AwaitExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      AwaitExpression0(UnaryExpression(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class AwaitExpression0(x1: UnaryExpression, parserParams: List[Boolean], span: Span) extends AwaitExpression {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"await $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = AwaitExpression0
}
object AwaitExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::AwaitExpression[0,0].Evaluation`,
  )
}
