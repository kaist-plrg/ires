package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ThrowStatement extends AST {
  val kind: String = "ThrowStatement"
}
object ThrowStatement extends ASTHelper {
  def apply(v: JsValue): ThrowStatement = v match {
    case JsSeq(JsInt(0), JsSeq(x2), JsBoolSeq(params), JsSpan(span)) =>
      ThrowStatement0(Expression(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class ThrowStatement0(x2: Expression, parserParams: List[Boolean], span: Span) extends ThrowStatement {
  x2.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"throw $x2 ;"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("Expression", x2, Nil).reverse
  val info: ASTInfo = ThrowStatement0
}
object ThrowStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ThrowStatement[0,0].Evaluation`,
  )
}
