package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SecFromTime` extends Algo {
  val head = NormalHead("SecFromTime", List(Param("t", Normal)))
  val ids = List(
    "eqn-SecFromTime",
    "sec-hours-minutes-second-and-milliseconds",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (floor (/ t 1000.0))
  |  0:return (%% __x0__ SecondsPerMinute)
  |}""".stripMargin)
  val code = scala.Array[String](
    """SecFromTime(_t_) = 𝔽(floor(ℝ(_t_ / msPerSecond)) modulo SecondsPerMinute)""",
  )
}
