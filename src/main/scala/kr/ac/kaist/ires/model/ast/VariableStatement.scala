package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait VariableStatement extends AST {
  val kind: String = "VariableStatement"
}
object VariableStatement extends ASTHelper {
  def apply(v: JsValue): VariableStatement = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      VariableStatement0(VariableDeclarationList(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class VariableStatement0(x1: VariableDeclarationList, parserParams: List[Boolean], span: Span) extends VariableStatement {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"var $x1 ;"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("VariableDeclarationList", x1, Nil).reverse
  val info: ASTInfo = VariableStatement0
}
object VariableStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::VariableStatement[0,0].VarDeclaredNames`,
    "Evaluation0" -> `AL::VariableStatement[0,0].Evaluation`,
  )
}
