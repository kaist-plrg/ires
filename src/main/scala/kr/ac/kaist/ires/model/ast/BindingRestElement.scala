package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait BindingRestElement extends AST {
  val kind: String = "BindingRestElement"
}

case class BindingRestElement0(x1: BindingIdentifier, parserParams: List[Boolean]) extends BindingRestElement {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"... $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("BindingIdentifier", x1, Nil).reverse
  val info: ASTInfo = BindingRestElement0
}
object BindingRestElement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IteratorBindingInitialization0" -> `AL::BindingRestElement[0,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::BindingRestElement[0,0].ContainsExpression`,
  )
}

case class BindingRestElement1(x1: BindingPattern, parserParams: List[Boolean]) extends BindingRestElement {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"... $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("BindingPattern", x1, Nil).reverse
  val info: ASTInfo = BindingRestElement1
}
object BindingRestElement1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IteratorBindingInitialization0" -> `AL::BindingRestElement[1,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::BindingRestElement[1,0].ContainsExpression`,
  )
}
