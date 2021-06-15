package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AssignmentPropertyList extends AST {
  val kind: String = "AssignmentPropertyList"
}
object AssignmentPropertyList extends ASTHelper {
  def apply(v: JsValue): AssignmentPropertyList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentPropertyList0(AssignmentProperty(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      AssignmentPropertyList1(AssignmentPropertyList(x0), AssignmentProperty(x2), params, span)
    case _ => throw InvalidAST
  }
}

case class AssignmentPropertyList0(x0: AssignmentProperty, parserParams: List[Boolean], span: Span) extends AssignmentPropertyList {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("AssignmentProperty", x0, Nil).reverse
  val info: ASTInfo = AssignmentPropertyList0
}
object AssignmentPropertyList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map()
}

case class AssignmentPropertyList1(x0: AssignmentPropertyList, x2: AssignmentProperty, parserParams: List[Boolean], span: Span) extends AssignmentPropertyList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("AssignmentProperty", x2, l("AssignmentPropertyList", x0, Nil)).reverse
  val info: ASTInfo = AssignmentPropertyList1
}
object AssignmentPropertyList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "PropertyDestructuringAssignmentEvaluation0" -> `AL::AssignmentPropertyList[1,0].PropertyDestructuringAssignmentEvaluation`,
  )
}
