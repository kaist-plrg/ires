package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait BindingElementList extends AST {
  val kind: String = "BindingElementList"
}

case class BindingElementList0(x0: BindingElisionElement, parserParams: List[Boolean]) extends BindingElementList {
  x0.parent = Some(this)
  val name: String = "BindingElementList0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingElisionElement", x0, Nil).reverse
  val info: ASTInfo = BindingElementList0
}
object BindingElementList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class BindingElementList1(x0: BindingElementList, x2: BindingElisionElement, parserParams: List[Boolean]) extends BindingElementList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val name: String = "BindingElementList1"
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("BindingElisionElement", x2, l("BindingElementList", x0, Nil)).reverse
  val info: ASTInfo = BindingElementList1
}
object BindingElementList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::BindingElementList[1,0].BoundNames`,
    "IteratorBindingInitialization0" -> `AL::BindingElementList[1,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::BindingElementList[1,0].ContainsExpression`,
  )
}
