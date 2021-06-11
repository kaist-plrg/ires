package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait IdentifierReference extends AST {
  val kind: String = "IdentifierReference"
}
object IdentifierReference extends ASTHelper {
  def apply(v: JsValue): IdentifierReference = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      IdentifierReference0(Identifier(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      IdentifierReference1(params, span)
    case JsSeq(JsInt(2), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      IdentifierReference2(params, span)
    case _ => throw InvalidAST
  }
}

case class IdentifierReference0(x0: Identifier, parserParams: List[Boolean], span: Span) extends IdentifierReference {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Identifier", x0, Nil).reverse
  val info: ASTInfo = IdentifierReference0
}
object IdentifierReference0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "AssignmentTargetType0" -> `AL::IdentifierReference[0,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::IdentifierReference[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::IdentifierReference[0,0].EarlyErrors`,
  )
}

case class IdentifierReference1(parserParams: List[Boolean], span: Span) extends IdentifierReference {
  val idx: Int = 1
  override def toString: String = {
    s"yield"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = IdentifierReference1
}
object IdentifierReference1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "AssignmentTargetType0" -> `AL::IdentifierReference[1,0].AssignmentTargetType`,
    "StringValue0" -> `AL::IdentifierReference[1,0].StringValue`,
    "Evaluation0" -> `AL::IdentifierReference[1,0].Evaluation`,
    "EarlyErrors0" -> `AL::IdentifierReference[1,0].EarlyErrors`,
  )
}

case class IdentifierReference2(parserParams: List[Boolean], span: Span) extends IdentifierReference {
  val idx: Int = 2
  override def toString: String = {
    s"await"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = IdentifierReference2
}
object IdentifierReference2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "AssignmentTargetType0" -> `AL::IdentifierReference[2,0].AssignmentTargetType`,
    "StringValue0" -> `AL::IdentifierReference[2,0].StringValue`,
    "Evaluation0" -> `AL::IdentifierReference[2,0].Evaluation`,
    "EarlyErrors0" -> `AL::IdentifierReference[2,0].EarlyErrors`,
  )
}
