package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Math.log` extends Algo {
  val head = BuiltinHead(parseRef("""Math.log"""), List(Param("x", Normal)))
  val ids = List(
    "sec-math.log",
    "sec-function-properties-of-the-math-object",
    "sec-math-object",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber x)
  |  0:let n = [? __x0__]
  |  1:if (|| (= n NaN) (= n Infinity)) return n else 2:{}
  |  2:if (= n 1i) return 0i else 2:{}
  |  3:if (|| (= n 0i) (= n -0.0)) return -Infinity else 2:{}
  |  4:if (< n 0i) return NaN else 2:{}
  |  5:??? "Return an implementation - approximated value representing the result of the natural logarithm of ℝ ( id:{n} ) ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _n_ be ? ToNumber(_x_).""",
    """          1. If _n_ is *NaN* or _n_ is *+∞*<sub>𝔽</sub>, return _n_.""",
    """          1. If _n_ is *1*<sub>𝔽</sub>, return *+0*<sub>𝔽</sub>.""",
    """          1. If _n_ is *+0*<sub>𝔽</sub> or _n_ is *-0*<sub>𝔽</sub>, return *-∞*<sub>𝔽</sub>.""",
    """          1. If _n_ < *+0*<sub>𝔽</sub>, return *NaN*.""",
    """          1. Return an implementation-approximated value representing the result of the natural logarithm of ℝ(_n_).""",
  )
}