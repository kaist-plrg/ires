package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.error.UnexpectedSemantics
import scala.collection.immutable.{ Set => SSet }

trait GeneratorDeclaration extends AST {
  val kind: String = "GeneratorDeclaration"
}

case class GeneratorDeclaration0(x2: BindingIdentifier, x4: FormalParameters, x7: GeneratorBody, parserParams: List[Boolean]) extends GeneratorDeclaration {
  x2.parent = Some(this)
  x4.parent = Some(this)
  x7.parent = Some(this)
  val idx: Int = 0
  override def toString: String = {
    s"function * $x2 ( $x4 ) { $x7 }"
  }
  val k: Int = d(x7, d(x4, d(x2, 0)))
  val fullList: List[(String, Value)] = l("GeneratorBody", x7, l("FormalParameters", x4, l("BindingIdentifier", x2, Nil))).reverse
  val info: ASTInfo = GeneratorDeclaration0
}
object GeneratorDeclaration0 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::GeneratorDeclaration[0,0].BoundNames`,
    "IsConstantDeclaration0" -> `AL::GeneratorDeclaration[0,0].IsConstantDeclaration`,
    "Contains0" -> `AL::GeneratorDeclaration[0,0].Contains`,
    "InstantiateFunctionObject0" -> `AL::GeneratorDeclaration[0,0].InstantiateFunctionObject`,
    "InstantiateGeneratorFunctionObject0" -> `AL::GeneratorDeclaration[0,0].InstantiateGeneratorFunctionObject`,
    "EarlyErrors0" -> `AL::GeneratorDeclaration[0,0].EarlyErrors`,
  )
}

case class GeneratorDeclaration1(x3: FormalParameters, x6: GeneratorBody, parserParams: List[Boolean]) extends GeneratorDeclaration {
  x3.parent = Some(this)
  x6.parent = Some(this)
  val idx: Int = 1
  override def toString: String = {
    s"function * ( $x3 ) { $x6 }"
  }
  val k: Int = d(x6, d(x3, 0))
  val fullList: List[(String, Value)] = l("GeneratorBody", x6, l("FormalParameters", x3, Nil)).reverse
  val info: ASTInfo = GeneratorDeclaration1
}
object GeneratorDeclaration1 extends ASTInfo {
  val maxK: Int = 0
  val semMap: Map[String, Algo] = Map(
    "BoundNames0" -> `AL::GeneratorDeclaration[1,0].BoundNames`,
    "IsConstantDeclaration0" -> `AL::GeneratorDeclaration[1,0].IsConstantDeclaration`,
    "Contains0" -> `AL::GeneratorDeclaration[1,0].Contains`,
    "InstantiateFunctionObject0" -> `AL::GeneratorDeclaration[1,0].InstantiateFunctionObject`,
    "InstantiateGeneratorFunctionObject0" -> `AL::GeneratorDeclaration[1,0].InstantiateGeneratorFunctionObject`,
    "EarlyErrors0" -> `AL::GeneratorDeclaration[1,0].EarlyErrors`,
  )
}
