package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AssignmentElementList extends AST {
  val kind: String = "AssignmentElementList"
}
object AssignmentElementList extends ASTHelper {
  def apply(v: JsValue): AssignmentElementList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentElementList0(AssignmentElisionElement(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentElementList1(AssignmentElementList(x0), AssignmentElisionElement(x2), params)
    case _ => throw InvalidAST
  }
}

case class AssignmentElementList0(x0: AssignmentElisionElement, parserParams: List[Boolean]) extends AssignmentElementList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AssignmentElisionElement", x0, Nil).reverse
  val info: ASTInfo = AssignmentElementList0
}
object AssignmentElementList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IteratorDestructuringAssignmentEvaluation0" -> `AL::AssignmentElementList[0,0].IteratorDestructuringAssignmentEvaluation`,
  )
}

case class AssignmentElementList1(x0: AssignmentElementList, x2: AssignmentElisionElement, parserParams: List[Boolean]) extends AssignmentElementList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentElisionElement", x2, l("AssignmentElementList", x0, Nil)).reverse
  val info: ASTInfo = AssignmentElementList1
}
object AssignmentElementList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IteratorDestructuringAssignmentEvaluation0" -> `AL::AssignmentElementList[1,0].IteratorDestructuringAssignmentEvaluation`,
  )
}
