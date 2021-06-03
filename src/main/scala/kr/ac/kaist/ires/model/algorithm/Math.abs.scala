package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Math.abs` extends Algo {
  val head = BuiltinHead(parseRef("""Math.abs"""), List(Param("x", Normal)))
  val ids = List(
    "sec-math.abs",
    "sec-function-properties-of-the-math-object",
    "sec-math-object",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber x)
  |  0:let n = [? __x0__]
  |  1:if (= n NaN) return NaN else 2:{}
  |  2:if (= n -0.0) return 0i else 2:{}
  |  3:if (= n -Infinity) return Infinity else 2:{}
  |  4:if (< n 0i) return (- n) else 2:{}
  |  5:return n
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _n_ be ? ToNumber(_x_).""",
    """          1. If _n_ is *NaN*, return *NaN*.""",
    """          1. If _n_ is *-0*<sub>𝔽</sub>, return *+0*<sub>𝔽</sub>.""",
    """          1. If _n_ is *-∞*<sub>𝔽</sub>, return *+∞*<sub>𝔽</sub>.""",
    """          1. If _n_ < *+0*<sub>𝔽</sub>, return -_n_.""",
    """          1. Return _n_.""",
  )
}
