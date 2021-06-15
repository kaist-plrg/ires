package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::msPerDay` extends Algo {
  val head = NormalHead("msPerDay", List())
  val ids = List(
    "eqn-msPerDay",
    "sec-day-number-and-time-within-day",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""return 86400000i""".stripMargin)
  val code = scala.Array[String](
    """msPerDay = *86400000* <sub>𝔽</sub>""",
  )
}
