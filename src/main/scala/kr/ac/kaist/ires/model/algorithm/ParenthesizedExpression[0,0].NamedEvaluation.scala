package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ParenthesizedExpression[0,0].NamedEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ParenthesizedExpression", 0, 0, Rhs(List(Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")")), None), "NamedEvaluation", List(Param("name", Normal)))
  val ids = List(
    "sec-runtime-semantics-namedevaluation",
    "sec-syntax-directed-operations-function-name-inference",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsAnonymousFunctionDefinition Expression)
  |  0:assert (= __x0__ true)
  |  1:access __x1__ = (Expression "NamedEvaluation" name)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: IsAnonymousFunctionDefinition(|Expression|) is *true*.""",
    """        1. Return the result of performing NamedEvaluation for |Expression| with argument _name_.""",
  )
}
