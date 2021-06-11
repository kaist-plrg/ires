package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import kr.ac.kaist.ires.util.Span
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AsyncFunctionDeclaration extends AST {
  val kind: String = "AsyncFunctionDeclaration"
}
object AsyncFunctionDeclaration extends ASTHelper {
  def apply(v: JsValue): AsyncFunctionDeclaration = v match {
    case JsSeq(JsInt(0), JsSeq(x3, x5, x8), JsBoolSeq(params), JsSpan(span)) =>
      AsyncFunctionDeclaration0(BindingIdentifier(x3), FormalParameters(x5), AsyncFunctionBody(x8), params, span)
    case JsSeq(JsInt(1), JsSeq(x4, x7), JsBoolSeq(params), JsSpan(span)) =>
      AsyncFunctionDeclaration1(FormalParameters(x4), AsyncFunctionBody(x7), params, span)
    case _ => throw InvalidAST
  }
}

case class AsyncFunctionDeclaration0(x3: BindingIdentifier, x5: FormalParameters, x8: AsyncFunctionBody, parserParams: List[Boolean], span: Span) extends AsyncFunctionDeclaration {
  x3.parent = Some(this)
  x5.parent = Some(this)
  x8.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"async function $x3 ( $x5 ) { $x8 }"
  }
  val k: Int = d(x8, d(x5, d(x3, 0)))
  val fullList: List[(String, Value)] = l("AsyncFunctionBody", x8, l("FormalParameters", x5, l("BindingIdentifier", x3, Nil))).reverse
  val info: ASTInfo = AsyncFunctionDeclaration0
}
object AsyncFunctionDeclaration0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::AsyncFunctionDeclaration[0,0].BoundNames`,
    "IsConstantDeclaration0" -> `AL::AsyncFunctionDeclaration[0,0].IsConstantDeclaration`,
    "Contains0" -> `AL::AsyncFunctionDeclaration[0,0].Contains`,
    "InstantiateFunctionObject0" -> `AL::AsyncFunctionDeclaration[0,0].InstantiateFunctionObject`,
    "InstantiateAsyncFunctionObject0" -> `AL::AsyncFunctionDeclaration[0,0].InstantiateAsyncFunctionObject`,
    "Evaluation0" -> `AL::AsyncFunctionDeclaration[0,0].Evaluation`,
    "EarlyErrors0" -> `AL::AsyncFunctionDeclaration[0,0].EarlyErrors`,
  )
}

case class AsyncFunctionDeclaration1(x4: FormalParameters, x7: AsyncFunctionBody, parserParams: List[Boolean], span: Span) extends AsyncFunctionDeclaration {
  x4.parent = Some(this)
  x7.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"async function ( $x4 ) { $x7 }"
  }
  val k: Int = d(x7, d(x4, 0))
  val fullList: List[(String, Value)] = l("AsyncFunctionBody", x7, l("FormalParameters", x4, Nil)).reverse
  val info: ASTInfo = AsyncFunctionDeclaration1
}
object AsyncFunctionDeclaration1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::AsyncFunctionDeclaration[1,0].BoundNames`,
    "IsConstantDeclaration0" -> `AL::AsyncFunctionDeclaration[1,0].IsConstantDeclaration`,
    "Contains0" -> `AL::AsyncFunctionDeclaration[1,0].Contains`,
    "InstantiateFunctionObject0" -> `AL::AsyncFunctionDeclaration[1,0].InstantiateFunctionObject`,
    "InstantiateAsyncFunctionObject0" -> `AL::AsyncFunctionDeclaration[1,0].InstantiateAsyncFunctionObject`,
    "Evaluation0" -> `AL::AsyncFunctionDeclaration[1,0].Evaluation`,
    "EarlyErrors0" -> `AL::AsyncFunctionDeclaration[1,0].EarlyErrors`,
  )
}
