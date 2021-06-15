package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::msPerMinute` extends Algo {
  val head = NormalHead("msPerMinute", List())
  val ids = List(
    "eqn-msPerMinute",
    "sec-hours-minutes-second-and-milliseconds",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""return (* 1000.0 SecondsPerMinute)""".stripMargin)
  val code = scala.Array[String](
    """msPerMinute = *60000* <sub>𝔽</sub> = msPerSecond × 𝔽(SecondsPerMinute)""",
  )
}
