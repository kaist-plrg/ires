package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsIntegralNumber` extends Algo {
  val head = NormalHead("IsIntegralNumber", List(Param("argument", Normal)))
  val ids = List(
    "sec-isintegralnumber",
    "sec-testing-and-comparison-operations",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof argument) Number)) return false else 0:{}
  |  1:if (|| (|| (= argument NaN) (= argument Infinity)) (= argument -Infinity)) return false else 0:{}
  |  2:app __x0__ = (abs argument)
  |  2:app __x1__ = (floor __x0__)
  |  2:app __x2__ = (abs argument)
  |  2:if (! (== __x1__ __x2__)) return false else 0:{}
  |  3:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_argument_) is not Number, return *false*.""",
    """        1. If _argument_ is *NaN*, *+∞*<sub>𝔽</sub>, or *-∞*<sub>𝔽</sub>, return *false*.""",
    """        1. If floor(abs(ℝ(_argument_))) ≠ abs(ℝ(_argument_)), return *false*.""",
    """        1. Return *true*.""",
  )
}
