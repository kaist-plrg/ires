package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait BitwiseXORExpression extends AST {
  val kind: String = "BitwiseXORExpression"
}
object BitwiseXORExpression extends ASTHelper {
  def apply(v: JsValue): BitwiseXORExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      BitwiseXORExpression0(BitwiseANDExpression(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      BitwiseXORExpression1(BitwiseXORExpression(x0), BitwiseANDExpression(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class BitwiseXORExpression0(x0: BitwiseANDExpression, parserParams: List[Boolean], span: Span) extends BitwiseXORExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BitwiseANDExpression", x0, Nil).reverse
  val info: ASTInfo = BitwiseXORExpression0
}
object BitwiseXORExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class BitwiseXORExpression1(x0: BitwiseXORExpression, x2: BitwiseANDExpression, parserParams: List[Boolean], span: Span) extends BitwiseXORExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 ^ $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("BitwiseANDExpression", x2, l("BitwiseXORExpression", x0, Nil)).reverse
  val info: ASTInfo = BitwiseXORExpression1
}
object BitwiseXORExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::BitwiseXORExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::BitwiseXORExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::BitwiseXORExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::BitwiseXORExpression[1,0].HasCallInTailPosition`,
  )
}
