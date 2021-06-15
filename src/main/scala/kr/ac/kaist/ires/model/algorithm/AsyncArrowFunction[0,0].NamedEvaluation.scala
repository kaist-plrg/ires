package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncArrowFunction[0,0].NamedEvaluation` extends Algo {
  val head = SyntaxDirectedHead("AsyncArrowFunction", 0, 0, Rhs(List(Terminal("async"), NonTerminal("AsyncArrowBindingIdentifier", List(""), false), Terminal("=>"), NonTerminal("AsyncConciseBody", List(""), false)), None), "NamedEvaluation", List(Param("name", Normal)))
  val ids = List(
    "sec-runtime-semantics-namedevaluation",
    "sec-syntax-directed-operations-function-name-inference",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AsyncArrowFunction "InstantiateAsyncArrowFunctionExpression" name)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return InstantiateAsyncArrowFunctionExpression of |AsyncArrowFunction| with argument _name_.""",
  )
}
