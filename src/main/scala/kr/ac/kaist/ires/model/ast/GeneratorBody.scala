package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait GeneratorBody extends AST {
  val kind: String = "GeneratorBody"
}
object GeneratorBody extends ASTHelper {
  def apply(v: JsValue): GeneratorBody = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      GeneratorBody0(FunctionBody(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class GeneratorBody0(x0: FunctionBody, parserParams: List[Boolean], span: Span) extends GeneratorBody {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FunctionBody", x0, Nil).reverse
  val info: ASTInfo = GeneratorBody0
}
object GeneratorBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "EvaluateBody0" -> `AL::GeneratorBody[0,0].EvaluateBody`,
    "EvaluateGeneratorBody0" -> `AL::GeneratorBody[0,0].EvaluateGeneratorBody`,
  )
}
