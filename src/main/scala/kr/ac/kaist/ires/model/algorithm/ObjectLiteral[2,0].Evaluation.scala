package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectLiteral[2,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ObjectLiteral", 2, 0, Rhs(List(Terminal("{"), NonTerminal("PropertyDefinitionList", List(""), false), Terminal(","), Terminal("}")), None), "Evaluation", List())
  val ids = List(
    "sec-object-initializer-runtime-semantics-evaluation",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |  0:let obj = [! __x0__]
  |  1:access __x1__ = (PropertyDefinitionList "PropertyDefinitionEvaluation" obj true)
  |  1:[? __x1__]
  |  2:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _obj_ be ! OrdinaryObjectCreate(%Object.prototype%).""",
    """          1. Perform ? PropertyDefinitionEvaluation of |PropertyDefinitionList| with arguments _obj_ and *true*.""",
    """          1. Return _obj_.""",
  )
}
