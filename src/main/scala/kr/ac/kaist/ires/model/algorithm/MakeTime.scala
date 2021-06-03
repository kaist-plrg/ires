package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MakeTime` extends Algo {
  val head = NormalHead("MakeTime", List(Param("hour", Normal), Param("min", Normal), Param("sec", Normal), Param("ms", Normal)))
  val ids = List(
    "sec-maketime",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:if (|| (|| (= hour Infinity) (= hour -Infinity)) (|| (|| (= min Infinity) (= min -Infinity)) (|| (|| (= sec Infinity) (= sec -Infinity)) (|| (= ms Infinity) (= ms -Infinity))))) return NaN else 2:{}
  |  1:app __x0__ = (ToIntegerOrInfinity hour)
  |  1:let h = [! __x0__]
  |  2:app __x1__ = (ToIntegerOrInfinity min)
  |  2:let m = [! __x1__]
  |  3:app __x2__ = (ToIntegerOrInfinity sec)
  |  3:let s = [! __x2__]
  |  4:app __x3__ = (ToIntegerOrInfinity ms)
  |  4:let milli = [! __x3__]
  |  5:??? "Let id:{t} be ( ( id:{h} code:{*} msPerHour code:{+} id:{m} code:{*} msPerMinute ) code:{+} id:{s} code:{*} msPerSecond ) code:{+} id:{milli} , performing the arithmetic according to IEEE 754 - 2019 rules ( that is , as if using the ECMAScript operators code:{*} and code:{+} ) ."
  |  6:return t
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _hour_ is not finite or _min_ is not finite or _sec_ is not finite or _ms_ is not finite, return *NaN*.""",
    """          1. Let _h_ be 𝔽(! ToIntegerOrInfinity(_hour_)).""",
    """          1. Let _m_ be 𝔽(! ToIntegerOrInfinity(_min_)).""",
    """          1. Let _s_ be 𝔽(! ToIntegerOrInfinity(_sec_)).""",
    """          1. Let _milli_ be 𝔽(! ToIntegerOrInfinity(_ms_)).""",
    """          1. Let _t_ be ((_h_ `*` msPerHour `+` _m_ `*` msPerMinute) `+` _s_ `*` msPerSecond) `+` _milli_, performing the arithmetic according to IEEE 754-2019 rules (that is, as if using the ECMAScript operators `*` and `+`).""",
    """          1. Return _t_.""",
  )
}
