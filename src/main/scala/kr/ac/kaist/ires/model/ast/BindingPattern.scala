package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait BindingPattern extends AST {
  val kind: String = "BindingPattern"
}

case class BindingPattern0(x0: ObjectBindingPattern, parserParams: List[Boolean]) extends BindingPattern {
  x0.parent = Some(this)
  val name: String = "BindingPattern0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ObjectBindingPattern", x0, Nil).reverse
  val info: ASTInfo = BindingPattern0
}
object BindingPattern0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BindingInitialization0" -> `AL::BindingPattern[0,0].BindingInitialization`,
  )
}

case class BindingPattern1(x0: ArrayBindingPattern, parserParams: List[Boolean]) extends BindingPattern {
  x0.parent = Some(this)
  val name: String = "BindingPattern1"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ArrayBindingPattern", x0, Nil).reverse
  val info: ASTInfo = BindingPattern1
}
object BindingPattern1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BindingInitialization0" -> `AL::BindingPattern[1,0].BindingInitialization`,
  )
}
