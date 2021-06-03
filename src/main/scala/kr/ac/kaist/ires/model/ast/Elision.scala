package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait Elision extends AST {
  val kind: String = "Elision"
}

case class Elision0(parserParams: List[Boolean]) extends Elision {
  val name: String = "Elision0"
  override def toString: String = {
    s","
  }
  val k: Int = 0
  val fullList: List[(String, Value)] = Nil.reverse
  val info: ASTInfo = Elision0
}
object Elision0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ArrayAccumulation0" -> `AL::Elision[0,0].ArrayAccumulation`,
    "IteratorDestructuringAssignmentEvaluation0" -> `AL::Elision[0,0].IteratorDestructuringAssignmentEvaluation`,
  )
}

case class Elision1(x0: Elision, parserParams: List[Boolean]) extends Elision {
  x0.parent = Some(this)
  val name: String = "Elision1"
  override def toString: String = {
    s"$x0 ,"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("Elision", x0, Nil).reverse
  val info: ASTInfo = Elision1
}
object Elision1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ArrayAccumulation0" -> `AL::Elision[1,0].ArrayAccumulation`,
    "IteratorDestructuringAssignmentEvaluation0" -> `AL::Elision[1,0].IteratorDestructuringAssignmentEvaluation`,
  )
}
