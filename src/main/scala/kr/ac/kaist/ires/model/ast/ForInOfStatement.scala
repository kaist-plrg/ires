package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ForInOfStatement extends AST {
  val kind: String = "ForInOfStatement"
}

case class ForInOfStatement0(x3: LeftHandSideExpression, x5: Expression, x7: Statement, parserParams: List[Boolean]) extends ForInOfStatement {
  x3.parent = Some(this)
  x5.parent = Some(this)
  x7.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"for ( $x3 in $x5 ) $x7"
  }
  val k: Int = d(x7, d(x5, d(x3, 0)))
  val fullList: List[(String, Value)] = l("Statement", x7, l("Expression", x5, l("LeftHandSideExpression", x3, Nil))).reverse
  val info: ASTInfo = ForInOfStatement0
}
object ForInOfStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::ForInOfStatement[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ForInOfStatement[0,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::ForInOfStatement[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::ForInOfStatement[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::ForInOfStatement[0,0].ContainsUndefinedContinueTarget`,
    "ForInOfLoopEvaluation0" -> `AL::ForInOfStatement[0,0].ForInOfLoopEvaluation`,
    "HasCallInTailPosition0" -> `AL::ForInOfStatement[0,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::ForInOfStatement[0,0].EarlyErrors`,
  )
}

case class ForInOfStatement1(x3: ForBinding, x5: Expression, x7: Statement, parserParams: List[Boolean]) extends ForInOfStatement {
  x3.parent = Some(this)
  x5.parent = Some(this)
  x7.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"for ( var $x3 in $x5 ) $x7"
  }
  val k: Int = d(x7, d(x5, d(x3, 0)))
  val fullList: List[(String, Value)] = l("Statement", x7, l("Expression", x5, l("ForBinding", x3, Nil))).reverse
  val info: ASTInfo = ForInOfStatement1
}
object ForInOfStatement1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::ForInOfStatement[1,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ForInOfStatement[1,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::ForInOfStatement[1,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::ForInOfStatement[1,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::ForInOfStatement[1,0].ContainsUndefinedContinueTarget`,
    "ForInOfLoopEvaluation0" -> `AL::ForInOfStatement[1,0].ForInOfLoopEvaluation`,
    "HasCallInTailPosition0" -> `AL::ForInOfStatement[1,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::ForInOfStatement[1,0].EarlyErrors`,
  )
}

case class ForInOfStatement2(x2: ForDeclaration, x4: Expression, x6: Statement, parserParams: List[Boolean]) extends ForInOfStatement {
  x2.parent = Some(this)
  x4.parent = Some(this)
  x6.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"for ( $x2 in $x4 ) $x6"
  }
  val k: Int = d(x6, d(x4, d(x2, 0)))
  val fullList: List[(String, Value)] = l("Statement", x6, l("Expression", x4, l("ForDeclaration", x2, Nil))).reverse
  val info: ASTInfo = ForInOfStatement2
}
object ForInOfStatement2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::ForInOfStatement[2,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ForInOfStatement[2,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::ForInOfStatement[2,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::ForInOfStatement[2,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::ForInOfStatement[2,0].ContainsUndefinedContinueTarget`,
    "ForInOfLoopEvaluation0" -> `AL::ForInOfStatement[2,0].ForInOfLoopEvaluation`,
    "HasCallInTailPosition0" -> `AL::ForInOfStatement[2,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::ForInOfStatement[2,0].EarlyErrors`,
    "EarlyErrors0" -> `AL::ForInOfStatement[2,0].EarlyErrors`,
  )
}

case class ForInOfStatement3(x3: LeftHandSideExpression, x5: AssignmentExpression, x7: Statement, parserParams: List[Boolean]) extends ForInOfStatement {
  x3.parent = Some(this)
  x5.parent = Some(this)
  x7.parent = Some(this)
  val idx: Int = 3
  override def toString: String = {
    s"for ( $x3 of $x5 ) $x7"
  }
  val k: Int = d(x7, d(x5, d(x3, 0)))
  val fullList: List[(String, Value)] = l("Statement", x7, l("AssignmentExpression", x5, l("LeftHandSideExpression", x3, Nil))).reverse
  val info: ASTInfo = ForInOfStatement3
}
object ForInOfStatement3 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::ForInOfStatement[3,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ForInOfStatement[3,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::ForInOfStatement[3,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::ForInOfStatement[3,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::ForInOfStatement[3,0].ContainsUndefinedContinueTarget`,
    "ForInOfLoopEvaluation0" -> `AL::ForInOfStatement[3,0].ForInOfLoopEvaluation`,
    "HasCallInTailPosition0" -> `AL::ForInOfStatement[3,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::ForInOfStatement[3,0].EarlyErrors`,
  )
}

case class ForInOfStatement4(x3: ForBinding, x5: AssignmentExpression, x7: Statement, parserParams: List[Boolean]) extends ForInOfStatement {
  x3.parent = Some(this)
  x5.parent = Some(this)
  x7.parent = Some(this)
  val idx: Int = 4
  override def toString: String = {
    s"for ( var $x3 of $x5 ) $x7"
  }
  val k: Int = d(x7, d(x5, d(x3, 0)))
  val fullList: List[(String, Value)] = l("Statement", x7, l("AssignmentExpression", x5, l("ForBinding", x3, Nil))).reverse
  val info: ASTInfo = ForInOfStatement4
}
object ForInOfStatement4 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::ForInOfStatement[4,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ForInOfStatement[4,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::ForInOfStatement[4,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::ForInOfStatement[4,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::ForInOfStatement[4,0].ContainsUndefinedContinueTarget`,
    "ForInOfLoopEvaluation0" -> `AL::ForInOfStatement[4,0].ForInOfLoopEvaluation`,
    "HasCallInTailPosition0" -> `AL::ForInOfStatement[4,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::ForInOfStatement[4,0].EarlyErrors`,
  )
}

case class ForInOfStatement5(x2: ForDeclaration, x4: AssignmentExpression, x6: Statement, parserParams: List[Boolean]) extends ForInOfStatement {
  x2.parent = Some(this)
  x4.parent = Some(this)
  x6.parent = Some(this)
  val idx: Int = 5
  override def toString: String = {
    s"for ( $x2 of $x4 ) $x6"
  }
  val k: Int = d(x6, d(x4, d(x2, 0)))
  val fullList: List[(String, Value)] = l("Statement", x6, l("AssignmentExpression", x4, l("ForDeclaration", x2, Nil))).reverse
  val info: ASTInfo = ForInOfStatement5
}
object ForInOfStatement5 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::ForInOfStatement[5,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ForInOfStatement[5,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::ForInOfStatement[5,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::ForInOfStatement[5,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::ForInOfStatement[5,0].ContainsUndefinedContinueTarget`,
    "ForInOfLoopEvaluation0" -> `AL::ForInOfStatement[5,0].ForInOfLoopEvaluation`,
    "HasCallInTailPosition0" -> `AL::ForInOfStatement[5,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::ForInOfStatement[5,0].EarlyErrors`,
    "EarlyErrors0" -> `AL::ForInOfStatement[5,0].EarlyErrors`,
  )
}

case class ForInOfStatement6(x4: LeftHandSideExpression, x6: AssignmentExpression, x8: Statement, parserParams: List[Boolean]) extends ForInOfStatement {
  x4.parent = Some(this)
  x6.parent = Some(this)
  x8.parent = Some(this)
  val idx: Int = 6
  override def toString: String = {
    s"for await ( $x4 of $x6 ) $x8"
  }
  val k: Int = d(x8, d(x6, d(x4, 0)))
  val fullList: List[(String, Value)] = l("Statement", x8, l("AssignmentExpression", x6, l("LeftHandSideExpression", x4, Nil))).reverse
  val info: ASTInfo = ForInOfStatement6
}
object ForInOfStatement6 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::ForInOfStatement[6,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ForInOfStatement[6,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::ForInOfStatement[6,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::ForInOfStatement[6,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::ForInOfStatement[6,0].ContainsUndefinedContinueTarget`,
    "ForInOfLoopEvaluation0" -> `AL::ForInOfStatement[6,0].ForInOfLoopEvaluation`,
    "HasCallInTailPosition0" -> `AL::ForInOfStatement[6,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::ForInOfStatement[6,0].EarlyErrors`,
  )
}

case class ForInOfStatement7(x4: ForBinding, x6: AssignmentExpression, x8: Statement, parserParams: List[Boolean]) extends ForInOfStatement {
  x4.parent = Some(this)
  x6.parent = Some(this)
  x8.parent = Some(this)
  val idx: Int = 7
  override def toString: String = {
    s"for await ( var $x4 of $x6 ) $x8"
  }
  val k: Int = d(x8, d(x6, d(x4, 0)))
  val fullList: List[(String, Value)] = l("Statement", x8, l("AssignmentExpression", x6, l("ForBinding", x4, Nil))).reverse
  val info: ASTInfo = ForInOfStatement7
}
object ForInOfStatement7 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::ForInOfStatement[7,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ForInOfStatement[7,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::ForInOfStatement[7,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::ForInOfStatement[7,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::ForInOfStatement[7,0].ContainsUndefinedContinueTarget`,
    "ForInOfLoopEvaluation0" -> `AL::ForInOfStatement[7,0].ForInOfLoopEvaluation`,
    "HasCallInTailPosition0" -> `AL::ForInOfStatement[7,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::ForInOfStatement[7,0].EarlyErrors`,
  )
}

case class ForInOfStatement8(x3: ForDeclaration, x5: AssignmentExpression, x7: Statement, parserParams: List[Boolean]) extends ForInOfStatement {
  x3.parent = Some(this)
  x5.parent = Some(this)
  x7.parent = Some(this)
  val idx: Int = 8
  override def toString: String = {
    s"for await ( $x3 of $x5 ) $x7"
  }
  val k: Int = d(x7, d(x5, d(x3, 0)))
  val fullList: List[(String, Value)] = l("Statement", x7, l("AssignmentExpression", x5, l("ForDeclaration", x3, Nil))).reverse
  val info: ASTInfo = ForInOfStatement8
}
object ForInOfStatement8 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::ForInOfStatement[8,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ForInOfStatement[8,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::ForInOfStatement[8,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::ForInOfStatement[8,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::ForInOfStatement[8,0].ContainsUndefinedContinueTarget`,
    "ForInOfLoopEvaluation0" -> `AL::ForInOfStatement[8,0].ForInOfLoopEvaluation`,
    "HasCallInTailPosition0" -> `AL::ForInOfStatement[8,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::ForInOfStatement[8,0].EarlyErrors`,
    "EarlyErrors0" -> `AL::ForInOfStatement[8,0].EarlyErrors`,
  )
}
