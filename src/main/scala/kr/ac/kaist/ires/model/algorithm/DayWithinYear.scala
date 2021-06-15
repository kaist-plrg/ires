package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DayWithinYear` extends Algo {
  val head = NormalHead("DayWithinYear", List(Param("t", Normal)))
  val ids = List(
    "eqn-DayWithinYear",
    "sec-month-number",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (Day t)
  |  0:app __x1__ = (YearFromTime t)
  |  0:app __x2__ = (DayFromYear __x1__)
  |  0:return (- __x0__ __x2__)
  |}""".stripMargin)
  val code = scala.Array[String](
    """DayWithinYear(_t_) = Day(_t_) - DayFromYear(YearFromTime(_t_))""",
  )
}
