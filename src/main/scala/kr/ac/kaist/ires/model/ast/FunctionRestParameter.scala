package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait FunctionRestParameter extends AST {
  val kind: String = "FunctionRestParameter"
}

case class FunctionRestParameter0(x0: BindingRestElement, parserParams: List[Boolean]) extends FunctionRestParameter {
  x0.parent = Some(this)
  val name: String = "FunctionRestParameter0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingRestElement", x0, Nil).reverse
  val info: ASTInfo = FunctionRestParameter0
}
object FunctionRestParameter0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
