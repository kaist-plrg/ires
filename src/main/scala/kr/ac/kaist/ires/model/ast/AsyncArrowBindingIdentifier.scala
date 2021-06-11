package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AsyncArrowBindingIdentifier extends AST {
  val kind: String = "AsyncArrowBindingIdentifier"
}
object AsyncArrowBindingIdentifier extends ASTHelper {
  def apply(v: JsValue): AsyncArrowBindingIdentifier = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      AsyncArrowBindingIdentifier0(BindingIdentifier(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class AsyncArrowBindingIdentifier0(x0: BindingIdentifier, parserParams: List[Boolean], span: Span) extends AsyncArrowBindingIdentifier {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingIdentifier", x0, Nil).reverse
  val info: ASTInfo = AsyncArrowBindingIdentifier0
}
object AsyncArrowBindingIdentifier0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IteratorBindingInitialization0" -> `AL::AsyncArrowBindingIdentifier[0,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::AsyncArrowBindingIdentifier[0,0].ContainsExpression`,
    "IsSimpleParameterList0" -> `AL::AsyncArrowBindingIdentifier[0,0].IsSimpleParameterList`,
    "ExpectedArgumentCount0" -> `AL::AsyncArrowBindingIdentifier[0,0].ExpectedArgumentCount`,
  )
}
