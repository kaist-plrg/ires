package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ExpressionBody extends AST {
  val kind: String = "ExpressionBody"
}
object ExpressionBody extends ASTHelper {
  def apply(v: JsValue): ExpressionBody = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ExpressionBody0(AssignmentExpression(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class ExpressionBody0(x0: AssignmentExpression, parserParams: List[Boolean], span: Span) extends ExpressionBody {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AssignmentExpression", x0, Nil).reverse
  val info: ASTInfo = ExpressionBody0
}
object ExpressionBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ExpressionBody[0,0].Evaluation`,
  )
}
