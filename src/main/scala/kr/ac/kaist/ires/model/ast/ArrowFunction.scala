package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait ArrowFunction extends AST {
  val kind: String = "ArrowFunction"
}
object ArrowFunction extends ASTHelper {
  def apply(v: JsValue): ArrowFunction = v match {
    case JsSeq(JsInt(0), JsSeq(x0, x3), JsBoolSeq(params), JsSpan(span)) =>
      ArrowFunction0(ArrowParameters(x0), ConciseBody(x3), params, span)
    case _ => throw InvalidAST
  }
}

case class ArrowFunction0(x0: ArrowParameters, x3: ConciseBody, parserParams: List[Boolean], span: Span) extends ArrowFunction {
  x0.parent = Some(this)
  x3.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0 => $x3"
  }
  val k: Int = d(x3, d(x0, 0))
  val fullList: List[(String, Value)] = l("ConciseBody", x3, l("ArrowParameters", x0, Nil)).reverse
  val info: ASTInfo = ArrowFunction0
}
object ArrowFunction0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "HasName0" -> `AL::ArrowFunction[0,0].HasName`,
    "NamedEvaluation0" -> `AL::ArrowFunction[0,0].NamedEvaluation`,
    "Contains0" -> `AL::ArrowFunction[0,0].Contains`,
    "InstantiateArrowFunctionExpression0" -> `AL::ArrowFunction[0,0].InstantiateArrowFunctionExpression`,
    "Evaluation0" -> `AL::ArrowFunction[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::ArrowFunction[0,0].EarlyErrors`,
  )
}
