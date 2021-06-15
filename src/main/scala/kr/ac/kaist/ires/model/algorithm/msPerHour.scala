package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::msPerHour` extends Algo {
  val head = NormalHead("msPerHour", List())
  val ids = List(
    "eqn-msPerHour",
    "sec-hours-minutes-second-and-milliseconds",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""return (* 60000.0 MinutesPerHour)""".stripMargin)
  val code = scala.Array[String](
    """msPerHour = *3600000* <sub>𝔽</sub> = msPerMinute × 𝔽(MinutesPerHour)""",
  )
}
