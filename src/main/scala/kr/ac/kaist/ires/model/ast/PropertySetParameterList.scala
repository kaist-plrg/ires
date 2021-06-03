package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait PropertySetParameterList extends AST {
  val kind: String = "PropertySetParameterList"
}

case class PropertySetParameterList0(x0: FormalParameter, parserParams: List[Boolean]) extends PropertySetParameterList {
  x0.parent = Some(this)
  val name: String = "PropertySetParameterList0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FormalParameter", x0, Nil).reverse
  val info: ASTInfo = PropertySetParameterList0
}
object PropertySetParameterList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ExpectedArgumentCount0" -> `AL::PropertySetParameterList[0,0].ExpectedArgumentCount`,
  )
}
