package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TimeFromYear` extends Algo {
  val head = NormalHead("TimeFromYear", List(Param("y", Normal)))
  val ids = List(
    "eqn-TimeFromYear",
    "sec-year-number",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (DayFromYear y)
  |  0:return (* 8.64E7 __x0__)
  |}""".stripMargin)
  val code = scala.Array[String](
    """TimeFromYear(_y_) = msPerDay × DayFromYear(_y_)""",
  )
}
