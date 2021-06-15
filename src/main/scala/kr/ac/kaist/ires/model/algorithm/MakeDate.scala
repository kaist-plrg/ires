package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MakeDate` extends Algo {
  val head = NormalHead("MakeDate", List(Param("day", Normal), Param("time", Normal)))
  val ids = List(
    "sec-makedate",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:if (|| (|| (= day Infinity) (= day -Infinity)) (|| (= time Infinity) (= time -Infinity))) return NaN else 2:{}
  |  1:let tv = (+ (* day 8.64E7) time)
  |  2:if (|| (= tv Infinity) (= tv -Infinity)) return NaN else 2:{}
  |  3:return tv
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _day_ is not finite or _time_ is not finite, return *NaN*.""",
    """          1. Let _tv_ be _day_ × msPerDay + _time_.""",
    """          1. If _tv_ is not finite, return *NaN*.""",
    """          1. Return _tv_.""",
  )
}
