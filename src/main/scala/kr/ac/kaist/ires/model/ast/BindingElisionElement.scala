package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait BindingElisionElement extends AST {
  val kind: String = "BindingElisionElement"
}

case class BindingElisionElement0(x0: Option[Elision], x1: BindingElement, parserParams: List[Boolean]) extends BindingElisionElement {
  x0.foreach((m) => m.parent = Some(this))
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"${x0.getOrElse("")} $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("BindingElement", x1, l("Option[Elision]", x0, Nil)).reverse
  val info: ASTInfo = BindingElisionElement0
}
object BindingElisionElement0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "BoundNames1" -> `AL::BindingElisionElement[0,1].BoundNames`,
    "IteratorBindingInitialization1" -> `AL::BindingElisionElement[0,1].IteratorBindingInitialization`,
    "ContainsExpression1" -> `AL::BindingElisionElement[0,1].ContainsExpression`,
  )
}
