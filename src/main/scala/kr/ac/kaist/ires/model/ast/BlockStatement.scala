package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait BlockStatement extends AST {
  val kind: String = "BlockStatement"
}

case class BlockStatement0(x0: Block, parserParams: List[Boolean]) extends BlockStatement {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Block", x0, Nil).reverse
  val info: ASTInfo = BlockStatement0
}
object BlockStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
