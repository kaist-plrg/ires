package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToInt16` extends Algo {
  val head = NormalHead("ToInt16", List(Param("argument", Normal)))
  val ids = List(
    "sec-toint16",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber argument)
  |  0:let number = [? __x0__]
  |  1:if (|| (|| (|| (|| (= number NaN) (= number 0i)) (= number -0.0)) (= number Infinity)) (= number -Infinity)) return 0i else 0:{}
  |  2:let int = (convert number num2int )
  |  3:let int16bit = (%% int (** 2.0 16i))
  |  4:if (! (< int16bit (** 2.0 15i))) return (- int16bit (** 2.0 16i)) else return int16bit
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _number_ be ? ToNumber(_argument_).""",
    """        1. If _number_ is *NaN*, *+0*<sub>𝔽</sub>, *-0*<sub>𝔽</sub>, *+∞*<sub>𝔽</sub>, or *-∞*<sub>𝔽</sub>, return *+0*<sub>𝔽</sub>.""",
    """        1. Let _int_ be the mathematical value that is the same sign as _number_ and whose magnitude is floor(abs(ℝ(_number_))).""",
    """        1. Let _int16bit_ be _int_ modulo 2<sup>16</sup>.""",
    """        1. If _int16bit_ ≥ 2<sup>15</sup>, return 𝔽(_int16bit_ - 2<sup>16</sup>); otherwise return 𝔽(_int16bit_).""",
  )
}
