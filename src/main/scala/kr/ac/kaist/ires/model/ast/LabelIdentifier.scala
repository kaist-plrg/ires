package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait LabelIdentifier extends AST {
  val kind: String = "LabelIdentifier"
}
object LabelIdentifier extends ASTHelper {
  def apply(v: JsValue): LabelIdentifier = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      LabelIdentifier0(Identifier(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      LabelIdentifier1(params, span)
    case JsSeq(JsInt(2), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      LabelIdentifier2(params, span)
    case _ => throw InvalidAST
  }
}

case class LabelIdentifier0(x0: Identifier, parserParams: List[Boolean], span: Span) extends LabelIdentifier {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Identifier", x0, Nil).reverse
  val info: ASTInfo = LabelIdentifier0
}
object LabelIdentifier0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "EarlyErrors0" -> `AL::LabelIdentifier[0,0].EarlyErrors`,
  )
}

case class LabelIdentifier1(parserParams: List[Boolean], span: Span) extends LabelIdentifier {
  val idx: Int = 1
  override def toString: String = {
    s"yield"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = LabelIdentifier1
}
object LabelIdentifier1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "StringValue0" -> `AL::LabelIdentifier[1,0].StringValue`,
    "EarlyErrors0" -> `AL::LabelIdentifier[1,0].EarlyErrors`,
  )
}

case class LabelIdentifier2(parserParams: List[Boolean], span: Span) extends LabelIdentifier {
  val idx: Int = 2
  override def toString: String = {
    s"await"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = LabelIdentifier2
}
object LabelIdentifier2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "StringValue0" -> `AL::LabelIdentifier[2,0].StringValue`,
    "EarlyErrors0" -> `AL::LabelIdentifier[2,0].EarlyErrors`,
  )
}
