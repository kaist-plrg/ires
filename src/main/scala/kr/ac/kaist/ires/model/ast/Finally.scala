package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait Finally extends AST {
  val kind: String = "Finally"
}
object Finally extends ASTHelper {
  def apply(v: JsValue): Finally = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      Finally0(Block(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class Finally0(x1: Block, parserParams: List[Boolean], span: Span) extends Finally {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"finally $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("Block", x1, Nil).reverse
  val info: ASTInfo = Finally0
}
object Finally0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
