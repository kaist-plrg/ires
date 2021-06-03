package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UTC` extends Algo {
  val head = NormalHead("UTC", List(Param("t", Normal)))
  val ids = List(
    "sec-utc-t",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (LocalTZA t false)
  |  0:return (- t __x0__)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return _t_ - LocalTZA(_t_, *false*).""",
  )
}
