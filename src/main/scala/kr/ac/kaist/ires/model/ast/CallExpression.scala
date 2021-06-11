package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait CallExpression extends AST {
  val kind: String = "CallExpression"
}
object CallExpression extends ASTHelper {
  def apply(v: JsValue): CallExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      CallExpression0(CoverCallExpressionAndAsyncArrowHead(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      CallExpression1(SuperCall(x0), params)
    case JsSeq(JsInt(2), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      CallExpression2(ImportCall(x0), params)
    case JsSeq(JsInt(3), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      CallExpression3(CallExpression(x0), Arguments(x1), params)
    case JsSeq(JsInt(4), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      CallExpression4(CallExpression(x0), Expression(x2), params)
    case JsSeq(JsInt(5), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      CallExpression5(CallExpression(x0), lex("IdentifierName", x2), params)
    case JsSeq(JsInt(6), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      CallExpression6(CallExpression(x0), TemplateLiteral(x1), params)
    case _ => throw InvalidAST
  }
}

case class CallExpression0(x0: CoverCallExpressionAndAsyncArrowHead, parserParams: List[Boolean]) extends CallExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("CoverCallExpressionAndAsyncArrowHead", x0, Nil).reverse
  val info: ASTInfo = CallExpression0
}
object CallExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "AssignmentTargetType0" -> `AL::CallExpression[0,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::CallExpression[0,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::CallExpression[0,0].HasCallInTailPosition`,
  )
}

case class CallExpression1(x0: SuperCall, parserParams: List[Boolean]) extends CallExpression {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("SuperCall", x0, Nil).reverse
  val info: ASTInfo = CallExpression1
}
object CallExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "AssignmentTargetType0" -> `AL::CallExpression[1,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::CallExpression[1,0].HasCallInTailPosition`,
  )
}

case class CallExpression2(x0: ImportCall, parserParams: List[Boolean]) extends CallExpression {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ImportCall", x0, Nil).reverse
  val info: ASTInfo = CallExpression2
}
object CallExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "AssignmentTargetType0" -> `AL::CallExpression[2,0].AssignmentTargetType`,
  )
}

case class CallExpression3(x0: CallExpression, x1: Arguments, parserParams: List[Boolean]) extends CallExpression {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Arguments", x1, l("CallExpression", x0, Nil)).reverse
  val info: ASTInfo = CallExpression3
}
object CallExpression3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "AssignmentTargetType0" -> `AL::CallExpression[3,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::CallExpression[3,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::CallExpression[3,0].HasCallInTailPosition`,
  )
}

case class CallExpression4(x0: CallExpression, x2: Expression, parserParams: List[Boolean]) extends CallExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"$x0 [ $x2 ]"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("Expression", x2, l("CallExpression", x0, Nil)).reverse
  val info: ASTInfo = CallExpression4
}
object CallExpression4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "AssignmentTargetType0" -> `AL::CallExpression[4,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::CallExpression[4,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::CallExpression[4,0].HasCallInTailPosition`,
  )
}

case class CallExpression5(x0: CallExpression, x2: Lexical, parserParams: List[Boolean]) extends CallExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 5
  override def toString: String = {
    s"$x0 . $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("Lexical", x2, l("CallExpression", x0, Nil)).reverse
  val info: ASTInfo = CallExpression5
}
object CallExpression5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Contains0" -> `AL::CallExpression[5,0].Contains`,
    "AssignmentTargetType0" -> `AL::CallExpression[5,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::CallExpression[5,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::CallExpression[5,0].HasCallInTailPosition`,
  )
}

case class CallExpression6(x0: CallExpression, x1: TemplateLiteral, parserParams: List[Boolean]) extends CallExpression {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 6
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("TemplateLiteral", x1, l("CallExpression", x0, Nil)).reverse
  val info: ASTInfo = CallExpression6
}
object CallExpression6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "AssignmentTargetType0" -> `AL::CallExpression[6,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::CallExpression[6,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::CallExpression[6,0].HasCallInTailPosition`,
  )
}
