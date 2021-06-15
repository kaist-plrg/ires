package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait BlockStatement extends AST {
  val kind: String = "BlockStatement"
}
object BlockStatement extends ASTHelper {
  def apply(v: JsValue): BlockStatement = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      BlockStatement0(Block(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class BlockStatement0(x0: Block, parserParams: List[Boolean], span: Span) extends BlockStatement {
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
