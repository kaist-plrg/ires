package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait UniqueFormalParameters extends AST {
  val kind: String = "UniqueFormalParameters"
}
object UniqueFormalParameters extends ASTHelper {
  def apply(v: JsValue): UniqueFormalParameters = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      UniqueFormalParameters0(FormalParameters(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class UniqueFormalParameters0(x0: FormalParameters, parserParams: List[Boolean], span: Span) extends UniqueFormalParameters {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FormalParameters", x0, Nil).reverse
  val info: ASTInfo = UniqueFormalParameters0
}
object UniqueFormalParameters0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "EarlyErrors0" -> `AL::UniqueFormalParameters[0,0].EarlyErrors`,
  )
}
