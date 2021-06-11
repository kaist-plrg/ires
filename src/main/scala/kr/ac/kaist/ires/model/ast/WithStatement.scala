package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait WithStatement extends AST {
  val kind: String = "WithStatement"
}
object WithStatement extends ASTHelper {
  def apply(v: JsValue): WithStatement = v match {
    case JsSeq(JsInt(0), JsSeq(x2, x4), JsBoolSeq(params), JsSpan(span)) =>
      WithStatement0(Expression(x2), Statement(x4), params)
    case _ => throw InvalidAST
  }
}

case class WithStatement0(x2: Expression, x4: Statement, parserParams: List[Boolean]) extends WithStatement {
  x2.parent = Some(this)
  x4.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"with ( $x2 ) $x4"
  }
  val k: Int = d(x4, d(x2, 0))
  val fullList: List[(String, Value)] = l("Statement", x4, l("Expression", x2, Nil)).reverse
  val info: ASTInfo = WithStatement0
}
object WithStatement0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "VarDeclaredNames0" -> `AL::WithStatement[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::WithStatement[0,0].VarScopedDeclarations`,
    "ContainsDuplicateLabels0" -> `AL::WithStatement[0,0].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget0" -> `AL::WithStatement[0,0].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget0" -> `AL::WithStatement[0,0].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::WithStatement[0,0].Evaluation`,
    "HasCallInTailPosition0" -> `AL::WithStatement[0,0].HasCallInTailPosition`,
    "EarlyErrors0" -> `AL::WithStatement[0,0].EarlyErrors`,
  )
}
