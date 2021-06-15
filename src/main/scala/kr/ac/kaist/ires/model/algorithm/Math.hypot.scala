package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Math.hypot` extends Algo {
  val head = BuiltinHead(parseRef("""Math.hypot"""), List(Param("args", Variadic)))
  val ids = List(
    "sec-math.hypot",
    "sec-function-properties-of-the-math-object",
    "sec-math-object",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:let coerced = (new [])
  |  1:let __x0__ = args
  |  1:let __x1__ = 0i
  |  1:while (< __x1__ __x0__.length) {
  |    let arg = __x0__[__x1__]
  |    2:app __x2__ = (ToNumber arg)
  |    2:let n = [? __x2__]
  |    3:append n -> coerced
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  4:let onlyZero = true
  |  5:let __x3__ = coerced
  |  5:let __x4__ = 0i
  |  5:while (< __x4__ __x3__.length) {
  |    let number = __x3__[__x4__]
  |    6:if (|| (= number NaN) (= number Infinity)) return number else 2:{}
  |    7:if (= number -Infinity) return Infinity else 2:{}
  |    8:if (! (|| (= number 0i) (= number -0.0))) onlyZero = false else 2:{}
  |    __x4__ = (+ __x4__ 1i)
  |  }
  |  9:if (= onlyZero true) return 0i else 2:{}
  |  10:??? "Return an implementation - approximated value representing the square root of the sum of squares of the mathematical values of the elements of id:{coerced} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _coerced_ be a new empty List.""",
    """          1. For each element _arg_ of _args_, do""",
    """            1. Let _n_ be ? ToNumber(_arg_).""",
    """            1. Append _n_ to _coerced_.""",
    """          1. Let _onlyZero_ be *true*.""",
    """          1. For each element _number_ of _coerced_, do""",
    """            1. If _number_ is *NaN* or _number_ is *+∞*<sub>𝔽</sub>, return _number_.""",
    """            1. If _number_ is *-∞*<sub>𝔽</sub>, return *+∞*<sub>𝔽</sub>.""",
    """            1. If _number_ is neither *+0*<sub>𝔽</sub> nor *-0*<sub>𝔽</sub>, set _onlyZero_ to *false*.""",
    """          1. If _onlyZero_ is *true*, return *+0*<sub>𝔽</sub>.""",
    """          1. Return an implementation-approximated value representing the square root of the sum of squares of the mathematical values of the elements of _coerced_.""",
  )
}
