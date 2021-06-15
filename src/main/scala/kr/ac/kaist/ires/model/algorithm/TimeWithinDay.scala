package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TimeWithinDay` extends Algo {
  val head = NormalHead("TimeWithinDay", List(Param("t", Normal)))
  val ids = List(
    "eqn-TimeWithinDay",
    "sec-day-number-and-time-within-day",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""return (%% t 8.64E7)""".stripMargin)
  val code = scala.Array[String](
    """TimeWithinDay(_t_) = 𝔽(ℝ(_t_) modulo ℝ(msPerDay))""",
  )
}
