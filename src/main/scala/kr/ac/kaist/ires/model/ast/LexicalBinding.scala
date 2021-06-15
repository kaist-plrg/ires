package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait LexicalBinding extends AST {
  val kind: String = "LexicalBinding"
}
object LexicalBinding extends ASTHelper {
  def apply(v: JsValue): LexicalBinding = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      LexicalBinding0(BindingIdentifier(x0), opt(x1, Initializer.apply), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      LexicalBinding1(BindingPattern(x0), Initializer(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class LexicalBinding0(x0: BindingIdentifier, x1: Option[Initializer], parserParams: List[Boolean], span: Span) extends LexicalBinding {
  x0.parent = Some(this)
  x1.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"$x0 ${x1.getOrElse("")}"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Option[Initializer]", x1, l("BindingIdentifier", x0, Nil)).reverse
  val info: ASTInfo = LexicalBinding0
}
object LexicalBinding0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "BoundNames1" -> `AL::LexicalBinding[0,1].BoundNames`,
    "Evaluation0" -> `AL::LexicalBinding[0,0].Evaluation`,
    "Evaluation1" -> `AL::LexicalBinding[0,1].Evaluation`,
    "EarlyErrors1" -> `AL::LexicalBinding[0,1].EarlyErrors`,
  )
}

case class LexicalBinding1(x0: BindingPattern, x1: Initializer, parserParams: List[Boolean], span: Span) extends LexicalBinding {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Initializer", x1, l("BindingPattern", x0, Nil)).reverse
  val info: ASTInfo = LexicalBinding1
}
object LexicalBinding1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::LexicalBinding[1,0].BoundNames`,
    "Evaluation0" -> `AL::LexicalBinding[1,0].Evaluation`,
  )
}
