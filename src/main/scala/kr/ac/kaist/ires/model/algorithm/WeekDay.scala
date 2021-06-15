package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::WeekDay` extends Algo {
  val head = NormalHead("WeekDay", List(Param("t", Normal)))
  val ids = List(
    "sec-week-day",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (Day t)
  |  0:return (%% (+ __x0__ 4i) 7i)
  |}""".stripMargin)
  val code = scala.Array[String](
    """WeekDay(_t_) = 𝔽(ℝ(Day(_t_) + *4* <sub>𝔽</sub>) modulo 7)""",
  )
}
