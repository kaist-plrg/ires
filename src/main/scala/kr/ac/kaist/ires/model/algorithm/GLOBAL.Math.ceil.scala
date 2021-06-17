package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Math.ceil` extends Algo {
  val head = BuiltinHead(parseRef("""Math.ceil"""), List(Param("x", Normal)))
  val ids = List(
    "sec-math.ceil",
    "sec-function-properties-of-the-math-object",
    "sec-math-object",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber x)
  |  0:let n = [? __x0__]
  |  1:if (|| (|| (|| (|| (= n NaN) (= n 0i)) (= n -0.0)) (= n Infinity)) (= n -Infinity)) return n else 2:{}
  |  2:if (&& (< n 0i) (< -1i n)) return -0.0 else 2:{}
  |  3:??? "If id:{n} is an integral Number , return id:{n} ."
  |  4:??? "Return the smallest ( closest to - ∞ ) integral Number value that is not less than id:{n} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _n_ be ? ToNumber(_x_).""",
    """          1. If _n_ is *NaN*, _n_ is *+0*<sub>𝔽</sub>, _n_ is *-0*<sub>𝔽</sub>, _n_ is *+∞*<sub>𝔽</sub>, or _n_ is *-∞*<sub>𝔽</sub>, return _n_.""",
    """          1. If _n_ < *+0*<sub>𝔽</sub> and _n_ > *-1*<sub>𝔽</sub>, return *-0*<sub>𝔽</sub>.""",
    """          1. If _n_ is an integral Number, return _n_.""",
    """          1. Return the smallest (closest to -∞) integral Number value that is not less than _n_.""",
  )
}
