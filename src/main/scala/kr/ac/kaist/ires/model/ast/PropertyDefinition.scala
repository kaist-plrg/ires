package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait PropertyDefinition extends AST {
  val kind: String = "PropertyDefinition"
}
object PropertyDefinition extends ASTHelper {
  def apply(v: JsValue): PropertyDefinition = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PropertyDefinition0(IdentifierReference(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PropertyDefinition1(CoverInitializedName(x0), params)
    case JsSeq(JsInt(2), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      PropertyDefinition2(PropertyName(x0), AssignmentExpression(x2), params)
    case JsSeq(JsInt(3), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PropertyDefinition3(MethodDefinition(x0), params)
    case JsSeq(JsInt(4), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      PropertyDefinition4(AssignmentExpression(x1), params)
    case _ => throw InvalidAST
  }
}

case class PropertyDefinition0(x0: IdentifierReference, parserParams: List[Boolean]) extends PropertyDefinition {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("IdentifierReference", x0, Nil).reverse
  val info: ASTInfo = PropertyDefinition0
}
object PropertyDefinition0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "PropName0" -> `AL::PropertyDefinition[0,0].PropName`,
    "PropertyDefinitionEvaluation0" -> `AL::PropertyDefinition[0,0].PropertyDefinitionEvaluation`,
  )
}

case class PropertyDefinition1(x0: CoverInitializedName, parserParams: List[Boolean]) extends PropertyDefinition {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("CoverInitializedName", x0, Nil).reverse
  val info: ASTInfo = PropertyDefinition1
}
object PropertyDefinition1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "EarlyErrors0" -> `AL::PropertyDefinition[1,0].EarlyErrors`,
  )
}

case class PropertyDefinition2(x0: PropertyName, x2: AssignmentExpression, parserParams: List[Boolean]) extends PropertyDefinition {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0 : $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentExpression", x2, l("PropertyName", x0, Nil)).reverse
  val info: ASTInfo = PropertyDefinition2
}
object PropertyDefinition2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "PropName0" -> `AL::PropertyDefinition[2,0].PropName`,
    "PropertyDefinitionEvaluation0" -> `AL::PropertyDefinition[2,0].PropertyDefinitionEvaluation`,
  )
}

case class PropertyDefinition3(x0: MethodDefinition, parserParams: List[Boolean]) extends PropertyDefinition {
  x0.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("MethodDefinition", x0, Nil).reverse
  val info: ASTInfo = PropertyDefinition3
}
object PropertyDefinition3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Contains0" -> `AL::PropertyDefinition[3,0].Contains`,
    "EarlyErrors0" -> `AL::PropertyDefinition[3,0].EarlyErrors`,
  )
}

case class PropertyDefinition4(x1: AssignmentExpression, parserParams: List[Boolean]) extends PropertyDefinition {
  x1.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"... $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("AssignmentExpression", x1, Nil).reverse
  val info: ASTInfo = PropertyDefinition4
}
object PropertyDefinition4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "PropName0" -> `AL::PropertyDefinition[4,0].PropName`,
    "PropertyDefinitionEvaluation0" -> `AL::PropertyDefinition[4,0].PropertyDefinitionEvaluation`,
  )
}
