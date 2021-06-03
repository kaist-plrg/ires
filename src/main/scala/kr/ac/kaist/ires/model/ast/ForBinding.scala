package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ForBinding extends AST {
  val kind: String = "ForBinding"
}

case class ForBinding0(x0: BindingIdentifier, parserParams: List[Boolean]) extends ForBinding {
  x0.parent = Some(this)
  val name: String = "ForBinding0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingIdentifier", x0, Nil).reverse
  val info: ASTInfo = ForBinding0
}
object ForBinding0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsDestructuring0" -> `AL::ForBinding[0,0].IsDestructuring`,
    "Evaluation0" -> `AL::ForBinding[0,0].Evaluation`,
  )
}

case class ForBinding1(x0: BindingPattern, parserParams: List[Boolean]) extends ForBinding {
  x0.parent = Some(this)
  val name: String = "ForBinding1"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingPattern", x0, Nil).reverse
  val info: ASTInfo = ForBinding1
}
object ForBinding1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsDestructuring0" -> `AL::ForBinding[1,0].IsDestructuring`,
  )
}
