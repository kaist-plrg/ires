package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Day` extends Algo {
  val head = NormalHead("Day", List(Param("t", Normal)))
  val ids = List(
    "eqn-Day",
    "sec-day-number-and-time-within-day",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (floor (/ t 8.64E7))
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """Day(_t_) = 𝔽(floor(ℝ(_t_ / msPerDay)))""",
  )
}
