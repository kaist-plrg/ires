package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ContinueStatement extends AST {
  val kind: String = "ContinueStatement"
}

case class ContinueStatement0(parserParams: List[Boolean]) extends ContinueStatement {
  val idx: Int = 0
  override def toString: String = {
    s"continue ;"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = ContinueStatement0
}
object ContinueStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ContainsUndefinedContinueTarget0" -> `AL::ContinueStatement[0,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::ContinueStatement[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::ContinueStatement[0,0].EarlyErrors`,
  )
}

case class ContinueStatement1(x2: LabelIdentifier, parserParams: List[Boolean]) extends ContinueStatement {
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"continue $x2 ;"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("LabelIdentifier", x2, Nil).reverse
  val info: ASTInfo = ContinueStatement1
}
object ContinueStatement1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ContainsUndefinedContinueTarget0" -> `AL::ContinueStatement[1,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::ContinueStatement[1,0].Evaluation`,
    "EarlyErrors0" -> `AL::ContinueStatement[1,0].EarlyErrors`,
  )
}
