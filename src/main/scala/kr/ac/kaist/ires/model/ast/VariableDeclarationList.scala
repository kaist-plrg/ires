package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait VariableDeclarationList extends AST {
  val kind: String = "VariableDeclarationList"
}
object VariableDeclarationList extends ASTHelper {
  def apply(v: JsValue): VariableDeclarationList = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      VariableDeclarationList0(VariableDeclaration(x0), params)
    case JsSeq(JsInt(1), JsSeq(x0, x2), JsBoolSeq(params), JsSpan(span)) =>
      VariableDeclarationList1(VariableDeclarationList(x0), VariableDeclaration(x2), params)
    case _ => throw InvalidAST
  }
}

case class VariableDeclarationList0(x0: VariableDeclaration, parserParams: List[Boolean]) extends VariableDeclarationList {
  x0.parent = Some(this)
  val idx: Int = 0
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
  val idx: Int = 1
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
