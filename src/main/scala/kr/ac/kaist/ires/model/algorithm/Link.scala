package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Link` extends Algo {
  val head = NormalHead("Link", List())
  val ids = List(
    "sec-moduledeclarationlinking",
    "sec-cyclic-module-records",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:assert (! (|| (= module.Status CONST_linking) (= module.Status CONST_evaluating)))
  |  1:let stack = (new [])
  |  2:app __x0__ = (InnerModuleLinking module stack 0i)
  |  2:let result = __x0__
  |  3:app __x1__ = (IsAbruptCompletion result)
  |  3:if __x1__ {
  |    4:let __x2__ = stack
  |    4:let __x3__ = 0i
  |    4:while (< __x3__ __x2__.length) {
  |      let m = __x2__[__x3__]
  |      5:assert (= m.Status CONST_linking)
  |      6:m.Status = CONST_unlinked
  |      7:m.Environment = undefined
  |      8:m.DFSIndex = undefined
  |      9:m.DFSAncestorIndex = undefined
  |      __x3__ = (+ __x3__ 1i)
  |    }
  |    10:assert (= module.Status CONST_unlinked)
  |    11:return result
  |  } else 36:{}
  |  12:assert (|| (= module.Status CONST_linked) (= module.Status CONST_evaluated))
  |  13:assert (= stack.length 0i)
  |  14:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _module_.[[Status]] is not ~linking~ or ~evaluating~.""",
    """            1. Let _stack_ be a new empty List.""",
    """            1. Let _result_ be InnerModuleLinking(_module_, _stack_, 0).""",
    """            1. If _result_ is an abrupt completion, then""",
    """              1. For each Cyclic Module Record _m_ of _stack_, do""",
    """                1. Assert: _m_.[[Status]] is ~linking~.""",
    """                1. Set _m_.[[Status]] to ~unlinked~.""",
    """                1. Set _m_.[[Environment]] to *undefined*.""",
    """                1. Set _m_.[[DFSIndex]] to *undefined*.""",
    """                1. Set _m_.[[DFSAncestorIndex]] to *undefined*.""",
    """              1. Assert: _module_.[[Status]] is ~unlinked~.""",
    """              1. Return _result_.""",
    """            1. Assert: _module_.[[Status]] is ~linked~ or ~evaluated~.""",
    """            1. Assert: _stack_ is empty.""",
    """            1. Return *undefined*.""",
  )
}
