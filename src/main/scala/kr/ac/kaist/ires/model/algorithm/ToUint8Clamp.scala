package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToUint8Clamp` extends Algo {
  val head = NormalHead("ToUint8Clamp", List(Param("argument", Normal)))
  val ids = List(
    "sec-touint8clamp",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber argument)
  |  0:let number = [? __x0__]
  |  1:if (= number NaN) return 0i else 0:{}
  |  2:if (! (< 0i number)) return 0i else 0:{}
  |  3:if (! (< number 255i)) return 255i else 0:{}
  |  4:app __x1__ = (floor number)
  |  4:let f = __x1__
  |  5:if (< (+ f 0.5) number) return (+ f 1i) else 0:{}
  |  6:if (< number (+ f 0.5)) return f else 0:{}
  |  7:if (= (% f 2i) 1i) return (+ f 1i) else 0:{}
  |  8:return f
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _number_ be ? ToNumber(_argument_).""",
    """        1. If _number_ is *NaN*, return *+0*<sub>𝔽</sub>.""",
    """        1. If ℝ(_number_) ≤ 0, return *+0*<sub>𝔽</sub>.""",
    """        1. If ℝ(_number_) ≥ 255, return *255*<sub>𝔽</sub>.""",
    """        1. Let _f_ be floor(ℝ(_number_)).""",
    """        1. If _f_ + 0.5 < ℝ(_number_), return 𝔽(_f_ + 1).""",
    """        1. If ℝ(_number_) < _f_ + 0.5, return 𝔽(_f_).""",
    """        1. If _f_ is odd, return 𝔽(_f_ + 1).""",
    """        1. Return 𝔽(_f_).""",
  )
}
