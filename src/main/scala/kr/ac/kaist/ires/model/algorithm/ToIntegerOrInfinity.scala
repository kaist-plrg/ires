package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToIntegerOrInfinity` extends Algo {
  val head = NormalHead("ToIntegerOrInfinity", List(Param("argument", Normal)))
  val ids = List(
    "sec-tointegerorinfinity",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber argument)
  |  0:let number = [? __x0__]
  |  1:if (|| (|| (= number NaN) (= number 0i)) (= number -0.0)) return 0i else 0:{}
  |  2:if (= number Infinity) return Infinity else 0:{}
  |  3:if (= number -Infinity) return -Infinity else 0:{}
  |  4:app __x1__ = (abs number)
  |  4:app __x2__ = (floor __x1__)
  |  4:let integer = __x2__
  |  5:if (< number 0i) integer = (- integer) else 0:{}
  |  6:return integer
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _number_ be ? ToNumber(_argument_).""",
    """        1. If _number_ is *NaN*, *+0*<sub>𝔽</sub>, or *-0*<sub>𝔽</sub>, return 0.""",
    """        1. If _number_ is *+∞*<sub>𝔽</sub>, return +∞.""",
    """        1. If _number_ is *-∞*<sub>𝔽</sub>, return -∞.""",
    """        1. Let _integer_ be floor(abs(ℝ(_number_))).""",
    """        1. If _number_ < *+0*<sub>𝔽</sub>, set _integer_ to -_integer_.""",
    """        1. Return _integer_.""",
  )
}
