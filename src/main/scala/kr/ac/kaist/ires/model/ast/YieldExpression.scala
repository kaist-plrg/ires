package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait YieldExpression extends AST {
  val kind: String = "YieldExpression"
}
object YieldExpression extends ASTHelper {
  def apply(v: JsValue): YieldExpression = v match {
    case JsSeq(JsInt(0), JsSeq(), JsBoolSeq(params), JsSpan(span)) =>
      YieldExpression0(params, span)
    case JsSeq(JsInt(1), JsSeq(x2), JsBoolSeq(params), JsSpan(span)) =>
      YieldExpression1(AssignmentExpression(x2), params, span)
    case JsSeq(JsInt(2), JsSeq(x3), JsBoolSeq(params), JsSpan(span)) =>
      YieldExpression2(AssignmentExpression(x3), params, span)
    case _ => throw InvalidAST
  }
}

case class YieldExpression0(parserParams: List[Boolean], span: Span) extends YieldExpression {
  val idx: Int = 0
  override def toString: String = {
    s"yield"
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = YieldExpression0
}
object YieldExpression0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::YieldExpression[0,0].Evaluation`,
  )
}

case class YieldExpression1(x2: AssignmentExpression, parserParams: List[Boolean], span: Span) extends YieldExpression {
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"yield $x2"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("AssignmentExpression", x2, Nil).reverse
  val info: ASTInfo = YieldExpression1
}
object YieldExpression1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::YieldExpression[1,0].Evaluation`,
  )
}

case class YieldExpression2(x3: AssignmentExpression, parserParams: List[Boolean], span: Span) extends YieldExpression {
  x3.parent = Some(this)
  val idx: Int = 2
  override def toString: String = {
    s"yield * $x3"
  }
  val k: Int = d(x3, 0)
  val fullList: List[(String, Value)] = l("AssignmentExpression", x3, Nil).reverse
  val info: ASTInfo = YieldExpression2
}
object YieldExpression2 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::YieldExpression[2,0].Evaluation`,
  )
}
