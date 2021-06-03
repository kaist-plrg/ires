package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait VariableDeclarationList extends AST {
  val kind: String = "VariableDeclarationList"
}

case class VariableDeclarationList0(x0: VariableDeclaration, parserParams: List[Boolean]) extends VariableDeclarationList {
  x0.parent = Some(this)
  val name: String = "VariableDeclarationList0"
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("VariableDeclaration", x0, Nil).reverse
  val info: ASTInfo = VariableDeclarationList0
}
object VariableDeclarationList0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarScopedDeclarations0" -> `AL::VariableDeclarationList[0,0].VarScopedDeclarations`,
  )
}

case class VariableDeclarationList1(x0: VariableDeclarationList, x2: VariableDeclaration, parserParams: List[Boolean]) extends VariableDeclarationList {
  x0.parent = Some(this)
  x2.parent = Some(this)
  val name: String = "VariableDeclarationList1"
  override def toString: String = {
    s"$x0 , $x2"
  }
  val k: Int = d(x2, d(x0, 0))
  val fullList: List[(String, Value)] = l("VariableDeclaration", x2, l("VariableDeclarationList", x0, Nil)).reverse
  val info: ASTInfo = VariableDeclarationList1
}
object VariableDeclarationList1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::VariableDeclarationList[1,0].BoundNames`,
    "VarScopedDeclarations0" -> `AL::VariableDeclarationList[1,0].VarScopedDeclarations`,
    "Evaluation0" -> `AL::VariableDeclarationList[1,0].Evaluation`,
  )
}
