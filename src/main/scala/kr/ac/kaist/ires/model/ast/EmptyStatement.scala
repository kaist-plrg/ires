package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait EmptyStatement extends AST {
  val kind: String = "EmptyStatement"
}
object EmptyStatement extends ASTHelper {
  def apply(v: JsValue): EmptyStatement = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      EmptyStatement0(params)
    case _ => throw InvalidAST
  }
}

case class EmptyStatement0(parserParams: List[Boolean]) extends EmptyStatement {
  val idx: Int = 0
  override def toString: String = {
    s";"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = EmptyStatement0
}
object EmptyStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::EmptyStatement[0,0].Evaluation`,
  )
}
