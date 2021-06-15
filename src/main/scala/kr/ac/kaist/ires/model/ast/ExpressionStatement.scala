package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ExpressionStatement extends AST {
  val kind: String = "ExpressionStatement"
}
object ExpressionStatement extends ASTHelper {
  def apply(v: JsValue): ExpressionStatement = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ExpressionStatement0(Expression(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class ExpressionStatement0(x1: Expression, parserParams: List[Boolean], span: Span) extends ExpressionStatement {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x1 ;"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Expression", x1, Nil).reverse
  val info: ASTInfo = ExpressionStatement0
}
object ExpressionStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ExpressionStatement[0,0].Evaluation`,
  )
}
