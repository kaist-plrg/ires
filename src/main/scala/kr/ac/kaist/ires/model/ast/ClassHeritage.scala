package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ClassHeritage extends AST {
  val kind: String = "ClassHeritage"
}

case class ClassHeritage0(x1: LeftHandSideExpression, parserParams: List[Boolean]) extends ClassHeritage {
  x1.parent = Some(this)
  val name: String = "ClassHeritage0"
  override def toString: String = {
    s"extends $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("LeftHandSideExpression", x1, Nil).reverse
  val info: ASTInfo = ClassHeritage0
}
object ClassHeritage0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
