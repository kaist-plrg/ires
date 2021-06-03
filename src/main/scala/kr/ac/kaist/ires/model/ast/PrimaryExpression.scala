package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait PrimaryExpression extends AST {
  val kind: String = "PrimaryExpression"
}

case class PrimaryExpression0(parserParams: List[Boolean]) extends PrimaryExpression {
  val name: String = "PrimaryExpression0"
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

case class PrimaryExpression1(x0: IdentifierReference, parserParams: List[Boolean]) extends PrimaryExpression {
  x0.parent = Some(this)
  val name: String = "PrimaryExpression1"
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

case class PrimaryExpression2(x0: Literal, parserParams: List[Boolean]) extends PrimaryExpression {
  x0.parent = Some(this)
  val name: String = "PrimaryExpression2"
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

case class PrimaryExpression3(x0: ArrayLiteral, parserParams: List[Boolean]) extends PrimaryExpression {
  x0.parent = Some(this)
  val name: String = "PrimaryExpression3"
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

case class PrimaryExpression4(x0: ObjectLiteral, parserParams: List[Boolean]) extends PrimaryExpression {
  x0.parent = Some(this)
  val name: String = "PrimaryExpression4"
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

case class PrimaryExpression5(x0: FunctionExpression, parserParams: List[Boolean]) extends PrimaryExpression {
  x0.parent = Some(this)
  val name: String = "PrimaryExpression5"
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

case class PrimaryExpression6(x0: ClassExpression, parserParams: List[Boolean]) extends PrimaryExpression {
  x0.parent = Some(this)
  val name: String = "PrimaryExpression6"
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

case class PrimaryExpression7(x0: GeneratorExpression, parserParams: List[Boolean]) extends PrimaryExpression {
  x0.parent = Some(this)
  val name: String = "PrimaryExpression7"
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

case class PrimaryExpression8(x0: AsyncFunctionExpression, parserParams: List[Boolean]) extends PrimaryExpression {
  x0.parent = Some(this)
  val name: String = "PrimaryExpression8"
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

case class PrimaryExpression9(x0: AsyncGeneratorExpression, parserParams: List[Boolean]) extends PrimaryExpression {
  x0.parent = Some(this)
  val name: String = "PrimaryExpression9"
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

case class PrimaryExpression10(x0: Lexical, parserParams: List[Boolean]) extends PrimaryExpression {
  x0.parent = Some(this)
  val name: String = "PrimaryExpression10"
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

case class PrimaryExpression11(x0: TemplateLiteral, parserParams: List[Boolean]) extends PrimaryExpression {
  x0.parent = Some(this)
  val name: String = "PrimaryExpression11"
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

case class PrimaryExpression12(x0: CoverParenthesizedExpressionAndArrowParameterList, parserParams: List[Boolean]) extends PrimaryExpression {
  x0.parent = Some(this)
  val name: String = "PrimaryExpression12"
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
