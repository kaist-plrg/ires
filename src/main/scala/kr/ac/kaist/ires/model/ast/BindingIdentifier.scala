package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait BindingIdentifier extends AST {
  val kind: String = "BindingIdentifier"
}
object BindingIdentifier extends ASTHelper {
  def apply(v: JsValue): BindingIdentifier = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      BindingIdentifier0(Identifier(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      BindingIdentifier1(params, span)
    case JsSeq(JsInt(2), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      BindingIdentifier2(params, span)
    case _ => throw InvalidAST
  }
}

case class BindingIdentifier0(x0: Identifier, parserParams: List[Boolean], span: Span) extends BindingIdentifier {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Identifier", x0, Nil).reverse
  val info: ASTInfo = BindingIdentifier0
}
object BindingIdentifier0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::BindingIdentifier[0,0].BoundNames`,
    "BindingInitialization0" -> `AL::BindingIdentifier[0,0].BindingInitialization`,
    "EarlyErrors0" -> `AL::BindingIdentifier[0,0].EarlyErrors`,
    "EarlyErrors0" -> `AL::BindingIdentifier[0,0].EarlyErrors`,
  )
}

case class BindingIdentifier1(parserParams: List[Boolean], span: Span) extends BindingIdentifier {
  val idx: Int = 1
  override def toString: String = {
    s"yield"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = BindingIdentifier1
}
object BindingIdentifier1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::BindingIdentifier[1,0].BoundNames`,
    "BindingInitialization0" -> `AL::BindingIdentifier[1,0].BindingInitialization`,
    "StringValue0" -> `AL::BindingIdentifier[1,0].StringValue`,
    "EarlyErrors0" -> `AL::BindingIdentifier[1,0].EarlyErrors`,
    "EarlyErrors0" -> `AL::BindingIdentifier[1,0].EarlyErrors`,
  )
}

case class BindingIdentifier2(parserParams: List[Boolean], span: Span) extends BindingIdentifier {
  val idx: Int = 2
  override def toString: String = {
    s"await"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = BindingIdentifier2
}
object BindingIdentifier2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::BindingIdentifier[2,0].BoundNames`,
    "BindingInitialization0" -> `AL::BindingIdentifier[2,0].BindingInitialization`,
    "StringValue0" -> `AL::BindingIdentifier[2,0].StringValue`,
    "EarlyErrors0" -> `AL::BindingIdentifier[2,0].EarlyErrors`,
    "EarlyErrors0" -> `AL::BindingIdentifier[2,0].EarlyErrors`,
  )
}
