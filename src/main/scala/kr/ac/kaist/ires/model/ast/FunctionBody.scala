package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait FunctionBody extends AST {
  val kind: String = "FunctionBody"
}
object FunctionBody extends ASTHelper {
  def apply(v: JsValue): FunctionBody = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      FunctionBody0(FunctionStatementList(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class FunctionBody0(x0: FunctionStatementList, parserParams: List[Boolean], span: Span) extends FunctionBody {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FunctionStatementList", x0, Nil).reverse
  val info: ASTInfo = FunctionBody0
}
object FunctionBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "EvaluateBody0" -> `AL::FunctionBody[0,0].EvaluateBody`,
    "FunctionBodyContainsUseStrict0" -> `AL::FunctionBody[0,0].FunctionBodyContainsUseStrict`,
    "EvaluateFunctionBody0" -> `AL::FunctionBody[0,0].EvaluateFunctionBody`,
    "EarlyErrors0" -> `AL::FunctionBody[0,0].EarlyErrors`,
  )
}
