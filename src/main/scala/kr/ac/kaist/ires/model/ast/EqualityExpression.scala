package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait EqualityExpression extends AST {
  val kind: String = "EqualityExpression"
}
object EqualityExpression extends ASTHelper {
  def apply(v: JsValue): EqualityExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      EqualityExpression0(RelationalExpression(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      EqualityExpression1(EqualityExpression(x0), RelationalExpression(x2), params)
    case JsSeq(JsInt(2), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      EqualityExpression2(EqualityExpression(x0), RelationalExpression(x2), params)
    case JsSeq(JsInt(3), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      EqualityExpression3(EqualityExpression(x0), RelationalExpression(x2), params)
    case JsSeq(JsInt(4), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      EqualityExpression4(EqualityExpression(x0), RelationalExpression(x2), params)
    case _ => throw InvalidAST
  }
}

case class EqualityExpression0(x0: RelationalExpression, parserParams: List[Boolean]) extends EqualityExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("RelationalExpression", x0, Nil).reverse
  val info: ASTInfo = EqualityExpression0
}
object EqualityExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class EqualityExpression1(x0: EqualityExpression, x2: RelationalExpression, parserParams: List[Boolean]) extends EqualityExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 == $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("RelationalExpression", x2, l("EqualityExpression", x0, Nil)).reverse
  val info: ASTInfo = EqualityExpression1
}
object EqualityExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::EqualityExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::EqualityExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::EqualityExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::EqualityExpression[1,0].HasCallInTailPosition`,
  )
}

case class EqualityExpression2(x0: EqualityExpression, x2: RelationalExpression, parserParams: List[Boolean]) extends EqualityExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0 != $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("RelationalExpression", x2, l("EqualityExpression", x0, Nil)).reverse
  val info: ASTInfo = EqualityExpression2
}
object EqualityExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::EqualityExpression[2,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::EqualityExpression[2,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::EqualityExpression[2,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::EqualityExpression[2,0].HasCallInTailPosition`,
  )
}

case class EqualityExpression3(x0: EqualityExpression, x2: RelationalExpression, parserParams: List[Boolean]) extends EqualityExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0 === $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("RelationalExpression", x2, l("EqualityExpression", x0, Nil)).reverse
  val info: ASTInfo = EqualityExpression3
}
object EqualityExpression3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::EqualityExpression[3,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::EqualityExpression[3,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::EqualityExpression[3,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::EqualityExpression[3,0].HasCallInTailPosition`,
  )
}

case class EqualityExpression4(x0: EqualityExpression, x2: RelationalExpression, parserParams: List[Boolean]) extends EqualityExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"$x0 !== $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("RelationalExpression", x2, l("EqualityExpression", x0, Nil)).reverse
  val info: ASTInfo = EqualityExpression4
}
object EqualityExpression4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::EqualityExpression[4,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::EqualityExpression[4,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::EqualityExpression[4,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::EqualityExpression[4,0].HasCallInTailPosition`,
  )
}
