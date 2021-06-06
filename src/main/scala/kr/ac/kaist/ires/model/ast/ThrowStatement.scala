package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ThrowStatement extends AST {
  val kind: String = "ThrowStatement"
}

case class ThrowStatement0(x2: Expression, parserParams: List[Boolean]) extends ThrowStatement {
  x2.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"throw $x2 ;"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("Expression", x2, Nil).reverse
  val info: ASTInfo = ThrowStatement0
}
object ThrowStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::ThrowStatement[0,0].Evaluation`,
  )
}
