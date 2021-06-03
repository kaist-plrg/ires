package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectLiteral[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ObjectLiteral", 0, 0, Rhs(List(Terminal("{"), Terminal("}")), None), "Evaluation", List())
  val ids = List(
    "sec-object-initializer-runtime-semantics-evaluation",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |  0:return [! __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ! OrdinaryObjectCreate(%Object.prototype%).""",
  )
}
