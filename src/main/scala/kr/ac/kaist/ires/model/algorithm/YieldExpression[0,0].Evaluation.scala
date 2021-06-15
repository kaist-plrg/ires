package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::YieldExpression[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("YieldExpression", 0, 0, Rhs(List(Terminal("yield")), None), "Evaluation", List())
  val ids = List(
    "sec-generator-function-definitions-runtime-semantics-evaluation",
    "sec-generator-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (Yield undefined)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? Yield(*undefined*).""",
  )
}
