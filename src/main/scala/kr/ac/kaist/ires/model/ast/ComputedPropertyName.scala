package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ComputedPropertyName extends AST {
  val kind: String = "ComputedPropertyName"
}
object ComputedPropertyName extends ASTHelper {
  def apply(v: JsValue): ComputedPropertyName = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ComputedPropertyName0(AssignmentExpression(x1), params)
    case _ => throw InvalidAST
  }
}

case class ComputedPropertyName0(x1: AssignmentExpression, parserParams: List[Boolean]) extends ComputedPropertyName {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"[ $x1 ]"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("AssignmentExpression", x1, Nil).reverse
  val info: ASTInfo = ComputedPropertyName0
}
object ComputedPropertyName0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "PropName0" -> `AL::ComputedPropertyName[0,0].PropName`,
    "Evaluation0" -> `AL::ComputedPropertyName[0,0].Evaluation`,
  )
}
