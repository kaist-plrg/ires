package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AwaitExpression[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("AwaitExpression", 0, 0, Rhs(List(Terminal("await"), NonTerminal("UnaryExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-async-function-definitions-runtime-semantics-evaluation",
    "sec-async-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (UnaryExpression "Evaluation")
  |  0:let exprRef = __x0__
  |  1:app __x1__ = (GetValue exprRef)
  |  1:let value = [? __x1__]
  |  2:app __x2__ = (Await value)
  |  2:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _exprRef_ be the result of evaluating |UnaryExpression|.""",
    """        1. Let _value_ be ? GetValue(_exprRef_).""",
    """        1. Return ? Await(_value_).""",
  )
}
