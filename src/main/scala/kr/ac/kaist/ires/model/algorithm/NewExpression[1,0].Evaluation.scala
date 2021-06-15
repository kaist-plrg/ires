package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NewExpression[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("NewExpression", 1, 0, Rhs(List(Terminal("new"), NonTerminal("NewExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-new-operator-runtime-semantics-evaluation",
    "sec-new-operator",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (EvaluateNew NewExpression CONST_empty)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? EvaluateNew(|NewExpression|, ~empty~).""",
  )
}
