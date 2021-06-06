package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait Initializer extends AST {
  val kind: String = "Initializer"
}

case class Initializer0(x1: AssignmentExpression, parserParams: List[Boolean]) extends Initializer {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"= $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("AssignmentExpression", x1, Nil).reverse
  val info: ASTInfo = Initializer0
}
object Initializer0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
