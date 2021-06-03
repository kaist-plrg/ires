package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SecondsPerMinute` extends Algo {
  val head = NormalHead("SecondsPerMinute", List())
  val ids = List(
    "eqn-SecondsPerMinute",
    "sec-hours-minutes-second-and-milliseconds",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""return 60i""".stripMargin)
  val code = scala.Array[String](
    """SecondsPerMinute = 60""",
  )
}
