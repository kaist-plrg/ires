package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait CatchParameter extends AST {
  val kind: String = "CatchParameter"
}
object CatchParameter extends ASTHelper {
  def apply(v: JsValue): CatchParameter = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      CatchParameter0(BindingIdentifier(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      CatchParameter1(BindingPattern(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class CatchParameter0(x0: BindingIdentifier, parserParams: List[Boolean], span: Span) extends CatchParameter {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingIdentifier", x0, Nil).reverse
  val info: ASTInfo = CatchParameter0
}
object CatchParameter0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class CatchParameter1(x0: BindingPattern, parserParams: List[Boolean], span: Span) extends CatchParameter {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingPattern", x0, Nil).reverse
  val info: ASTInfo = CatchParameter1
}
object CatchParameter1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
