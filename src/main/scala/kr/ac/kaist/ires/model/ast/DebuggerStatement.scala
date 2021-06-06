package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait DebuggerStatement extends AST {
  val kind: String = "DebuggerStatement"
}

case class DebuggerStatement0(parserParams: List[Boolean]) extends DebuggerStatement {
  val idx: Int = 0
  override def toString: String = {
    s"debugger ;"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = DebuggerStatement0
}
object DebuggerStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::DebuggerStatement[0,0].Evaluation`,
  )
}
