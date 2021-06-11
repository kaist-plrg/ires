package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait Identifier extends AST {
  val kind: String = "Identifier"
}
object Identifier extends ASTHelper {
  def apply(v: JsValue): Identifier = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Identifier0(lex("IdentifierName", x0), params, span)
    case _ => throw InvalidAST
  }
}

case class Identifier0(x0: Lexical, parserParams: List[Boolean], span: Span) extends Identifier {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Lexical", x0, Nil).reverse
  val info: ASTInfo = Identifier0
}
object Identifier0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "StringValue0" -> `AL::Identifier[0,0].StringValue`,
    "EarlyErrors0" -> `AL::Identifier[0,0].EarlyErrors`,
  )
}
