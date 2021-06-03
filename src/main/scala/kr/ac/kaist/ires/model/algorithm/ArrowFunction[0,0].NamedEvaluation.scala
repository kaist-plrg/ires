package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrowFunction[0,0].NamedEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ArrowFunction", 0, 0, Rhs(List(NonTerminal("ArrowParameters", List(""), false), Terminal("=>"), NonTerminal("ConciseBody", List(""), false)), None), "NamedEvaluation", List(Param("name", Normal)))
  val ids = List(
    "sec-runtime-semantics-namedevaluation",
    "sec-syntax-directed-operations-function-name-inference",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ArrowFunction "InstantiateArrowFunctionExpression" name)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return InstantiateArrowFunctionExpression of |ArrowFunction| with argument _name_.""",
  )
}
