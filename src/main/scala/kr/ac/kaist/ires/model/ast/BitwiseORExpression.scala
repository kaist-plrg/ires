package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait BitwiseORExpression extends AST {
  val kind: String = "BitwiseORExpression"
}
object BitwiseORExpression extends ASTHelper {
  def apply(v: JsValue): BitwiseORExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      BitwiseORExpression0(BitwiseXORExpression(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      BitwiseORExpression1(BitwiseORExpression(x0), BitwiseXORExpression(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class BitwiseORExpression0(x0: BitwiseXORExpression, parserParams: List[Boolean], span: Span) extends BitwiseORExpression {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BitwiseXORExpression", x0, Nil).reverse
  val info: ASTInfo = BitwiseORExpression0
}
object BitwiseORExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class BitwiseORExpression1(x0: BitwiseORExpression, x2: BitwiseXORExpression, parserParams: List[Boolean], span: Span) extends BitwiseORExpression {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 | $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("BitwiseXORExpression", x2, l("BitwiseORExpression", x0, Nil)).reverse
  val info: ASTInfo = BitwiseORExpression1
}
object BitwiseORExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IsFunctionDefinition0" -> `AL::BitwiseORExpression[1,0].IsFunctionDefinition`,
    "AssignmentTargetType0" -> `AL::BitwiseORExpression[1,0].AssignmentTargetType`,
    "Evaluation0" -> `AL::BitwiseORExpression[1,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::BitwiseORExpression[1,0].HasCallInTailPosition`,
  )
}
