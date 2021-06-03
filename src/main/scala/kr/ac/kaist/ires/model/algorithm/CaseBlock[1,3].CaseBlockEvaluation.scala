package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseBlock[1,3].CaseBlockEvaluation` extends Algo {
  val head = SyntaxDirectedHead("CaseBlock", 1, 3, Rhs(List(Terminal("{"), NonTerminal("CaseClauses", List(""), true), NonTerminal("DefaultClause", List(""), false), NonTerminal("CaseClauses", List(""), true), Terminal("}")), None), "CaseBlockEvaluation", List(Param("input", Normal)))
  val ids = List(
    "sec-runtime-semantics-caseblockevaluation",
    "sec-switch-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let V = undefined
  |  3:if (! (= CaseClauses0 absent)) ??? "Let id:{A} be the List of nt:{CaseClause} items in the first nt:{CaseClauses} , in source text order ." else let A = (new [])
  |  5:let found = false
  |  6:let __x0__ = A
  |  6:let __x1__ = 0i
  |  6:while (< __x1__ __x0__.length) {
  |    let C = __x0__[__x1__]
  |    7:if (= found false) {
  |      8:app __x2__ = (CaseClauseIsSelected C input)
  |      8:found = [? __x2__]
  |    } else 30:{}
  |    9:if (= found true) {
  |      10:access __x3__ = (C "Evaluation")
  |      10:let R = __x3__
  |      11:if (! (= R.Value CONST_empty)) V = R.Value else 30:{}
  |      12:app __x4__ = (IsAbruptCompletion R)
  |      12:if __x4__ {
  |        app __x5__ = (UpdateEmpty R V)
  |        return __x5__
  |      } else 30:{}
  |    } else 30:{}
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  13:let foundInB = false
  |  16:if (! (= CaseClauses1 absent)) ??? "Let id:{B} be the List of nt:{CaseClause} items in the second nt:{CaseClauses} , in source text order ." else let B = (new [])
  |  18:if (= found false) {
  |    19:let __x6__ = B
  |    19:let __x7__ = 0i
  |    19:while (< __x7__ __x6__.length) {
  |      let C = __x6__[__x7__]
  |      20:if (= foundInB false) {
  |        21:app __x8__ = (CaseClauseIsSelected C input)
  |        21:foundInB = [? __x8__]
  |      } else 30:{}
  |      22:if (= foundInB true) {
  |        23:access __x9__ = (C "Evaluation")
  |        23:let R = __x9__
  |        24:if (! (= R.Value CONST_empty)) V = R.Value else 30:{}
  |        25:app __x10__ = (IsAbruptCompletion R)
  |        25:if __x10__ {
  |          app __x11__ = (UpdateEmpty R V)
  |          return __x11__
  |        } else 30:{}
  |      } else 30:{}
  |      __x7__ = (+ __x7__ 1i)
  |    }
  |  } else 30:{}
  |  26:if (= foundInB true) return V else 30:{}
  |  27:access __x12__ = (DefaultClause "Evaluation")
  |  27:let R = __x12__
  |  28:if (! (= R.Value CONST_empty)) V = R.Value else 30:{}
  |  29:app __x13__ = (IsAbruptCompletion R)
  |  29:if __x13__ {
  |    app __x14__ = (UpdateEmpty R V)
  |    return __x14__
  |  } else 30:{}
  |  31:let __x15__ = B
  |  31:let __x16__ = 0i
  |  31:while (< __x16__ __x15__.length) {
  |    let C = __x15__[__x16__]
  |    32:access __x17__ = (C "Evaluation")
  |    32:let R = __x17__
  |    33:if (! (= R.Value CONST_empty)) V = R.Value else 30:{}
  |    34:app __x18__ = (IsAbruptCompletion R)
  |    34:if __x18__ {
  |      app __x19__ = (UpdateEmpty R V)
  |      return __x19__
  |    } else 30:{}
  |    __x16__ = (+ __x16__ 1i)
  |  }
  |  35:return V
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _V_ be *undefined*.""",
    """        1. If the first |CaseClauses| is present, then""",
    """          1. Let _A_ be the List of |CaseClause| items in the first |CaseClauses|, in source text order.""",
    """        1. Else,""",
    """          1. Let _A_ be « ».""",
    """        1. Let _found_ be *false*.""",
    """        1. For each |CaseClause| _C_ of _A_, do""",
    """          1. If _found_ is *false*, then""",
    """            1. Set _found_ to ? CaseClauseIsSelected(_C_, _input_).""",
    """          1. If _found_ is *true*, then""",
    """            1. Let _R_ be the result of evaluating _C_.""",
    """            1. If _R_.[[Value]] is not ~empty~, set _V_ to _R_.[[Value]].""",
    """            1. If _R_ is an abrupt completion, return Completion(UpdateEmpty(_R_, _V_)).""",
    """        1. Let _foundInB_ be *false*.""",
    """        1. If the second |CaseClauses| is present, then""",
    """          1. Let _B_ be the List of |CaseClause| items in the second |CaseClauses|, in source text order.""",
    """        1. Else,""",
    """          1. Let _B_ be « ».""",
    """        1. If _found_ is *false*, then""",
    """          1. For each |CaseClause| _C_ of _B_, do""",
    """            1. If _foundInB_ is *false*, then""",
    """              1. Set _foundInB_ to ? CaseClauseIsSelected(_C_, _input_).""",
    """            1. If _foundInB_ is *true*, then""",
    """              1. Let _R_ be the result of evaluating |CaseClause| _C_.""",
    """              1. If _R_.[[Value]] is not ~empty~, set _V_ to _R_.[[Value]].""",
    """              1. If _R_ is an abrupt completion, return Completion(UpdateEmpty(_R_, _V_)).""",
    """        1. If _foundInB_ is *true*, return NormalCompletion(_V_).""",
    """        1. Let _R_ be the result of evaluating |DefaultClause|.""",
    """        1. If _R_.[[Value]] is not ~empty~, set _V_ to _R_.[[Value]].""",
    """        1. If _R_ is an abrupt completion, return Completion(UpdateEmpty(_R_, _V_)).""",
    """        1. NOTE: The following is another complete iteration of the second |CaseClauses|.""",
    """        1. For each |CaseClause| _C_ of _B_, do""",
    """          1. Let _R_ be the result of evaluating |CaseClause| _C_.""",
    """          1. If _R_.[[Value]] is not ~empty~, set _V_ to _R_.[[Value]].""",
    """          1. If _R_ is an abrupt completion, return Completion(UpdateEmpty(_R_, _V_)).""",
    """        1. Return NormalCompletion(_V_).""",
  )
}
