package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ClassElement extends AST {
  val kind: String = "ClassElement"
}
object ClassElement extends ASTHelper {
  def apply(v: JsValue): ClassElement = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ClassElement0(MethodDefinition(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ClassElement1(MethodDefinition(x1), params, span)
    case JsSeq(JsInt(2), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      ClassElement2(params, span)
    case _ => throw InvalidAST
  }
}

case class ClassElement0(x0: MethodDefinition, parserParams: List[Boolean], span: Span) extends ClassElement {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("MethodDefinition", x0, Nil).reverse
  val info: ASTInfo = ClassElement0
}
object ClassElement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ClassElementKind0" -> `AL::ClassElement[0,0].ClassElementKind`,
    "IsStatic0" -> `AL::ClassElement[0,0].IsStatic`,
    "EarlyErrors0" -> `AL::ClassElement[0,0].EarlyErrors`,
  )
}

case class ClassElement1(x1: MethodDefinition, parserParams: List[Boolean], span: Span) extends ClassElement {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"static $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("MethodDefinition", x1, Nil).reverse
  val info: ASTInfo = ClassElement1
}
object ClassElement1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ClassElementKind0" -> `AL::ClassElement[1,0].ClassElementKind`,
    "IsStatic0" -> `AL::ClassElement[1,0].IsStatic`,
    "EarlyErrors0" -> `AL::ClassElement[1,0].EarlyErrors`,
  )
}

case class ClassElement2(parserParams: List[Boolean], span: Span) extends ClassElement {
  val idx: Int = 2
  override def toString: String = {
    s";"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = ClassElement2
}
object ClassElement2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ComputedPropertyContains0" -> `AL::ClassElement[2,0].ComputedPropertyContains`,
    "PropName0" -> `AL::ClassElement[2,0].PropName`,
    "ClassElementKind0" -> `AL::ClassElement[2,0].ClassElementKind`,
    "IsStatic0" -> `AL::ClassElement[2,0].IsStatic`,
  )
}
