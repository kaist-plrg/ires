package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait AsyncGeneratorDeclaration extends AST {
  val kind: String = "AsyncGeneratorDeclaration"
}

case class AsyncGeneratorDeclaration0(x4: BindingIdentifier, x6: FormalParameters, x9: AsyncGeneratorBody, parserParams: List[Boolean]) extends AsyncGeneratorDeclaration {
  x4.parent = Some(this)
  x6.parent = Some(this)
  x9.parent = Some(this)
  val name: String = "AsyncGeneratorDeclaration0"
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
  val name: String = "AsyncGeneratorDeclaration1"
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
