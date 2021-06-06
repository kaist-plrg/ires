package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait SuperCall extends AST {
  val kind: String = "SuperCall"
}

case class SuperCall0(x1: Arguments, parserParams: List[Boolean]) extends SuperCall {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"super $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Arguments", x1, Nil).reverse
  val info: ASTInfo = SuperCall0
}
object SuperCall0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::SuperCall[0,0].Evaluation`,
  )
}
