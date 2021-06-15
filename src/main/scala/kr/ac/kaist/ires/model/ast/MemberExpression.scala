package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait MemberExpression extends AST {
  val kind: String = "MemberExpression"
}
object MemberExpression extends ASTHelper {
  def apply(v: JsValue): MemberExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      MemberExpression0(PrimaryExpression(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      MemberExpression1(MemberExpression(x0), Expression(x2), params, span)
    case JsSeq(JsInt(2), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      MemberExpression2(MemberExpression(x0), lex("IdentifierName", x2), params, span)
    case JsSeq(JsInt(3), JsSeq(x0, x1), JsBoolSeq(params), JsSpan(span)) =>
      MemberExpression3(MemberExpression(x0), TemplateLiteral(x1), params, span)
    case JsSeq(JsInt(4), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      MemberExpression4(SuperProperty(x0), params, span)
    case JsSeq(JsInt(5), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      MemberExpression5(MetaProperty(x0), params, span)
    case JsSeq(JsInt(6), JsSeq(x1, x2), JsBoolSeq(params), JsSpan(span)) =>
      MemberExpression6(MemberExpression(x1), Arguments(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class MemberExpression0(x0: PrimaryExpression, parserParams: List[Boolean], span: Span) extends MemberExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("PrimaryExpression", x0, Nil).reverse
  val info: ASTInfo = MemberExpression0
}
object MemberExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsDestructuring0" -> `AL::MemberExpression[0,0].IsDestructuring`,
  )
}

case class MemberExpression1(x0: MemberExpression, x2: Expression, parserParams: List[Boolean], span: Span) extends MemberExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 [ $x2 ]"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("Expression", x2, l("MemberExpression", x0, Nil)).reverse
  val info: ASTInfo = MemberExpression1
}
object MemberExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MemberExpression[1,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::MemberExpression[1,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::MemberExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::MemberExpression[1,0].Evaluation`,
    "IsDestructuring0" -> `AL::MemberExpression[1,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::MemberExpression[1,0].HasCallInTailPosition`,
  )
}

case class MemberExpression2(x0: MemberExpression, x2: Lexical, parserParams: List[Boolean], span: Span) extends MemberExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0 . $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("Lexical", x2, l("MemberExpression", x0, Nil)).reverse
  val info: ASTInfo = MemberExpression2
}
object MemberExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MemberExpression[2,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::MemberExpression[2,0].IsIdentifierRef`,
    "Contains0" -> `AL::MemberExpression[2,0].Contains`,
    "AssignmentTargetType0" -> `AL::MemberExpression[2,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::MemberExpression[2,0].Evaluation`,
    "IsDestructuring0" -> `AL::MemberExpression[2,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::MemberExpression[2,0].HasCallInTailPosition`,
  )
}

case class MemberExpression3(x0: MemberExpression, x1: TemplateLiteral, parserParams: List[Boolean], span: Span) extends MemberExpression {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("TemplateLiteral", x1, l("MemberExpression", x0, Nil)).reverse
  val info: ASTInfo = MemberExpression3
}
object MemberExpression3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MemberExpression[3,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::MemberExpression[3,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::MemberExpression[3,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::MemberExpression[3,0].Evaluation`,
    "IsDestructuring0" -> `AL::MemberExpression[3,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::MemberExpression[3,0].HasCallInTailPosition`,
  )
}

case class MemberExpression4(x0: SuperProperty, parserParams: List[Boolean], span: Span) extends MemberExpression {
  x0.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("SuperProperty", x0, Nil).reverse
  val info: ASTInfo = MemberExpression4
}
object MemberExpression4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MemberExpression[4,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::MemberExpression[4,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::MemberExpression[4,0].AssignmentTargetType`,
    "IsDestructuring0" -> `AL::MemberExpression[4,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::MemberExpression[4,0].HasCallInTailPosition`,
  )
}

case class MemberExpression5(x0: MetaProperty, parserParams: List[Boolean], span: Span) extends MemberExpression {
  x0.parent = Some(this)
  val idx: Int = 5
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("MetaProperty", x0, Nil).reverse
  val info: ASTInfo = MemberExpression5
}
object MemberExpression5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MemberExpression[5,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::MemberExpression[5,0].IsIdentifierRef`,
    "IsDestructuring0" -> `AL::MemberExpression[5,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::MemberExpression[5,0].HasCallInTailPosition`,
  )
}

case class MemberExpression6(x1: MemberExpression, x2: Arguments, parserParams: List[Boolean], span: Span) extends MemberExpression {
  x1.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 6
  override def toString: String = {
    s"new $x1 $x2"
  }
  val k: Int = d(x2, d(x1, 0))
  val fullList: List[(String, Value)] = l("Arguments", x2, l("MemberExpression", x1, Nil)).reverse
  val info: ASTInfo = MemberExpression6
}
object MemberExpression6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::MemberExpression[6,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::MemberExpression[6,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::MemberExpression[6,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::MemberExpression[6,0].Evaluation`,
    "IsDestructuring0" -> `AL::MemberExpression[6,0].IsDestructuring`,
    "HasCallInTailPosition0" -> `AL::MemberExpression[6,0].HasCallInTailPosition`,
  )
}
