package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait Expression extends AST {
  val kind: String = "Expression"
}
object Expression extends ASTHelper {
  def apply(v: JsValue): Expression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      Expression0(AssignmentExpression(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      Expression1(Expression(x0), AssignmentExpression(x2), params)
    case _ => throw InvalidAST
  }
}

case class Expression0(x0: AssignmentExpression, parserParams: List[Boolean]) extends Expression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AssignmentExpression", x0, Nil).reverse
  val info: ASTInfo = Expression0
}
object Expression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "HasCallInTailPosition0" -> `AL::Expression[0,0].HasCallInTailPosition`,
  )
}

case class Expression1(x0: Expression, x2: AssignmentExpression, parserParams: List[Boolean]) extends Expression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentExpression", x2, l("Expression", x0, Nil)).reverse
  val info: ASTInfo = Expression1
}
object Expression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::Expression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::Expression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::Expression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::Expression[1,0].HasCallInTailPosition`,
  )
}
