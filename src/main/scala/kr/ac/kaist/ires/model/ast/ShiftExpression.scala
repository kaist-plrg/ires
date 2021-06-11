package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ShiftExpression extends AST {
  val kind: String = "ShiftExpression"
}
object ShiftExpression extends ASTHelper {
  def apply(v: JsValue): ShiftExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ShiftExpression0(AdditiveExpression(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      ShiftExpression1(ShiftExpression(x0), AdditiveExpression(x2), params)
    case JsSeq(JsInt(2), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      ShiftExpression2(ShiftExpression(x0), AdditiveExpression(x2), params)
    case JsSeq(JsInt(3), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      ShiftExpression3(ShiftExpression(x0), AdditiveExpression(x2), params)
    case _ => throw InvalidAST
  }
}

case class ShiftExpression0(x0: AdditiveExpression, parserParams: List[Boolean]) extends ShiftExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AdditiveExpression", x0, Nil).reverse
  val info: ASTInfo = ShiftExpression0
}
object ShiftExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class ShiftExpression1(x0: ShiftExpression, x2: AdditiveExpression, parserParams: List[Boolean]) extends ShiftExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 << $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AdditiveExpression", x2, l("ShiftExpression", x0, Nil)).reverse
  val info: ASTInfo = ShiftExpression1
}
object ShiftExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::ShiftExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::ShiftExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::ShiftExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::ShiftExpression[1,0].HasCallInTailPosition`,
  )
}

case class ShiftExpression2(x0: ShiftExpression, x2: AdditiveExpression, parserParams: List[Boolean]) extends ShiftExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0 >> $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AdditiveExpression", x2, l("ShiftExpression", x0, Nil)).reverse
  val info: ASTInfo = ShiftExpression2
}
object ShiftExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::ShiftExpression[2,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::ShiftExpression[2,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::ShiftExpression[2,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::ShiftExpression[2,0].HasCallInTailPosition`,
  )
}

case class ShiftExpression3(x0: ShiftExpression, x2: AdditiveExpression, parserParams: List[Boolean]) extends ShiftExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0 >>> $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AdditiveExpression", x2, l("ShiftExpression", x0, Nil)).reverse
  val info: ASTInfo = ShiftExpression3
}
object ShiftExpression3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::ShiftExpression[3,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::ShiftExpression[3,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::ShiftExpression[3,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::ShiftExpression[3,0].HasCallInTailPosition`,
  )
}
