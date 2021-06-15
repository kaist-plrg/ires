package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::msFromTime` extends Algo {
  val head = NormalHead("msFromTime", List(Param("t", Normal)))
  val ids = List(
    "eqn-msFromTime",
    "sec-hours-minutes-second-and-milliseconds",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""return (%% t 1000.0)""".stripMargin)
  val code = scala.Array[String](
    """msFromTime(_t_) = 𝔽(ℝ(_t_) modulo msPerSecond)""",
  )
}
