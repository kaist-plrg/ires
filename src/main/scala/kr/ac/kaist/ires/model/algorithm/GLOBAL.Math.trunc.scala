package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Math.trunc` extends Algo {
  val head = BuiltinHead(parseRef("""Math.trunc"""), List(Param("x", Normal)))
  val ids = List(
    "sec-math.trunc",
    "sec-function-properties-of-the-math-object",
    "sec-math-object",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber x)
  |  0:let n = [? __x0__]
  |  1:if (|| (|| (|| (|| (= n NaN) (= n 0i)) (= n -0.0)) (= n Infinity)) (= n -Infinity)) return n else 2:{}
  |  2:if (&& (< n 1i) (< 0i n)) return 0i else 2:{}
  |  3:if (&& (< n 0i) (< -1i n)) return -0.0 else 2:{}
  |  4:??? "Return the integral Number nearest id:{n} in the direction of value:{+0} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _n_ be ? ToNumber(_x_).""",
    """          1. If _n_ is *NaN*, _n_ is *+0*<sub>𝔽</sub>, _n_ is *-0*<sub>𝔽</sub>, _n_ is *+∞*<sub>𝔽</sub>, or _n_ is *-∞*<sub>𝔽</sub>, return _n_.""",
    """          1. If _n_ < *1*<sub>𝔽</sub> and _n_ > *+0*<sub>𝔽</sub>, return *+0*<sub>𝔽</sub>.""",
    """          1. If _n_ < *+0*<sub>𝔽</sub> and _n_ > *-1*<sub>𝔽</sub>, return *-0*<sub>𝔽</sub>.""",
    """          1. Return the integral Number nearest _n_ in the direction of *+0*<sub>𝔽</sub>.""",
  )
}
