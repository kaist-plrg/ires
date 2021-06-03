package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait MetaProperty extends AST {
  val kind: String = "MetaProperty"
}

case class MetaProperty0(x0: NewTarget, parserParams: List[Boolean]) extends MetaProperty {
  x0.parent = Some(this)
  val name: String = "MetaProperty0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("NewTarget", x0, Nil).reverse
  val info: ASTInfo = MetaProperty0
}
object MetaProperty0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class MetaProperty1(x0: ImportMeta, parserParams: List[Boolean]) extends MetaProperty {
  x0.parent = Some(this)
  val name: String = "MetaProperty1"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ImportMeta", x0, Nil).reverse
  val info: ASTInfo = MetaProperty1
}
object MetaProperty1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
