package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait IterationStatement extends AST {
  val kind: String = "IterationStatement"
}

case class IterationStatement0(x0: DoWhileStatement, parserParams: List[Boolean]) extends IterationStatement {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("DoWhileStatement", x0, Nil).reverse
  val info: ASTInfo = IterationStatement0
}
object IterationStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LoopEvaluation0" -> `AL::IterationStatement[0,0].LoopEvaluation`,
  )
}

case class IterationStatement1(x0: WhileStatement, parserParams: List[Boolean]) extends IterationStatement {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("WhileStatement", x0, Nil).reverse
  val info: ASTInfo = IterationStatement1
}
object IterationStatement1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LoopEvaluation0" -> `AL::IterationStatement[1,0].LoopEvaluation`,
  )
}

case class IterationStatement2(x0: ForStatement, parserParams: List[Boolean]) extends IterationStatement {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ForStatement", x0, Nil).reverse
  val info: ASTInfo = IterationStatement2
}
object IterationStatement2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LoopEvaluation0" -> `AL::IterationStatement[2,0].LoopEvaluation`,
  )
}

case class IterationStatement3(x0: ForInOfStatement, parserParams: List[Boolean]) extends IterationStatement {
  x0.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ForInOfStatement", x0, Nil).reverse
  val info: ASTInfo = IterationStatement3
}
object IterationStatement3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LoopEvaluation0" -> `AL::IterationStatement[3,0].LoopEvaluation`,
  )
}
