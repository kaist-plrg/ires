package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToUint32` extends Algo {
  val head = NormalHead("ToUint32", List(Param("argument", Normal)))
  val ids = List(
    "sec-touint32",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber argument)
  |  0:let number = [? __x0__]
  |  1:if (|| (|| (|| (|| (= number NaN) (= number 0i)) (= number -0.0)) (= number Infinity)) (= number -Infinity)) return 0i else 0:{}
  |  2:let int = (convert number num2int )
  |  3:let int32bit = (%% int (** 2.0 32i))
  |  4:return int32bit
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _number_ be ? ToNumber(_argument_).""",
    """        1. If _number_ is *NaN*, *+0*<sub>𝔽</sub>, *-0*<sub>𝔽</sub>, *+∞*<sub>𝔽</sub>, or *-∞*<sub>𝔽</sub>, return *+0*<sub>𝔽</sub>.""",
    """        1. Let _int_ be the mathematical value that is the same sign as _number_ and whose magnitude is floor(abs(ℝ(_number_))).""",
    """        1. Let _int32bit_ be _int_ modulo 2<sup>32</sup>.""",
    """        1. [id="step-touint32-return"] Return 𝔽(_int32bit_).""",
  )
}
