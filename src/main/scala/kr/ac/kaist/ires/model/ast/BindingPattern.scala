package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait BindingPattern extends AST {
  val kind: String = "BindingPattern"
}
object BindingPattern extends ASTHelper {
  def apply(v: JsValue): BindingPattern = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      BindingPattern0(ObjectBindingPattern(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      BindingPattern1(ArrayBindingPattern(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class BindingPattern0(x0: ObjectBindingPattern, parserParams: List[Boolean], span: Span) extends BindingPattern {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ObjectBindingPattern", x0, Nil).reverse
  val info: ASTInfo = BindingPattern0
}
object BindingPattern0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BindingInitialization0" -> `AL::BindingPattern[0,0].BindingInitialization`,
  )
}

case class BindingPattern1(x0: ArrayBindingPattern, parserParams: List[Boolean], span: Span) extends BindingPattern {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ArrayBindingPattern", x0, Nil).reverse
  val info: ASTInfo = BindingPattern1
}
object BindingPattern1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BindingInitialization0" -> `AL::BindingPattern[1,0].BindingInitialization`,
  )
}
