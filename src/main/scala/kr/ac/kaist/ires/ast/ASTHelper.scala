package kr.ac.kaist.ires.ast

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.util.{ Span, Pos }
import spray.json._

trait ASTHelper {
  // unapply for JsValue
  object JsSeq {
    def unapplySeq(value: JsValue): Option[Seq[JsValue]] = value match {
      case JsArray(vec) => Some(vec)
      case _ => None
    }
  }
  object JsInt {
    def unapply(value: JsValue): Option[Int] = value match {
      case JsNumber(x) => Some(x.toInt)
      case _ => None
    }
  }
  object JsBoolSeq {
    def unapply(value: JsValue): Option[List[Boolean]] = value match {
      case JsArray(ps) => Some(ps.map(_ == JsNumber(1)).toList)
      case _ => None
    }
  }
  object JsSpan {
    def unapply(value: JsValue): Option[Span] = value match {
      case JsSeq(JsInt(sl), JsInt(sc), JsInt(el), JsInt(ec)) =>
        Some(Span(Pos(sl, sc), Pos(el, ec)))
      case _ => None
    }
  }
  def opt[T](x: JsValue, f: JsValue => T): Option[T] =
    if (x == JsNull) None else Some(f(x))
  def lex(kind: String, value: JsValue): Lexical = value match {
    case JsString(str) => Lexical(kind, str)
    case _ => throw InvalidAST
  }
}
