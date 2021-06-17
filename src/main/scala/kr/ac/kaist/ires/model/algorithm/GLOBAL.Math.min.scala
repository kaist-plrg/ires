package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Math.min` extends Algo {
  val head = BuiltinHead(parseRef("""Math.min"""), List(Param("args", Variadic)))
  val ids = List(
    "sec-math.min",
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
  |  4:let lowest = Infinity
  |  5:let __x3__ = coerced
  |  5:let __x4__ = 0i
  |  5:while (< __x4__ __x3__.length) {
  |    let number = __x3__[__x4__]
  |    6:if (= number NaN) return NaN else 2:{}
  |    7:if (&& (= number -0.0) (= lowest 0i)) lowest = -0.0 else 2:{}
  |    8:if (< number lowest) lowest = number else 2:{}
  |    __x4__ = (+ __x4__ 1i)
  |  }
  |  9:return lowest
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _coerced_ be a new empty List.""",
    """          1. For each element _arg_ of _args_, do""",
    """            1. Let _n_ be ? ToNumber(_arg_).""",
    """            1. Append _n_ to _coerced_.""",
    """          1. Let _lowest_ be *+∞*<sub>𝔽</sub>.""",
    """          1. For each element _number_ of _coerced_, do""",
    """            1. If _number_ is *NaN*, return *NaN*.""",
    """            1. If _number_ is *-0*<sub>𝔽</sub> and _lowest_ is *+0*<sub>𝔽</sub>, set _lowest_ to *-0*<sub>𝔽</sub>.""",
    """            1. If _number_ < _lowest_, set _lowest_ to _number_.""",
    """          1. Return _lowest_.""",
  )
}
