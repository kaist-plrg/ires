package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait DefaultClause extends AST {
  val kind: String = "DefaultClause"
}
object DefaultClause extends ASTHelper {
  def apply(v: JsValue): DefaultClause = v match {
    case JsSeq(JsInt(0), JsSeq(x2), JsBoolSeq(params), JsSpan(span)) =>
      DefaultClause0(opt(x2, StatementList.apply), params, span)
    case _ => throw InvalidAST
  }
}

case class DefaultClause0(x2: Option[StatementList], parserParams: List[Boolean], span: Span) extends DefaultClause {
  x2.foreach((m) => m.parent = Some(this))
  val idx: Int = 0
  override def toString: String = {
    s"default : ${x2.getOrElse("")}"
  }
  val k: Int = d(x2, 0)
  val fullList: List[(String, Value)] = l("Option[StatementList]", x2, Nil).reverse
  val info: ASTInfo = DefaultClause0
}
object DefaultClause0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames1" -> `AL::DefaultClause[0,1].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations1" -> `AL::DefaultClause[0,1].LexicallyScopedDeclarations`,
    "VarDeclaredNames1" -> `AL::DefaultClause[0,1].VarDeclaredNames`,
    "VarScopedDeclarations1" -> `AL::DefaultClause[0,1].VarScopedDeclarations`,
    "ContainsDuplicateLabels1" -> `AL::DefaultClause[0,1].ContainsDuplicateLabels`,
    "ContainsUndefinedBreakTarget1" -> `AL::DefaultClause[0,1].ContainsUndefinedBreakTarget`,
    "ContainsUndefinedContinueTarget1" -> `AL::DefaultClause[0,1].ContainsUndefinedContinueTarget`,
    "Evaluation0" -> `AL::DefaultClause[0,0].Evaluation`,
    "Evaluation1" -> `AL::DefaultClause[0,1].Evaluation`,
    "HasCallInTailPosition1" -> `AL::DefaultClause[0,1].HasCallInTailPosition`,
  )
}
