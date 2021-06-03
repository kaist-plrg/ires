package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait ArrayAssignmentPattern extends AST {
  val kind: String = "ArrayAssignmentPattern"
}

case class ArrayAssignmentPattern0(x1: Option[Elision], x2: Option[AssignmentRestElement], parserParams: List[Boolean]) extends ArrayAssignmentPattern {
  x1.foreach((m) => m.parent = Some(this))
  x2.foreach((m) => m.parent = Some(this))
  val name: String = "ArrayAssignmentPattern0"
  override def toString: String = {
    s"[ ${x1.getOrElse("")} ${x2.getOrElse("")} ]"
  }
  val k: Int = d(x2, d(x1, 0))
  val fullList: List[(String, Value)] = l("Option[AssignmentRestElement]", x2, l("Option[Elision]", x1, Nil)).reverse
  val info: ASTInfo = ArrayAssignmentPattern0
}
object ArrayAssignmentPattern0 extends ASTInfo {
  val maxK: Int = 3
  val semMap: Map[String, Algo] = Map(
    "DestructuringAssignmentEvaluation0" -> `AL::ArrayAssignmentPattern[0,0].DestructuringAssignmentEvaluation`,
    "DestructuringAssignmentEvaluation2" -> `AL::ArrayAssignmentPattern[0,2].DestructuringAssignmentEvaluation`,
    "DestructuringAssignmentEvaluation3" -> `AL::ArrayAssignmentPattern[0,3].DestructuringAssignmentEvaluation`,
  )
}

case class ArrayAssignmentPattern1(x1: AssignmentElementList, parserParams: List[Boolean]) extends ArrayAssignmentPattern {
  x1.parent = Some(this)
  val name: String = "ArrayAssignmentPattern1"
  override def toString: String = {
    s"[ $x1 ]"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("AssignmentElementList", x1, Nil).reverse
  val info: ASTInfo = ArrayAssignmentPattern1
}
object ArrayAssignmentPattern1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "DestructuringAssignmentEvaluation0" -> `AL::ArrayAssignmentPattern[1,0].DestructuringAssignmentEvaluation`,
  )
}

case class ArrayAssignmentPattern2(x1: AssignmentElementList, x3: Option[Elision], x4: Option[AssignmentRestElement], parserParams: List[Boolean]) extends ArrayAssignmentPattern {
  x1.parent = Some(this)
  x3.foreach((m) => m.parent = Some(this))
  x4.foreach((m) => m.parent = Some(this))
  val name: String = "ArrayAssignmentPattern2"
  override def toString: String = {
    s"[ $x1 , ${x3.getOrElse("")} ${x4.getOrElse("")} ]"
  }
  val k: Int = d(x4, d(x3, d(x1, 0)))
  val fullList: List[(String, Value)] = l("Option[AssignmentRestElement]", x4, l("Option[Elision]", x3, l("AssignmentElementList", x1, Nil))).reverse
  val info: ASTInfo = ArrayAssignmentPattern2
}
object ArrayAssignmentPattern2 extends ASTInfo {
  val maxK: Int = 3
  val semMap: Map[String, Algo] = Map(
    "DestructuringAssignmentEvaluation3" -> `AL::ArrayAssignmentPattern[2,3].DestructuringAssignmentEvaluation`,
  )
}
