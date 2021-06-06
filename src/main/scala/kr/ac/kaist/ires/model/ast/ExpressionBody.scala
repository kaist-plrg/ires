package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ExpressionBody extends AST {
  val kind: String = "ExpressionBody"
}

case class ExpressionBody0(x0: AssignmentExpression, parserParams: List[Boolean]) extends ExpressionBody {
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
