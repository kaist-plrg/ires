package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ConciseBody extends AST {
  val kind: String = "ConciseBody"
}
object ConciseBody extends ASTHelper {
  def apply(v: JsValue): ConciseBody = v match {
    case JsSeq(JsInt(0), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ConciseBody0(ExpressionBody(x1), params, span)
    case JsSeq(JsInt(1), JsSeq(x1), JsBoolSeq(params), JsSpan(span)) =>
      ConciseBody1(FunctionBody(x1), params, span)
    case _ => throw InvalidAST
  }
}

case class ConciseBody0(x1: ExpressionBody, parserParams: List[Boolean], span: Span) extends ConciseBody {
  x1.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x1"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("ExpressionBody", x1, Nil).reverse
  val info: ASTInfo = ConciseBody0
}
object ConciseBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "LexicallyDeclaredNames0" -> `AL::ConciseBody[0,0].LexicallyDeclaredNames`,
    "LexicallyScopedDeclarations0" -> `AL::ConciseBody[0,0].LexicallyScopedDeclarations`,
    "VarDeclaredNames0" -> `AL::ConciseBody[0,0].VarDeclaredNames`,
    "VarScopedDeclarations0" -> `AL::ConciseBody[0,0].VarScopedDeclarations`,
    "EvaluateBody0" -> `AL::ConciseBody[0,0].EvaluateBody`,
    "ConciseBodyContainsUseStrict0" -> `AL::ConciseBody[0,0].ConciseBodyContainsUseStrict`,
    "EvaluateConciseBody0" -> `AL::ConciseBody[0,0].EvaluateConciseBody`,
  )
}

case class ConciseBody1(x1: FunctionBody, parserParams: List[Boolean], span: Span) extends ConciseBody {
  x1.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"{ $x1 }"
  }
  val k: Int = d(x1, 0)
  val fullList: List[(String, Value)] = l("FunctionBody", x1, Nil).reverse
  val info: ASTInfo = ConciseBody1
}
object ConciseBody1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "ConciseBodyContainsUseStrict0" -> `AL::ConciseBody[1,0].ConciseBodyContainsUseStrict`,
  )
}
