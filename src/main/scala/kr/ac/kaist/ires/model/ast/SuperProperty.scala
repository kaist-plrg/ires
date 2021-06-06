package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait SuperProperty extends AST {
  val kind: String = "SuperProperty"
}

case class SuperProperty0(x2: Expression, parserParams: List[Boolean]) extends SuperProperty {
  x2.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"super [ $x2 ]"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("Expression", x2, Nil).reverse
  val info: ASTInfo = SuperProperty0
}
object SuperProperty0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Evaluation0" -> `AL::SuperProperty[0,0].Evaluation`,
  )
}

case class SuperProperty1(x2: Lexical, parserParams: List[Boolean]) extends SuperProperty {
  x2.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"super . $x2"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("Lexical", x2, Nil).reverse
  val info: ASTInfo = SuperProperty1
}
object SuperProperty1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "Contains0" -> `AL::SuperProperty[1,0].Contains`,
    "Evaluation0" -> `AL::SuperProperty[1,0].Evaluation`,
  )
}
