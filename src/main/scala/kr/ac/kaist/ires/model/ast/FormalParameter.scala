package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait FormalParameter extends AST {
  val kind: String = "FormalParameter"
}
object FormalParameter extends ASTHelper {
  def apply(v: JsValue): FormalParameter = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      FormalParameter0(BindingElement(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class FormalParameter0(x0: BindingElement, parserParams: List[Boolean], span: Span) extends FormalParameter {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingElement", x0, Nil).reverse
  val info: ASTInfo = FormalParameter0
}
object FormalParameter0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsSimpleParameterList0" -> `AL::FormalParameter[0,0].IsSimpleParameterList`,
  )
}
