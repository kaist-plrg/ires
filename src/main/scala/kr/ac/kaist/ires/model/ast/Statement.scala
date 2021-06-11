package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait Statement extends AST {
  val kind: String = "Statement"
}
object Statement extends ASTHelper {
  def apply(v: JsValue): Statement = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement0(BlockStatement(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement1(VariableStatement(x0), params, span)
    case JsSeq(JsInt(2), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement2(EmptyStatement(x0), params, span)
    case JsSeq(JsInt(3), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement3(ExpressionStatement(x0), params, span)
    case JsSeq(JsInt(4), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement4(IfStatement(x0), params, span)
    case JsSeq(JsInt(5), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement5(BreakableStatement(x0), params, span)
    case JsSeq(JsInt(6), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement6(ContinueStatement(x0), params, span)
    case JsSeq(JsInt(7), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement7(BreakStatement(x0), params, span)
    case JsSeq(JsInt(8), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement8(ReturnStatement(x0), params, span)
    case JsSeq(JsInt(9), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement9(WithStatement(x0), params, span)
    case JsSeq(JsInt(10), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement10(LabelledStatement(x0), params, span)
    case JsSeq(JsInt(11), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement11(ThrowStatement(x0), params, span)
    case JsSeq(JsInt(12), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement12(TryStatement(x0), params, span)
    case JsSeq(JsInt(13), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Statement13(DebuggerStatement(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class Statement0(x0: BlockStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BlockStatement", x0, Nil).reverse
  val info: ASTInfo = Statement0
}
object Statement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LabelledEvaluation0" -> `AL::Statement[0,0].LabelledEvaluation`,
  )
}

case class Statement1(x0: VariableStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("VariableStatement", x0, Nil).reverse
  val info: ASTInfo = Statement1
}
object Statement1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ContainsDuplicateLabels0" -> `AL::Statement[1,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::Statement[1,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::Statement[1,0].ContainsUndefinedContinueTarget`,
    "LabelledEvaluation0" -> `AL::Statement[1,0].LabelledEvaluation`,
    "HasCallInTailPosition0" -> `AL::Statement[1,0].HasCallInTailPosition`,
  )
}

case class Statement2(x0: EmptyStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("EmptyStatement", x0, Nil).reverse
  val info: ASTInfo = Statement2
}
object Statement2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::Statement[2,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::Statement[2,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::Statement[2,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::Statement[2,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::Statement[2,0].ContainsUndefinedContinueTarget`,
    "LabelledEvaluation0" -> `AL::Statement[2,0].LabelledEvaluation`,
    "HasCallInTailPosition0" -> `AL::Statement[2,0].HasCallInTailPosition`,
  )
}

case class Statement3(x0: ExpressionStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ExpressionStatement", x0, Nil).reverse
  val info: ASTInfo = Statement3
}
object Statement3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::Statement[3,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::Statement[3,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::Statement[3,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::Statement[3,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::Statement[3,0].ContainsUndefinedContinueTarget`,
    "LabelledEvaluation0" -> `AL::Statement[3,0].LabelledEvaluation`,
    "HasCallInTailPosition0" -> `AL::Statement[3,0].HasCallInTailPosition`,
  )
}

case class Statement4(x0: IfStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("IfStatement", x0, Nil).reverse
  val info: ASTInfo = Statement4
}
object Statement4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LabelledEvaluation0" -> `AL::Statement[4,0].LabelledEvaluation`,
  )
}

case class Statement5(x0: BreakableStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 5
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BreakableStatement", x0, Nil).reverse
  val info: ASTInfo = Statement5
}
object Statement5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class Statement6(x0: ContinueStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 6
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ContinueStatement", x0, Nil).reverse
  val info: ASTInfo = Statement6
}
object Statement6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::Statement[6,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::Statement[6,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::Statement[6,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::Statement[6,0].ContainsUndefinedBreakTarget`,
    "LabelledEvaluation0" -> `AL::Statement[6,0].LabelledEvaluation`,
    "HasCallInTailPosition0" -> `AL::Statement[6,0].HasCallInTailPosition`,
  )
}

case class Statement7(x0: BreakStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 7
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BreakStatement", x0, Nil).reverse
  val info: ASTInfo = Statement7
}
object Statement7 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::Statement[7,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::Statement[7,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::Statement[7,0].ContainsDuplicateLabels`,
    "ContainsUndefinedContinueTarget0" -> `AL::Statement[7,0].ContainsUndefinedContinueTarget`,
    "LabelledEvaluation0" -> `AL::Statement[7,0].LabelledEvaluation`,
    "HasCallInTailPosition0" -> `AL::Statement[7,0].HasCallInTailPosition`,
  )
}

case class Statement8(x0: ReturnStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 8
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ReturnStatement", x0, Nil).reverse
  val info: ASTInfo = Statement8
}
object Statement8 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::Statement[8,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::Statement[8,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::Statement[8,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::Statement[8,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::Statement[8,0].ContainsUndefinedContinueTarget`,
    "LabelledEvaluation0" -> `AL::Statement[8,0].LabelledEvaluation`,
  )
}

case class Statement9(x0: WithStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 9
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("WithStatement", x0, Nil).reverse
  val info: ASTInfo = Statement9
}
object Statement9 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LabelledEvaluation0" -> `AL::Statement[9,0].LabelledEvaluation`,
  )
}

case class Statement10(x0: LabelledStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 10
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("LabelledStatement", x0, Nil).reverse
  val info: ASTInfo = Statement10
}
object Statement10 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class Statement11(x0: ThrowStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 11
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ThrowStatement", x0, Nil).reverse
  val info: ASTInfo = Statement11
}
object Statement11 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::Statement[11,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::Statement[11,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::Statement[11,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::Statement[11,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::Statement[11,0].ContainsUndefinedContinueTarget`,
    "LabelledEvaluation0" -> `AL::Statement[11,0].LabelledEvaluation`,
    "HasCallInTailPosition0" -> `AL::Statement[11,0].HasCallInTailPosition`,
  )
}

case class Statement12(x0: TryStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 12
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("TryStatement", x0, Nil).reverse
  val info: ASTInfo = Statement12
}
object Statement12 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LabelledEvaluation0" -> `AL::Statement[12,0].LabelledEvaluation`,
  )
}

case class Statement13(x0: DebuggerStatement, parserParams: List[Boolean], span: Span) extends Statement {
  x0.parent = Some(this)
  val idx: Int = 13
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("DebuggerStatement", x0, Nil).reverse
  val info: ASTInfo = Statement13
}
object Statement13 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::Statement[13,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::Statement[13,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::Statement[13,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::Statement[13,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::Statement[13,0].ContainsUndefinedContinueTarget`,
    "LabelledEvaluation0" -> `AL::Statement[13,0].LabelledEvaluation`,
    "HasCallInTailPosition0" -> `AL::Statement[13,0].HasCallInTailPosition`,
  )
}
