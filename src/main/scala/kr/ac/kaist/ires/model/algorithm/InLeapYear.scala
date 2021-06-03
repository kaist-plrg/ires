package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InLeapYear` extends Algo {
  val head = NormalHead("InLeapYear", List(Param("t", Normal)))
  val ids = List(
    "eqn-InLeapYear",
    "sec-year-number",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (YearFromTime t)
  |  0:app __x1__ = (DaysInYear __x0__)
  |  0:if (== __x1__ 365i) return 0i else 8:{}
  |  1:app __x2__ = (YearFromTime t)
  |  1:app __x3__ = (DaysInYear __x2__)
  |  1:if (== __x3__ 366i) return 1i else 8:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          = *+0*<sub>𝔽</sub> if DaysInYear(YearFromTime(_t_)) = *365*<sub>𝔽</sub>""",
    """          = *1*<sub>𝔽</sub> if DaysInYear(YearFromTime(_t_)) = *366*<sub>𝔽</sub>""",
  )
}
