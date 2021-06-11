package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait PrimaryExpression extends AST {
  val kind: String = "PrimaryExpression"
}
object PrimaryExpression extends ASTHelper {
  def apply(v: JsValue): PrimaryExpression = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression0(params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression1(IdentifierReference(x0), params, span)
    case JsSeq(JsInt(2), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression2(Literal(x0), params, span)
    case JsSeq(JsInt(3), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression3(ArrayLiteral(x0), params, span)
    case JsSeq(JsInt(4), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression4(ObjectLiteral(x0), params, span)
    case JsSeq(JsInt(5), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression5(FunctionExpression(x0), params, span)
    case JsSeq(JsInt(6), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression6(ClassExpression(x0), params, span)
    case JsSeq(JsInt(7), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression7(GeneratorExpression(x0), params, span)
    case JsSeq(JsInt(8), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression8(AsyncFunctionExpression(x0), params, span)
    case JsSeq(JsInt(9), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression9(AsyncGeneratorExpression(x0), params, span)
    case JsSeq(JsInt(10), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression10(lex("RegularExpressionLiteral", x0), params, span)
    case JsSeq(JsInt(11), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression11(TemplateLiteral(x0), params, span)
    case JsSeq(JsInt(12), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      PrimaryExpression12(CoverParenthesizedExpressionAndArrowParameterList(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class PrimaryExpression0(parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  val idx: Int = 0
  override def toString: String = {
    s"this"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = PrimaryExpression0
}
object PrimaryExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::PrimaryExpression[0,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::PrimaryExpression[0,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::PrimaryExpression[0,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::PrimaryExpression[0,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[0,0].HasCallInTailPosition`,
  )
}

case class PrimaryExpression1(x0: IdentifierReference, parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("IdentifierReference", x0, Nil).reverse
  val info: ASTInfo = PrimaryExpression1
}
object PrimaryExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::PrimaryExpression[1,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::PrimaryExpression[1,0].IsIdentifierRef`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[1,0].HasCallInTailPosition`,
  )
}

case class PrimaryExpression2(x0: Literal, parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Literal", x0, Nil).reverse
  val info: ASTInfo = PrimaryExpression2
}
object PrimaryExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::PrimaryExpression[2,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::PrimaryExpression[2,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::PrimaryExpression[2,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[2,0].HasCallInTailPosition`,
  )
}

case class PrimaryExpression3(x0: ArrayLiteral, parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  x0.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ArrayLiteral", x0, Nil).reverse
  val info: ASTInfo = PrimaryExpression3
}
object PrimaryExpression3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::PrimaryExpression[3,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::PrimaryExpression[3,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::PrimaryExpression[3,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[3,0].HasCallInTailPosition`,
  )
}

case class PrimaryExpression4(x0: ObjectLiteral, parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  x0.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ObjectLiteral", x0, Nil).reverse
  val info: ASTInfo = PrimaryExpression4
}
object PrimaryExpression4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::PrimaryExpression[4,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::PrimaryExpression[4,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::PrimaryExpression[4,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[4,0].HasCallInTailPosition`,
  )
}

case class PrimaryExpression5(x0: FunctionExpression, parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  x0.parent = Some(this)
  val idx: Int = 5
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FunctionExpression", x0, Nil).reverse
  val info: ASTInfo = PrimaryExpression5
}
object PrimaryExpression5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsIdentifierRef0" -> `AL::PrimaryExpression[5,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::PrimaryExpression[5,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[5,0].HasCallInTailPosition`,
  )
}

case class PrimaryExpression6(x0: ClassExpression, parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  x0.parent = Some(this)
  val idx: Int = 6
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ClassExpression", x0, Nil).reverse
  val info: ASTInfo = PrimaryExpression6
}
object PrimaryExpression6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsIdentifierRef0" -> `AL::PrimaryExpression[6,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::PrimaryExpression[6,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[6,0].HasCallInTailPosition`,
  )
}

case class PrimaryExpression7(x0: GeneratorExpression, parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  x0.parent = Some(this)
  val idx: Int = 7
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("GeneratorExpression", x0, Nil).reverse
  val info: ASTInfo = PrimaryExpression7
}
object PrimaryExpression7 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsIdentifierRef0" -> `AL::PrimaryExpression[7,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::PrimaryExpression[7,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[7,0].HasCallInTailPosition`,
  )
}

case class PrimaryExpression8(x0: AsyncFunctionExpression, parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  x0.parent = Some(this)
  val idx: Int = 8
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AsyncFunctionExpression", x0, Nil).reverse
  val info: ASTInfo = PrimaryExpression8
}
object PrimaryExpression8 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsIdentifierRef0" -> `AL::PrimaryExpression[8,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::PrimaryExpression[8,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[8,0].HasCallInTailPosition`,
  )
}

case class PrimaryExpression9(x0: AsyncGeneratorExpression, parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  x0.parent = Some(this)
  val idx: Int = 9
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AsyncGeneratorExpression", x0, Nil).reverse
  val info: ASTInfo = PrimaryExpression9
}
object PrimaryExpression9 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsIdentifierRef0" -> `AL::PrimaryExpression[9,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::PrimaryExpression[9,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[9,0].HasCallInTailPosition`,
  )
}

case class PrimaryExpression10(x0: Lexical, parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  x0.parent = Some(this)
  val idx: Int = 10
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Lexical", x0, Nil).reverse
  val info: ASTInfo = PrimaryExpression10
}
object PrimaryExpression10 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::PrimaryExpression[10,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::PrimaryExpression[10,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::PrimaryExpression[10,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::PrimaryExpression[10,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[10,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::PrimaryExpression[10,0].EarlyErrors`,
  )
}

case class PrimaryExpression11(x0: TemplateLiteral, parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  x0.parent = Some(this)
  val idx: Int = 11
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("TemplateLiteral", x0, Nil).reverse
  val info: ASTInfo = PrimaryExpression11
}
object PrimaryExpression11 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::PrimaryExpression[11,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::PrimaryExpression[11,0].IsIdentifierRef`,
    "AssignmentTargetType0" -> `AL::PrimaryExpression[11,0].AssignmentTargetType`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[11,0].HasCallInTailPosition`,
  )
}

case class PrimaryExpression12(x0: CoverParenthesizedExpressionAndArrowParameterList, parserParams: List[Boolean], span: Span) extends PrimaryExpression {
  x0.parent = Some(this)
  val idx: Int = 12
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("CoverParenthesizedExpressionAndArrowParameterList", x0, Nil).reverse
  val info: ASTInfo = PrimaryExpression12
}
object PrimaryExpression12 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "HasName0" -> `AL::PrimaryExpression[12,0].HasName`,
    "IsFunctionDefinition0" -> `AL::PrimaryExpression[12,0].IsFunctionDefinition`,
    "IsIdentifierRef0" -> `AL::PrimaryExpression[12,0].IsIdentifierRef`,
    "NamedEvaluation0" -> `AL::PrimaryExpression[12,0].NamedEvaluation`,
    "AssignmentTargetType0" -> `AL::PrimaryExpression[12,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::PrimaryExpression[12,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::PrimaryExpression[12,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::PrimaryExpression[12,0].EarlyErrors`,
  )
}
