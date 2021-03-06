package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AsyncGeneratorExpression extends AST {
  val kind: String = "AsyncGeneratorExpression"
}
object AsyncGeneratorExpression extends ASTHelper {
  def apply(v: JsValue): AsyncGeneratorExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x4, x6, x9), JsBoolSeq(params), JsSpan(span)) =>
      AsyncGeneratorExpression0(opt(x4, BindingIdentifier.apply), FormalParameters(x6), AsyncGeneratorBody(x9), params, span)
    case _ => throw InvalidAST
  }
}

case class AsyncGeneratorExpression0(x4: Option[BindingIdentifier], x6: FormalParameters, x9: AsyncGeneratorBody, parserParams: List[Boolean], span: Span) extends AsyncGeneratorExpression {
  x4.foreach((m) => m.parent = Some(this))
  x6.parent = Some(this)
  x9.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"async function * ${x4.getOrElse("")} ( $x6 ) { $x9 }"
  }
  val k: Int = d(x9, d(x6, d(x4, 0)))
  val fullList: List[(String, Value)] = l("AsyncGeneratorBody", x9, l("FormalParameters", x6, l("Option[BindingIdentifier]", x4, Nil))).reverse
  val info: ASTInfo = AsyncGeneratorExpression0
}
object AsyncGeneratorExpression0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "HasName0" -> `AL::AsyncGeneratorExpression[0,0].HasName`,
    "HasName1" -> `AL::AsyncGeneratorExpression[0,1].HasName`,
    "IsFunctionDefinition1" -> `AL::AsyncGeneratorExpression[0,1].IsFunctionDefinition`,
    "NamedEvaluation0" -> `AL::AsyncGeneratorExpression[0,0].NamedEvaluation`,
    "Contains1" -> `AL::AsyncGeneratorExpression[0,1].Contains`,
    "InstantiateAsyncGeneratorFunctionExpression0" -> `AL::AsyncGeneratorExpression[0,0].InstantiateAsyncGeneratorFunctionExpression`,
    "InstantiateAsyncGeneratorFunctionExpression1" -> `AL::AsyncGeneratorExpression[0,1].InstantiateAsyncGeneratorFunctionExpression`,
    "Evaluation1" -> `AL::AsyncGeneratorExpression[0,1].Evaluation`,
    "EarlyErrors1" -> `AL::AsyncGeneratorExpression[0,1].EarlyErrors`,
  )
}
