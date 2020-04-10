package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.{ AST, ASTInfo, Lexical }
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait BindingPropertyList extends AST {
  val kind: String = "BindingPropertyList"
}
case class BindingPropertyList0(x0: BindingProperty, parserParams: List[Boolean]) extends BindingPropertyList {
  val name: String = "BindingPropertyList0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingProperty", x0, Nil).reverse
  val info: ASTInfo = BindingPropertyList0
}
object BindingPropertyList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Func] = Map()
}
case class BindingPropertyList1(x0: BindingPropertyList, x2: BindingProperty, parserParams: List[Boolean]) extends BindingPropertyList {
  val name: String = "BindingPropertyList1"
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("BindingProperty", x2, l("BindingPropertyList", x0, Nil)).reverse
  val info: ASTInfo = BindingPropertyList1
}
object BindingPropertyList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Func] = Map(
    "BoundNames0" -> BindingPropertyList1BoundNames0.func,
    "ContainsExpression0" -> BindingPropertyList1ContainsExpression0.func,
    "PropertyBindingInitialization0" -> BindingPropertyList1PropertyBindingInitialization0.func
  )
}