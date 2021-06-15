package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TimeClip` extends Algo {
  val head = NormalHead("TimeClip", List(Param("time", Normal)))
  val ids = List(
    "sec-timeclip",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:if (|| (= time Infinity) (= time -Infinity)) return NaN else 2:{}
  |  1:app __x0__ = (abs time)
  |  1:if (< (* 8.64 (** 10.0 15i)) __x0__) return NaN else 2:{}
  |  2:app __x1__ = (ToIntegerOrInfinity time)
  |  2:return [! __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _time_ is not finite, return *NaN*.""",
    """          1. If abs(ℝ(_time_)) > 8.64 × 10<sup>15</sup>, return *NaN*.""",
    """          1. Return 𝔽(! ToIntegerOrInfinity(_time_)).""",
  )
}
