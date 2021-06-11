package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait DebuggerStatement extends AST {
  val kind: String = "DebuggerStatement"
}
object DebuggerStatement extends ASTHelper {
  def apply(v: JsValue): DebuggerStatement = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      DebuggerStatement0(params)
    case _ => throw InvalidAST
  }
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
