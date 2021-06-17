package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Date` extends Algo {
  val head = BuiltinHead(parseRef("""Date"""), List(Param("values", Variadic)))
  val ids = List(
    "sec-date",
    "sec-date-constructor",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) {
  |    1:??? "Let id:{now} be the time value ( UTC ) identifying the current time ."
  |    2:app __x0__ = (ToDateString now)
  |    2:return __x0__
  |  } else 13:{}
  |  3:let numberOfArgs = values.length
  |  18:if (== numberOfArgs 0i) ??? "Let id:{dv} be the time value ( UTC ) identifying the current time ." else if (== numberOfArgs 1i) {
  |    7:let value = values[0i]
  |    10:if (&& (= (typeof value) Object) (! (= value.DateValue absent))) {
  |      9:app __x1__ = (thisTimeValue value)
  |      9:let tv = [! __x1__]
  |    } else {
  |      11:app __x2__ = (ToPrimitive value)
  |      11:let v = [? __x2__]
  |      15:if (= (typeof v) String) ??? "Let id:{tv} be the result of parsing id:{v} as a date , in exactly the same manner as for the code:{parse} method ( link:{sec-date.parse} ) ." else {
  |        16:app __x3__ = (ToNumber v)
  |        16:let tv = [? __x3__]
  |      }
  |    }
  |    17:app __x4__ = (TimeClip tv)
  |    17:let dv = __x4__
  |  } else {
  |    19:assert (! (< numberOfArgs 2i))
  |    20:app __x5__ = (ToNumber values[0i])
  |    20:let y = [? __x5__]
  |    21:app __x6__ = (ToNumber values[1i])
  |    21:let m = [? __x6__]
  |    22:if (< 2i numberOfArgs) {
  |      app __x7__ = (ToNumber values[2i])
  |      let dt = [? __x7__]
  |    } else let dt = 1i
  |    23:if (< 3i numberOfArgs) {
  |      app __x8__ = (ToNumber values[3i])
  |      let h = [? __x8__]
  |    } else let h = 0i
  |    24:if (< 4i numberOfArgs) {
  |      app __x9__ = (ToNumber values[4i])
  |      let min = [? __x9__]
  |    } else let min = 0i
  |    25:if (< 5i numberOfArgs) {
  |      app __x10__ = (ToNumber values[5i])
  |      let s = [? __x10__]
  |    } else let s = 0i
  |    26:if (< 6i numberOfArgs) {
  |      app __x11__ = (ToNumber values[6i])
  |      let milli = [? __x11__]
  |    } else let milli = 0i
  |    28:if (= y NaN) let yr = NaN else {
  |      29:app __x12__ = (ToIntegerOrInfinity y)
  |      29:let yi = [! __x12__]
  |      30:if (&& (! (< yi 0i)) (! (< 99i yi))) let yr = (+ 1900i yi) else let yr = y
  |    }
  |    31:app __x13__ = (MakeDay yr m dt)
  |    31:app __x14__ = (MakeTime h min s milli)
  |    31:app __x15__ = (MakeDate __x13__ __x14__)
  |    31:let finalDate = __x15__
  |    32:app __x16__ = (UTC finalDate)
  |    32:app __x17__ = (TimeClip __x16__)
  |    32:let dv = __x17__
  |  }
  |  33:app __x18__ = (OrdinaryCreateFromConstructor NewTarget "%Date.prototype%" (new ["DateValue"]))
  |  33:let O = [? __x18__]
  |  34:O.DateValue = dv
  |  35:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, then""",
    """            1. Let _now_ be the time value (UTC) identifying the current time.""",
    """            1. Return ToDateString(_now_).""",
    """          1. Let _numberOfArgs_ be the number of elements in _values_.""",
    """          1. If _numberOfArgs_ = 0, then""",
    """            1. Let _dv_ be the time value (UTC) identifying the current time.""",
    """          1. Else if _numberOfArgs_ = 1, then""",
    """            1. Let _value_ be _values_[0].""",
    """            1. If Type(_value_) is Object and _value_ has a [[DateValue]] internal slot, then""",
    """              1. Let _tv_ be ! thisTimeValue(_value_).""",
    """            1. Else,""",
    """              1. Let _v_ be ? ToPrimitive(_value_).""",
    """              1. If Type(_v_) is String, then""",
    """                1. Assert: The next step never returns an abrupt completion because Type(_v_) is String.""",
    """                1. Let _tv_ be the result of parsing _v_ as a date, in exactly the same manner as for the `parse` method (<emu-xref href="#sec-date.parse"></emu-xref>).""",
    """              1. Else,""",
    """                1. Let _tv_ be ? ToNumber(_v_).""",
    """            1. Let _dv_ be TimeClip(_tv_).""",
    """          1. Else,""",
    """            1. Assert: _numberOfArgs_ ≥ 2.""",
    """            1. Let _y_ be ? ToNumber(_values_[0]).""",
    """            1. Let _m_ be ? ToNumber(_values_[1]).""",
    """            1. If _numberOfArgs_ > 2, let _dt_ be ? ToNumber(_values_[2]); else let _dt_ be *1*<sub>𝔽</sub>.""",
    """            1. If _numberOfArgs_ > 3, let _h_ be ? ToNumber(_values_[3]); else let _h_ be *+0*<sub>𝔽</sub>.""",
    """            1. If _numberOfArgs_ > 4, let _min_ be ? ToNumber(_values_[4]); else let _min_ be *+0*<sub>𝔽</sub>.""",
    """            1. If _numberOfArgs_ > 5, let _s_ be ? ToNumber(_values_[5]); else let _s_ be *+0*<sub>𝔽</sub>.""",
    """            1. If _numberOfArgs_ > 6, let _milli_ be ? ToNumber(_values_[6]); else let _milli_ be *+0*<sub>𝔽</sub>.""",
    """            1. If _y_ is *NaN*, let _yr_ be *NaN*.""",
    """            1. Else,""",
    """              1. Let _yi_ be ! ToIntegerOrInfinity(_y_).""",
    """              1. If 0 ≤ _yi_ ≤ 99, let _yr_ be *1900*<sub>𝔽</sub> + 𝔽(_yi_); otherwise, let _yr_ be _y_.""",
    """            1. Let _finalDate_ be MakeDate(MakeDay(_yr_, _m_, _dt_), MakeTime(_h_, _min_, _s_, _milli_)).""",
    """            1. Let _dv_ be TimeClip(UTC(_finalDate_)).""",
    """          1. Let _O_ be ? OrdinaryCreateFromConstructor(NewTarget, *"%Date.prototype%"*, « [[DateValue]] »).""",
    """          1. Set _O_.[[DateValue]] to _dv_.""",
    """          1. Return _O_.""",
  )
}
