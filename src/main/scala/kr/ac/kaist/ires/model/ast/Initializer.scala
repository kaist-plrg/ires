package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait Initializer extends AST {
  val kind: String = "Initializer"
}
object Initializer extends ASTHelper {
  def apply(v: JsValue): Initializer = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      Initializer0(AssignmentExpression(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class Initializer0(x1: AssignmentExpression, parserParams: List[Boolean], span: Span) extends Initializer {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"= $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("AssignmentExpression", x1, Nil).reverse
  val info: ASTInfo = Initializer0
}
object Initializer0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
