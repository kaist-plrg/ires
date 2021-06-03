package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait BreakableStatement extends AST {
  val kind: String = "BreakableStatement"
}

case class BreakableStatement0(x0: IterationStatement, parserParams: List[Boolean]) extends BreakableStatement {
  x0.parent = Some(this)
  val name: String = "BreakableStatement0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("IterationStatement", x0, Nil).reverse
  val info: ASTInfo = BreakableStatement0
}
object BreakableStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ContainsUndefinedContinueTarget0" -> `AL::BreakableStatement[0,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::BreakableStatement[0,0].Evaluation`,
    "LabelledEvaluation0" -> `AL::BreakableStatement[0,0].LabelledEvaluation`,
  )
}

case class BreakableStatement1(x0: SwitchStatement, parserParams: List[Boolean]) extends BreakableStatement {
  x0.parent = Some(this)
  val name: String = "BreakableStatement1"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("SwitchStatement", x0, Nil).reverse
  val info: ASTInfo = BreakableStatement1
}
object BreakableStatement1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::BreakableStatement[1,0].Evaluation`,
    "LabelledEvaluation0" -> `AL::BreakableStatement[1,0].LabelledEvaluation`,
  )
}
