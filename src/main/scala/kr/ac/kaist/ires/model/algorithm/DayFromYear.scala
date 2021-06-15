package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DayFromYear` extends Algo {
  val head = NormalHead("DayFromYear", List(Param("y", Normal)))
  val ids = List(
    "eqn-DaysFromYear",
    "sec-year-number",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (floor (/ (- y 1969i) 4i))
  |  0:app __x1__ = (floor (/ (- y 1901i) 100i))
  |  0:app __x2__ = (floor (/ (- y 1601i) 400i))
  |  0:return (+ (- (+ (* 365i (- y 1970i)) __x0__) __x1__) __x2__)
  |}""".stripMargin)
  val code = scala.Array[String](
    """DayFromYear(_y_) = 𝔽(365 × (ℝ(_y_) - 1970) + floor((ℝ(_y_) - 1969) / 4) - floor((ℝ(_y_) - 1901) / 100) + floor((ℝ(_y_) - 1601) / 400))""",
  )
}
