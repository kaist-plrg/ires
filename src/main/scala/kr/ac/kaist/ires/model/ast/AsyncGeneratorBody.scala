package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AsyncGeneratorBody extends AST {
  val kind: String = "AsyncGeneratorBody"
}
object AsyncGeneratorBody extends ASTHelper {
  def apply(v: JsValue): AsyncGeneratorBody = v match {
    case JsSeq(JsInt(0), JsSeq(x0), JsBoolSeq(params), JsSpan(span)) =>
      AsyncGeneratorBody0(FunctionBody(x0), params, span)
    case _ => throw InvalidAST
  }
}

case class AsyncGeneratorBody0(x0: FunctionBody, parserParams: List[Boolean], span: Span) extends AsyncGeneratorBody {
  x0.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"$x0"
  }
  val k: Int = d(x0, 0)
  val fullList: List[(String, Value)] = l("FunctionBody", x0, Nil).reverse
  val info: ASTInfo = AsyncGeneratorBody0
}
object AsyncGeneratorBody0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "EvaluateBody0" -> `AL::AsyncGeneratorBody[0,0].EvaluateBody`,
    "EvaluateAsyncGeneratorBody0" -> `AL::AsyncGeneratorBody[0,0].EvaluateAsyncGeneratorBody`,
  )
}
