package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Math.asin` extends Algo {
  val head = BuiltinHead(parseRef("""Math.asin"""), List(Param("x", Normal)))
  val ids = List(
    "sec-math.asin",
    "sec-function-properties-of-the-math-object",
    "sec-math-object",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber x)
  |  0:let n = [? __x0__]
  |  1:if (|| (|| (= n NaN) (= n 0i)) (= n -0.0)) return n else 2:{}
  |  2:if (|| (< 1i n) (< n -1i)) return NaN else 2:{}
  |  3:??? "Return an implementation - approximated value representing the result of the inverse sine of ℝ ( id:{n} ) ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _n_ be ? ToNumber(_x_).""",
    """          1. If _n_ is *NaN*, _n_ is *+0*<sub>𝔽</sub>, or _n_ is *-0*<sub>𝔽</sub>, return _n_.""",
    """          1. If _n_ > *1*<sub>𝔽</sub> or _n_ < *-1*<sub>𝔽</sub>, return *NaN*.""",
    """          1. Return an implementation-approximated value representing the result of the inverse sine of ℝ(_n_).""",
  )
}
