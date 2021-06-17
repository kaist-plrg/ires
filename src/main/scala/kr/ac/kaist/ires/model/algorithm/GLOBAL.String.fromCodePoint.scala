package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.String.fromCodePoint` extends Algo {
  val head = BuiltinHead(parseRef("""String.fromCodePoint"""), List(Param("codePoints", Variadic)))
  val ids = List(
    "sec-string.fromcodepoint",
    "sec-properties-of-the-string-constructor",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let result = ""
  |  1:let __x0__ = codePoints
  |  1:let __x1__ = 0i
  |  1:while (< __x1__ __x0__.length) {
  |    let next = __x0__[__x1__]
  |    2:app __x2__ = (ToNumber next)
  |    2:let nextCP = [? __x2__]
  |    3:app __x3__ = (IsIntegralNumber nextCP)
  |    3:if (= [! __x3__] false) throw RangeError else 6:{}
  |    4:if (|| (< nextCP 0i) (< 1114111i nextCP)) throw RangeError else 6:{}
  |    5:app __x4__ = (UTF16EncodeCodePoint nextCP)
  |    5:result = (+ result [! __x4__])
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  7:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _result_ be the empty String.""",
    """          1. For each element _next_ of _codePoints_, do""",
    """            1. Let _nextCP_ be ? ToNumber(_next_).""",
    """            1. If ! IsIntegralNumber(_nextCP_) is *false*, throw a *RangeError* exception.""",
    """            1. If ℝ(_nextCP_) < 0 or ℝ(_nextCP_) > 0x10FFFF, throw a *RangeError* exception.""",
    """            1. Set _result_ to the string-concatenation of _result_ and ! UTF16EncodeCodePoint(ℝ(_nextCP_)).""",
    """          1. Assert: If _codePoints_ is empty, then _result_ is the empty String.""",
    """          1. Return _result_.""",
  )
}
