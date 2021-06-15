package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ArrowParameters extends AST {
  val kind: String = "ArrowParameters"
}
object ArrowParameters extends ASTHelper {
  def apply(v: JsValue): ArrowParameters = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ArrowParameters0(BindingIdentifier(x0), params, span)
    case JsSeq(JsInt(1), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      ArrowParameters1(CoverParenthesizedExpressionAndArrowParameterList(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class ArrowParameters0(x0: BindingIdentifier, parserParams: List[Boolean], span: Span) extends ArrowParameters {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("BindingIdentifier", x0, Nil).reverse
  val info: ASTInfo = ArrowParameters0
}
object ArrowParameters0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "IteratorBindingInitialization0" -> `AL::ArrowParameters[0,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::ArrowParameters[0,0].ContainsExpression`,
    "IsSimpleParameterList0" -> `AL::ArrowParameters[0,0].IsSimpleParameterList`,
    "ExpectedArgumentCount0" -> `AL::ArrowParameters[0,0].ExpectedArgumentCount`,
    "CoveredFormalsList0" -> `AL::ArrowParameters[0,0].CoveredFormalsList`,
  )
}

case class ArrowParameters1(x0: CoverParenthesizedExpressionAndArrowParameterList, parserParams: List[Boolean], span: Span) extends ArrowParameters {
  x0.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("CoverParenthesizedExpressionAndArrowParameterList", x0, Nil).reverse
  val info: ASTInfo = ArrowParameters1
}
object ArrowParameters1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::ArrowParameters[1,0].BoundNames`,
    "Contains0" -> `AL::ArrowParameters[1,0].Contains`,
    "IteratorBindingInitialization0" -> `AL::ArrowParameters[1,0].IteratorBindingInitialization`,
    "ContainsExpression0" -> `AL::ArrowParameters[1,0].ContainsExpression`,
    "IsSimpleParameterList0" -> `AL::ArrowParameters[1,0].IsSimpleParameterList`,
    "ExpectedArgumentCount0" -> `AL::ArrowParameters[1,0].ExpectedArgumentCount`,
    "EarlyErrors0" -> `AL::ArrowParameters[1,0].EarlyErrors`,
  )
}
