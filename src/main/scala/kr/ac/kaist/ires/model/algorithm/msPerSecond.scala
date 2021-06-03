package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::msPerSecond` extends Algo {
  val head = NormalHead("msPerSecond", List())
  val ids = List(
    "eqn-msPerSecond",
    "sec-hours-minutes-second-and-milliseconds",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""return 1000i""".stripMargin)
  val code = scala.Array[String](
    """msPerSecond = *1000* <sub>𝔽</sub>""",
  )
}
