package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait VariableDeclaration extends AST {
  val kind: String = "VariableDeclaration"
}

case class VariableDeclaration0(x0: BindingIdentifier, x1: Option[Initializer], parserParams: List[Boolean]) extends VariableDeclaration {
  x0.parent = Some(this)
  x1.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"$x0 ${x1.getOrElse("")}"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Option[Initializer]", x1, l("BindingIdentifier", x0, Nil)).reverse
  val info: ASTInfo = VariableDeclaration0
}
object VariableDeclaration0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "BoundNames1" -> `AL::VariableDeclaration[0,1].BoundNames`,
    "Evaluation0" -> `AL::VariableDeclaration[0,0].Evaluation`,
    "Evaluation1" -> `AL::VariableDeclaration[0,1].Evaluation`,
  )
}

case class VariableDeclaration1(x0: BindingPattern, x1: Initializer, parserParams: List[Boolean]) extends VariableDeclaration {
  x0.parent = Some(this)
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0 $x1"
  }
  val k: Int = d(x1, d(x0, 0))
  val fullList: List[(String, Value)] = l("Initializer", x1, l("BindingPattern", x0, Nil)).reverse
  val info: ASTInfo = VariableDeclaration1
}
object VariableDeclaration1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::VariableDeclaration[1,0].BoundNames`,
    "Evaluation0" -> `AL::VariableDeclaration[1,0].Evaluation`,
  )
}
