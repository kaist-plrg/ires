package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseBlock[0,1].CaseBlockEvaluation` extends Algo {
  val head = SyntaxDirectedHead("CaseBlock", 0, 1, Rhs(List(Terminal("{"), NonTerminal("CaseClauses", List(""), false), Terminal("}")), None), "CaseBlockEvaluation", List(Param("input", Normal)))
  val ids = List(
    "sec-runtime-semantics-caseblockevaluation",
    "sec-switch-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let V = undefined
  |  1:??? "Let id:{A} be the List of nt:{CaseClause} items in nt:{CaseClauses} , in source text order ."
  |  2:let found = false
  |  3:let __x0__ = A
  |  3:let __x1__ = 0i
  |  3:while (< __x1__ __x0__.length) {
  |    let C = __x0__[__x1__]
  |    4:if (= found false) {
  |      5:app __x2__ = (CaseClauseIsSelected C input)
  |      5:found = [? __x2__]
  |    } else 2:{}
  |    6:if (= found true) {
  |      7:access __x3__ = (C "Evaluation")
  |      7:let R = __x3__
  |      8:if (! (= R.Value CONST_empty)) V = R.Value else 2:{}
  |      9:app __x4__ = (IsAbruptCompletion R)
  |      9:if __x4__ {
  |        app __x5__ = (UpdateEmpty R V)
  |        return __x5__
  |      } else 2:{}
  |    } else 2:{}
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  10:return V
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _V_ be *undefined*.""",
    """        1. Let _A_ be the List of |CaseClause| items in |CaseClauses|, in source text order.""",
    """        1. Let _found_ be *false*.""",
    """        1. For each |CaseClause| _C_ of _A_, do""",
    """          1. If _found_ is *false*, then""",
    """            1. Set _found_ to ? CaseClauseIsSelected(_C_, _input_).""",
    """          1. If _found_ is *true*, then""",
    """            1. Let _R_ be the result of evaluating _C_.""",
    """            1. If _R_.[[Value]] is not ~empty~, set _V_ to _R_.[[Value]].""",
    """            1. If _R_ is an abrupt completion, return Completion(UpdateEmpty(_R_, _V_)).""",
    """        1. Return NormalCompletion(_V_).""",
  )
}
