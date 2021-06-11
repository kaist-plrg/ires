package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait GeneratorExpression extends AST {
  val kind: String = "GeneratorExpression"
}
object GeneratorExpression extends ASTHelper {
  def apply(v: JsValue): GeneratorExpression = v match {
    case JsSeq(JsInt(0), JsSeq(x2, x4, x7), JsBoolSeq(params), JsSpan(span)) =>
      GeneratorExpression0(opt(x2, BindingIdentifier.apply), FormalParameters(x4), GeneratorBody(x7), params, span)
    case _ => throw InvalidAST
  }
}

case class GeneratorExpression0(x2: Option[BindingIdentifier], x4: FormalParameters, x7: GeneratorBody, parserParams: List[Boolean], span: Span) extends GeneratorExpression {
  x2.foreach((m) => m.parent = Some(this))
  x4.parent = Some(this)
  x7.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"function * ${x2.getOrElse("")} ( $x4 ) { $x7 }"
  }
  val k: Int = d(x7, d(x4, d(x2, 0)))
  val fullList: List[(String, Value)] = l("GeneratorBody", x7, l("FormalParameters", x4, l("Option[BindingIdentifier]", x2, Nil))).reverse
  val info: ASTInfo = GeneratorExpression0
}
object GeneratorExpression0 extends ASTInfo {
  val maxK: Int = 1
  val semMap: Map[String, Algo] = Map(
    "HasName0" -> `AL::GeneratorExpression[0,0].HasName`,
    "HasName1" -> `AL::GeneratorExpression[0,1].HasName`,
    "IsFunctionDefinition1" -> `AL::GeneratorExpression[0,1].IsFunctionDefinition`,
    "NamedEvaluation0" -> `AL::GeneratorExpression[0,0].NamedEvaluation`,
    "Contains1" -> `AL::GeneratorExpression[0,1].Contains`,
    "InstantiateGeneratorFunctionExpression0" -> `AL::GeneratorExpression[0,0].InstantiateGeneratorFunctionExpression`,
    "InstantiateGeneratorFunctionExpression1" -> `AL::GeneratorExpression[0,1].InstantiateGeneratorFunctionExpression`,
    "Evaluation1" -> `AL::GeneratorExpression[0,1].Evaluation`,
    "EarlyErrors1" -> `AL::GeneratorExpression[0,1].EarlyErrors`,
  )
}
