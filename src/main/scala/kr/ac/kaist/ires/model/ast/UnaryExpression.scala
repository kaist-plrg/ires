package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait UnaryExpression extends AST {
  val kind: String = "UnaryExpression"
}
object UnaryExpression extends ASTHelper {
  def apply(v: JsValue): UnaryExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      UnaryExpression0(UpdateExpression(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      UnaryExpression1(UnaryExpression(x1), params, span)
    case JsSeq(JsInt(2), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      UnaryExpression2(UnaryExpression(x1), params, span)
    case JsSeq(JsInt(3), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      UnaryExpression3(UnaryExpression(x1), params, span)
    case JsSeq(JsInt(4), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      UnaryExpression4(UnaryExpression(x1), params, span)
    case JsSeq(JsInt(5), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      UnaryExpression5(UnaryExpression(x1), params, span)
    case JsSeq(JsInt(6), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      UnaryExpression6(UnaryExpression(x1), params, span)
    case JsSeq(JsInt(7), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      UnaryExpression7(UnaryExpression(x1), params, span)
    case JsSeq(JsInt(8), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      UnaryExpression8(AwaitExpression(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class UnaryExpression0(x0: UpdateExpression, parserParams: List[Boolean], span: Span) extends UnaryExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("UpdateExpression", x0, Nil).reverse
  val info: ASTInfo = UnaryExpression0
}
object UnaryExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class UnaryExpression1(x1: UnaryExpression, parserParams: List[Boolean], span: Span) extends UnaryExpression {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"delete $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression1
}
object UnaryExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[1,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression2(x1: UnaryExpression, parserParams: List[Boolean], span: Span) extends UnaryExpression {
  x1.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"void $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression2
}
object UnaryExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[2,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[2,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[2,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[2,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression3(x1: UnaryExpression, parserParams: List[Boolean], span: Span) extends UnaryExpression {
  x1.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"typeof $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression3
}
object UnaryExpression3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[3,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[3,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[3,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[3,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression4(x1: UnaryExpression, parserParams: List[Boolean], span: Span) extends UnaryExpression {
  x1.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"+ $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression4
}
object UnaryExpression4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[4,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[4,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[4,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[4,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression5(x1: UnaryExpression, parserParams: List[Boolean], span: Span) extends UnaryExpression {
  x1.parent = Some(this)
  val idx: Int = 5
  override def toString: String = {
    s"- $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression5
}
object UnaryExpression5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[5,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[5,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[5,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[5,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression6(x1: UnaryExpression, parserParams: List[Boolean], span: Span) extends UnaryExpression {
  x1.parent = Some(this)
  val idx: Int = 6
  override def toString: String = {
    s"~ $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression6
}
object UnaryExpression6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[6,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[6,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[6,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[6,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression7(x1: UnaryExpression, parserParams: List[Boolean], span: Span) extends UnaryExpression {
  x1.parent = Some(this)
  val idx: Int = 7
  override def toString: String = {
    s"! $x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("UnaryExpression", x1, Nil).reverse
  val info: ASTInfo = UnaryExpression7
}
object UnaryExpression7 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[7,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[7,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::UnaryExpression[7,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[7,0].HasCallInTailPosition`,
  )
}

case class UnaryExpression8(x0: AwaitExpression, parserParams: List[Boolean], span: Span) extends UnaryExpression {
  x0.parent = Some(this)
  val idx: Int = 8
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AwaitExpression", x0, Nil).reverse
  val info: ASTInfo = UnaryExpression8
}
object UnaryExpression8 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::UnaryExpression[8,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::UnaryExpression[8,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::UnaryExpression[8,0].HasCallInTailPosition`,
  )
}
