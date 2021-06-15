package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait UpdateExpression extends AST {
  val kind: String = "UpdateExpression"
}
object UpdateExpression extends ASTHelper {
  def apply(v: JsValue): UpdateExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      UpdateExpression0(LeftHandSideExpression(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      UpdateExpression1(LeftHandSideExpression(x0), params, span)
    case JsSeq(JsInt(2), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      UpdateExpression2(LeftHandSideExpression(x0), params, span)
    case JsSeq(JsInt(3), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      UpdateExpression3(UnaryExpression(x1), params, span)
    case JsSeq(JsInt(4), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      UpdateExpression4(UnaryExpression(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class UpdateExpression0(x0: LeftHandSideExpression, parserParams: List[Boolean], span: Span) extends UpdateExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("LeftHandSideExpression", x0, Nil).reverse
  val info: ASTInfo = UpdateExpression0
}
object UpdateExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class UpdateExpression1(x0: LeftHandSideExpression, parserParams: List[Boolean], span: Span) extends UpdateExpression {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 ++"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("LeftHandSideExpression", x0, Nil).reverse
  val info: ASTInfo = UpdateExpression1
}
object UpdateExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UpdateExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UpdateExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UpdateExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UpdateExpression[1,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::UpdateExpression[1,0].EarlyErrors`,
  )
}

case class UpdateExpression2(x0: LeftHandSideExpression, parserParams: List[Boolean], span: Span) extends UpdateExpression {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0 --"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("LeftHandSideExpression", x0, Nil).reverse
  val info: ASTInfo = UpdateExpression2
}
object UpdateExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UpdateExpression[2,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UpdateExpression[2,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UpdateExpression[2,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UpdateExpression[2,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::UpdateExpression[2,0].EarlyErrors`,
  )
}

case class UpdateExpression3(x1: UnaryExpression, parserParams: List[Boolean], span: Span) extends UpdateExpression {
  x1.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"++ $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UpdateExpression3
}
object UpdateExpression3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UpdateExpression[3,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UpdateExpression[3,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UpdateExpression[3,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UpdateExpression[3,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::UpdateExpression[3,0].EarlyErrors`,
  )
}

case class UpdateExpression4(x1: UnaryExpression, parserParams: List[Boolean], span: Span) extends UpdateExpression {
  x1.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"-- $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UpdateExpression4
}
object UpdateExpression4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UpdateExpression[4,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UpdateExpression[4,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UpdateExpression[4,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UpdateExpression[4,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::UpdateExpression[4,0].EarlyErrors`,
  )
}
