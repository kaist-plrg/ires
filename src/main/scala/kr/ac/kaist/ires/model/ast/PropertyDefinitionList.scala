package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait PropertyDefinitionList extends AST {
  val kind: String = "PropertyDefinitionList"
}
object PropertyDefinitionList extends ASTHelper {
  def apply(v: JsValue): PropertyDefinitionList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PropertyDefinitionList0(PropertyDefinition(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      PropertyDefinitionList1(PropertyDefinitionList(x0), PropertyDefinition(x2), params)
    case _ => throw InvalidAST
  }
}

case class PropertyDefinitionList0(x0: PropertyDefinition, parserParams: List[Boolean]) extends PropertyDefinitionList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("PropertyDefinition", x0, Nil).reverse
  val info: ASTInfo = PropertyDefinitionList0
}
object PropertyDefinitionList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "PropertyNameList0" -> `AL::PropertyDefinitionList[0,0].PropertyNameList`,
  )
}

case class PropertyDefinitionList1(x0: PropertyDefinitionList, x2: PropertyDefinition, parserParams: List[Boolean]) extends PropertyDefinitionList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("PropertyDefinition", x2, l("PropertyDefinitionList", x0, Nil)).reverse
  val info: ASTInfo = PropertyDefinitionList1
}
object PropertyDefinitionList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "PropertyNameList0" -> `AL::PropertyDefinitionList[1,0].PropertyNameList`,
    "PropertyDefinitionEvaluation0" -> `AL::PropertyDefinitionList[1,0].PropertyDefinitionEvaluation`,
  )
}
