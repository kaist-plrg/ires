package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AssignmentPattern extends AST {
  val kind: String = "AssignmentPattern"
}
object AssignmentPattern extends ASTHelper {
  def apply(v: JsValue): AssignmentPattern = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentPattern0(ObjectAssignmentPattern(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentPattern1(ArrayAssignmentPattern(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class AssignmentPattern0(x0: ObjectAssignmentPattern, parserParams: List[Boolean], span: Span) extends AssignmentPattern {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ObjectAssignmentPattern", x0, Nil).reverse
  val info: ASTInfo = AssignmentPattern0
}
object AssignmentPattern0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentPattern1(x0: ArrayAssignmentPattern, parserParams: List[Boolean], span: Span) extends AssignmentPattern {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("ArrayAssignmentPattern", x0, Nil).reverse
  val info: ASTInfo = AssignmentPattern1
}
object AssignmentPattern1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}
