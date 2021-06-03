package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DaysInYear` extends Algo {
  val head = NormalHead("DaysInYear", List(Param("y", Normal)))
  val ids = List(
    "eqn-DaysInYear",
    "sec-year-number",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:if (! (== (%% y 4i) 0i)) return 365i else 8:{}
  |  1:if (&& (== (%% y 4i) 0i) (! (== (%% y 100i) 0i))) return 366i else 8:{}
  |  2:if (&& (== (%% y 100i) 0i) (! (== (%% y 400i) 0i))) return 365i else 8:{}
  |  3:if (== (%% y 400i) 0i) return 366i else 8:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          = *365*<sub>𝔽</sub> if (ℝ(_y_) modulo 4) ≠ 0""",
    """          = *366*<sub>𝔽</sub> if (ℝ(_y_) modulo 4) = 0 and (ℝ(_y_) modulo 100) ≠ 0""",
    """          = *365*<sub>𝔽</sub> if (ℝ(_y_) modulo 100) = 0 and (ℝ(_y_) modulo 400) ≠ 0""",
    """          = *366*<sub>𝔽</sub> if (ℝ(_y_) modulo 400) = 0""",
  )
}
