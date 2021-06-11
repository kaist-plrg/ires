package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ArrowFormalParameters extends AST {
  val kind: String = "ArrowFormalParameters"
}
object ArrowFormalParameters extends ASTHelper {
  def apply(v: JsValue): ArrowFormalParameters = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ArrowFormalParameters0(UniqueFormalParameters(x1), params)
    case _ => throw InvalidAST
  }
}

case class ArrowFormalParameters0(x1: UniqueFormalParameters, parserParams: List[Boolean]) extends ArrowFormalParameters {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"( $x1 )"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UniqueFormalParameters", x1, Nil).reverse
  val info: ASTInfo = ArrowFormalParameters0
}
object ArrowFormalParameters0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
