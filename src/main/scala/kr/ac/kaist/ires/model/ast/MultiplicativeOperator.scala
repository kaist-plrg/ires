package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait MultiplicativeOperator extends AST {
  val kind: String = "MultiplicativeOperator"
}

case class MultiplicativeOperator0(parserParams: List[Boolean]) extends MultiplicativeOperator {
  val name: String = "MultiplicativeOperator0"
  override def toString: String = {
    s"*"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = MultiplicativeOperator0
}
object MultiplicativeOperator0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class MultiplicativeOperator1(parserParams: List[Boolean]) extends MultiplicativeOperator {
  val name: String = "MultiplicativeOperator1"
  override def toString: String = {
    s"/"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = MultiplicativeOperator1
}
object MultiplicativeOperator1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class MultiplicativeOperator2(parserParams: List[Boolean]) extends MultiplicativeOperator {
  val name: String = "MultiplicativeOperator2"
  override def toString: String = {
    s"%"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = MultiplicativeOperator2
}
object MultiplicativeOperator2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
