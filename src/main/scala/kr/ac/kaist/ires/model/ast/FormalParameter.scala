package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait FormalParameter extends AST {
  val kind: String = "FormalParameter"
}

case class FormalParameter0(x0: BindingElement, parserParams: List[Boolean]) extends FormalParameter {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingElement", x0, Nil).reverse
  val info: ASTInfo = FormalParameter0
}
object FormalParameter0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsSimpleParameterList0" -> `AL::FormalParameter[0,0].IsSimpleParameterList`,
  )
}
