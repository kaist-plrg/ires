package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Math.acos` extends Algo {
  val head = BuiltinHead(parseRef("""Math.acos"""), List(Param("x", Normal)))
  val ids = List(
    "sec-math.acos",
    "sec-function-properties-of-the-math-object",
    "sec-math-object",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber x)
  |  0:let n = [? __x0__]
  |  1:if (|| (|| (= n NaN) (< 1i n)) (< n -1i)) return NaN else 2:{}
  |  2:if (= n 1i) return 0i else 2:{}
  |  3:??? "Return an implementation - approximated value representing the result of the inverse cosine of ℝ ( id:{n} ) ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _n_ be ? ToNumber(_x_).""",
    """          1. If _n_ is *NaN*, _n_ > *1*<sub>𝔽</sub>, or _n_ < *-1*<sub>𝔽</sub>, return *NaN*.""",
    """          1. If _n_ is *1*<sub>𝔽</sub>, return *+0*<sub>𝔽</sub>.""",
    """          1. Return an implementation-approximated value representing the result of the inverse cosine of ℝ(_n_).""",
  )
}
