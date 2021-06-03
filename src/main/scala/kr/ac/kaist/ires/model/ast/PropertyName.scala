package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait PropertyName extends AST {
  val kind: String = "PropertyName"
}

case class PropertyName0(x0: LiteralPropertyName, parserParams: List[Boolean]) extends PropertyName {
  x0.parent = Some(this)
  val name: String = "PropertyName0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("LiteralPropertyName", x0, Nil).reverse
  val info: ASTInfo = PropertyName0
}
object PropertyName0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ComputedPropertyContains0" -> `AL::PropertyName[0,0].ComputedPropertyContains`,
    "IsComputedPropertyKey0" -> `AL::PropertyName[0,0].IsComputedPropertyKey`,
  )
}

case class PropertyName1(x0: ComputedPropertyName, parserParams: List[Boolean]) extends PropertyName {
  x0.parent = Some(this)
  val name: String = "PropertyName1"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ComputedPropertyName", x0, Nil).reverse
  val info: ASTInfo = PropertyName1
}
object PropertyName1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ComputedPropertyContains0" -> `AL::PropertyName[1,0].ComputedPropertyContains`,
    "IsComputedPropertyKey0" -> `AL::PropertyName[1,0].IsComputedPropertyKey`,
  )
}
