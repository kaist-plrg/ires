package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ClassElementList extends AST {
  val kind: String = "ClassElementList"
}
object ClassElementList extends ASTHelper {
  def apply(v: JsValue): ClassElementList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ClassElementList0(ClassElement(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      ClassElementList1(ClassElementList(x0), ClassElement(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class ClassElementList0(x0: ClassElement, parserParams: List[Boolean], span: Span) extends ClassElementList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ClassElement", x0, Nil).reverse
  val info: ASTInfo = ClassElementList0
}
object ClassElementList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ConstructorMethod0" -> `AL::ClassElementList[0,0].ConstructorMethod`,
    "NonConstructorMethodDefinitions0" -> `AL::ClassElementList[0,0].NonConstructorMethodDefinitions`,
    "PrototypePropertyNameList0" -> `AL::ClassElementList[0,0].PrototypePropertyNameList`,
  )
}

case class ClassElementList1(x0: ClassElementList, x1: ClassElement, parserParams: List[Boolean], span: Span) extends ClassElementList {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("ClassElement", x1, l("ClassElementList", x0, Nil)).reverse
  val info: ASTInfo = ClassElementList1
}
object ClassElementList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ComputedPropertyContains0" -> `AL::ClassElementList[1,0].ComputedPropertyContains`,
    "ConstructorMethod0" -> `AL::ClassElementList[1,0].ConstructorMethod`,
    "NonConstructorMethodDefinitions0" -> `AL::ClassElementList[1,0].NonConstructorMethodDefinitions`,
    "PrototypePropertyNameList0" -> `AL::ClassElementList[1,0].PrototypePropertyNameList`,
  )
}
