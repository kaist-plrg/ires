package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait PropertySetParameterList extends AST {
  val kind: String = "PropertySetParameterList"
}
object PropertySetParameterList extends ASTHelper {
  def apply(v: JsValue): PropertySetParameterList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PropertySetParameterList0(FormalParameter(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class PropertySetParameterList0(x0: FormalParameter, parserParams: List[Boolean], span: Span) extends PropertySetParameterList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FormalParameter", x0, Nil).reverse
  val info: ASTInfo = PropertySetParameterList0
}
object PropertySetParameterList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ExpectedArgumentCount0" -> `AL::PropertySetParameterList[0,0].ExpectedArgumentCount`,
  )
}
