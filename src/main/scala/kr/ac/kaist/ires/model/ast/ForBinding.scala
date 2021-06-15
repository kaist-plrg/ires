package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ForBinding extends AST {
  val kind: String = "ForBinding"
}
object ForBinding extends ASTHelper {
  def apply(v: JsValue): ForBinding = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ForBinding0(BindingIdentifier(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ForBinding1(BindingPattern(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class ForBinding0(x0: BindingIdentifier, parserParams: List[Boolean], span: Span) extends ForBinding {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingIdentifier", x0, Nil).reverse
  val info: ASTInfo = ForBinding0
}
object ForBinding0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsDestructuring0" -> `AL::ForBinding[0,0].IsDestructuring`,
    "Evaluation0" -> `AL::ForBinding[0,0].Evaluation`,
  )
}

case class ForBinding1(x0: BindingPattern, parserParams: List[Boolean], span: Span) extends ForBinding {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingPattern", x0, Nil).reverse
  val info: ASTInfo = ForBinding1
}
object ForBinding1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsDestructuring0" -> `AL::ForBinding[1,0].IsDestructuring`,
  )
}
