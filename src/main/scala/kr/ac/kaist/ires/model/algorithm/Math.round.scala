package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Math.round` extends Algo {
  val head = BuiltinHead(parseRef("""Math.round"""), List(Param("x", Normal)))
  val ids = List(
    "sec-math.round",
    "sec-function-properties-of-the-math-object",
    "sec-math-object",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber x)
  |  0:let n = [? __x0__]
  |  1:??? "If id:{n} is value:{NaN} , value:{+∞} , value:{-∞} , or an integral Number , return id:{n} ."
  |  2:if (&& (< n 0.5) (< 0i n)) return 0i else 2:{}
  |  3:if (&& (< n 0i) (! (< n -0.5))) return -0.0 else 2:{}
  |  4:??? "Return the integral Number closest to id:{n} , preferring the Number closer to + ∞ in the case of a tie ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _n_ be ? ToNumber(_x_).""",
    """          1. If _n_ is *NaN*, *+∞*<sub>𝔽</sub>, *-∞*<sub>𝔽</sub>, or an integral Number, return _n_.""",
    """          1. If _n_ < *0.5*<sub>𝔽</sub> and _n_ > *+0*<sub>𝔽</sub>, return *+0*<sub>𝔽</sub>.""",
    """          1. If _n_ < *+0*<sub>𝔽</sub> and _n_ ≥ *-0.5*<sub>𝔽</sub>, return *-0*<sub>𝔽</sub>.""",
    """          1. Return the integral Number closest to _n_, preferring the Number closer to +∞ in the case of a tie.""",
  )
}
