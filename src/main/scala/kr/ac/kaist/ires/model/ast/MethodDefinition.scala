package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait MethodDefinition extends AST {
  val kind: String = "MethodDefinition"
}
object MethodDefinition extends ASTHelper {
  def apply(v: JsValue): MethodDefinition = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x2, x5), JsBoolSeq(params), JsSpan(span)) =>
      MethodDefinition0(PropertyName(x0), UniqueFormalParameters(x2), FunctionBody(x5), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      MethodDefinition1(GeneratorMethod(x0), params, span)
    case JsSeq(JsInt(2), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      MethodDefinition2(AsyncMethod(x0), params, span)
    case JsSeq(JsInt(3), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      MethodDefinition3(AsyncGeneratorMethod(x0), params, span)
    case JsSeq(JsInt(4), JsSeq(x1, x5), JsBoolSeq(params), JsSpan(span)) =>
      MethodDefinition4(PropertyName(x1), FunctionBody(x5), params, span)
    case JsSeq(JsInt(5), JsSeq(x1, x3, x6), JsBoolSeq(params), JsSpan(span)) =>
      MethodDefinition5(PropertyName(x1), PropertySetParameterList(x3), FunctionBody(x6), params, span)
    case _ => throw InvalidAST
  }
}

case class MethodDefinition0(x0: PropertyName, x2: UniqueFormalParameters, x5: FunctionBody, parserParams: List[Boolean], span: Span) extends MethodDefinition {
  x0.parent = Some(this)
  x2.parent = Some(this)
  x5.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0 ( $x2 ) { $x5 }"
  }
  val k: Int = d(x5, d(x2, d(x0, 0)))
  val fullList: List[(String, Value)] = l("FunctionBody", x5, l("UniqueFormalParameters", x2, l("PropertyName", x0, Nil))).reverse
  val info: ASTInfo = MethodDefinition0
}
object MethodDefinition0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ComputedPropertyContains0" -> `AL::MethodDefinition[0,0].ComputedPropertyContains`,
    "PropName0" -> `AL::MethodDefinition[0,0].PropName`,
    "PropertyDefinitionEvaluation0" -> `AL::MethodDefinition[0,0].PropertyDefinitionEvaluation`,
    "HasDirectSuper0" -> `AL::MethodDefinition[0,0].HasDirectSuper`,
    "SpecialMethod0" -> `AL::MethodDefinition[0,0].SpecialMethod`,
    "DefineMethod0" -> `AL::MethodDefinition[0,0].DefineMethod`,
    "MethodDefinitionEvaluation0" -> `AL::MethodDefinition[0,0].MethodDefinitionEvaluation`,
    "EarlyErrors0" -> `AL::MethodDefinition[0,0].EarlyErrors`,
  )
}

case class MethodDefinition1(x0: GeneratorMethod, parserParams: List[Boolean], span: Span) extends MethodDefinition {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("GeneratorMethod", x0, Nil).reverse
  val info: ASTInfo = MethodDefinition1
}
object MethodDefinition1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "SpecialMethod0" -> `AL::MethodDefinition[1,0].SpecialMethod`,
  )
}

case class MethodDefinition2(x0: AsyncMethod, parserParams: List[Boolean], span: Span) extends MethodDefinition {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AsyncMethod", x0, Nil).reverse
  val info: ASTInfo = MethodDefinition2
}
object MethodDefinition2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "SpecialMethod0" -> `AL::MethodDefinition[2,0].SpecialMethod`,
  )
}

case class MethodDefinition3(x0: AsyncGeneratorMethod, parserParams: List[Boolean], span: Span) extends MethodDefinition {
  x0.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AsyncGeneratorMethod", x0, Nil).reverse
  val info: ASTInfo = MethodDefinition3
}
object MethodDefinition3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "SpecialMethod0" -> `AL::MethodDefinition[3,0].SpecialMethod`,
  )
}

case class MethodDefinition4(x1: PropertyName, x5: FunctionBody, parserParams: List[Boolean], span: Span) extends MethodDefinition {
  x1.parent = Some(this)
  x5.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"get $x1 ( ) { $x5 }"
  }
  val k: Int = d(x5, d(x1, 0))
  val fullList: List[(String, Value)] = l("FunctionBody", x5, l("PropertyName", x1, Nil)).reverse
  val info: ASTInfo = MethodDefinition4
}
object MethodDefinition4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ComputedPropertyContains0" -> `AL::MethodDefinition[4,0].ComputedPropertyContains`,
    "PropName0" -> `AL::MethodDefinition[4,0].PropName`,
    "PropertyDefinitionEvaluation0" -> `AL::MethodDefinition[4,0].PropertyDefinitionEvaluation`,
    "HasDirectSuper0" -> `AL::MethodDefinition[4,0].HasDirectSuper`,
    "SpecialMethod0" -> `AL::MethodDefinition[4,0].SpecialMethod`,
    "MethodDefinitionEvaluation0" -> `AL::MethodDefinition[4,0].MethodDefinitionEvaluation`,
  )
}

case class MethodDefinition5(x1: PropertyName, x3: PropertySetParameterList, x6: FunctionBody, parserParams: List[Boolean], span: Span) extends MethodDefinition {
  x1.parent = Some(this)
  x3.parent = Some(this)
  x6.parent = Some(this)
  val idx: Int = 5
  override def toString: String = {
    s"set $x1 ( $x3 ) { $x6 }"
  }
  val k: Int = d(x6, d(x3, d(x1, 0)))
  val fullList: List[(String, Value)] = l("FunctionBody", x6, l("PropertySetParameterList", x3, l("PropertyName", x1, Nil))).reverse
  val info: ASTInfo = MethodDefinition5
}
object MethodDefinition5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ComputedPropertyContains0" -> `AL::MethodDefinition[5,0].ComputedPropertyContains`,
    "PropName0" -> `AL::MethodDefinition[5,0].PropName`,
    "PropertyDefinitionEvaluation0" -> `AL::MethodDefinition[5,0].PropertyDefinitionEvaluation`,
    "HasDirectSuper0" -> `AL::MethodDefinition[5,0].HasDirectSuper`,
    "SpecialMethod0" -> `AL::MethodDefinition[5,0].SpecialMethod`,
    "MethodDefinitionEvaluation0" -> `AL::MethodDefinition[5,0].MethodDefinitionEvaluation`,
    "EarlyErrors0" -> `AL::MethodDefinition[5,0].EarlyErrors`,
  )
}
