package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.InvalidAST
import scala.collection.immutable.{ Set => SSet }
import spray.json._

trait AsyncGeneratorDeclaration extends AST {
  val kind: String = "AsyncGeneratorDeclaration"
}
object AsyncGeneratorDeclaration extends ASTHelper {
  def apply(v: JsValue): AsyncGeneratorDeclaration = v match {
    case JsSeq(JsInt(0), JsSeq(x4, x6, x9), JsBoolSeq(params), JsSpan(span)) =>
      AsyncGeneratorDeclaration0(BindingIdentifier(x4), FormalParameters(x6), AsyncGeneratorBody(x9), params)
    case JsSeq(JsInt(1), JsSeq(x5, x8), JsBoolSeq(params), JsSpan(span)) =>
      AsyncGeneratorDeclaration1(FormalParameters(x5), AsyncGeneratorBody(x8), params)
    case _ => throw InvalidAST
  }
}

case class AsyncGeneratorDeclaration0(x4: BindingIdentifier, x6: FormalParameters, x9: AsyncGeneratorBody, parserParams: List[Boolean]) extends AsyncGeneratorDeclaration {
  x4.parent = Some(this)
  x6.parent = Some(this)
  x9.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"async function * $x4 ( $x6 ) { $x9 }"
  }
  val k: Int = d(x9, d(x6, d(x4, 0)))
  val fullList: List[(String, Value)] = l("AsyncGeneratorBody", x9, l("FormalParameters", x6, l("BindingIdentifier", x4, Nil))).reverse
  val info: ASTInfo = AsyncGeneratorDeclaration0
}
object AsyncGeneratorDeclaration0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::AsyncGeneratorDeclaration[0,0].BoundNames`,
    "IsConstantDeclaration0" -> `AL::AsyncGeneratorDeclaration[0,0].IsConstantDeclaration`,
    "Contains0" -> `AL::AsyncGeneratorDeclaration[0,0].Contains`,
    "InstantiateFunctionObject0" -> `AL::AsyncGeneratorDeclaration[0,0].InstantiateFunctionObject`,
    "InstantiateAsyncGeneratorFunctionObject0" -> `AL::AsyncGeneratorDeclaration[0,0].InstantiateAsyncGeneratorFunctionObject`,
    "EarlyErrors0" -> `AL::AsyncGeneratorDeclaration[0,0].EarlyErrors`,
  )
}

case class AsyncGeneratorDeclaration1(x5: FormalParameters, x8: AsyncGeneratorBody, parserParams: List[Boolean]) extends AsyncGeneratorDeclaration {
  x5.parent = Some(this)
  x8.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"async function * ( $x5 ) { $x8 }"
  }
  val k: Int = d(x8, d(x5, 0))
  val fullList: List[(String, Value)] = l("AsyncGeneratorBody", x8, l("FormalParameters", x5, Nil)).reverse
  val info: ASTInfo = AsyncGeneratorDeclaration1
}
object AsyncGeneratorDeclaration1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::AsyncGeneratorDeclaration[1,0].BoundNames`,
    "IsConstantDeclaration0" -> `AL::AsyncGeneratorDeclaration[1,0].IsConstantDeclaration`,
    "Contains0" -> `AL::AsyncGeneratorDeclaration[1,0].Contains`,
    "InstantiateFunctionObject0" -> `AL::AsyncGeneratorDeclaration[1,0].InstantiateFunctionObject`,
    "InstantiateAsyncGeneratorFunctionObject0" -> `AL::AsyncGeneratorDeclaration[1,0].InstantiateAsyncGeneratorFunctionObject`,
    "EarlyErrors0" -> `AL::AsyncGeneratorDeclaration[1,0].EarlyErrors`,
  )
}
