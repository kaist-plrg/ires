package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number.prototype.toExponential` extends Algo {
  val head = BuiltinHead(parseRef("""Number.prototype.toExponential"""), List(Param("fractionDigits", Normal)))
  val ids = List(
    "sec-number.prototype.toexponential",
    "sec-properties-of-the-number-prototype-object",
    "sec-number-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisNumberValue this)
  |  0:let x = [? __x0__]
  |  1:app __x1__ = (ToIntegerOrInfinity fractionDigits)
  |  1:let f = [? __x1__]
  |  3:if (|| (= x Infinity) (= x -Infinity)) {
  |    app __x2__ = (PRIMITIVE[Number].toString x)
  |    return [! __x2__]
  |  } else 2:{}
  |  4:if (|| (< f 0i) (< 100i f)) throw RangeError else 2:{}
  |  5:x = x
  |  6:let s = ""
  |  7:if (< x 0i) {
  |    8:s = "-"
  |    9:x = (- x)
  |  } else 2:{}
  |  13:if (== x 0i) {
  |    11:??? "Let id:{m} be the String value consisting of id:{f} + 1 occurrences of the code unit 0x0030 ( DIGIT ZERO ) ."
  |    12:let e = 0i
  |  } else {
  |    16:if (! (= fractionDigits undefined)) ??? "Let id:{e} and id:{n} be integers such that 10 sup:{id:{f}} ≤ id:{n} < 10 sup:{id:{f} + 1} and for which id:{n} × 10 sup:{id:{e} - id:{n}} - id:{x} is as close to zero as possible . If there are two such sets of id:{e} and id:{n} , pick the id:{e} and id:{n} for which id:{n} × 10 sup:{id:{e} - id:{f}} is larger ." else ??? "Let id:{e} , id:{n} , and id:{f} be integers such that id:{f} ≥ 0 , 10 sup:{id:{f}} ≤ id:{n} < 10 sup:{id:{f} + 1} , id:{n} × 10 sup:{id:{e} - id:{f}} is id:{x} , and id:{f} is as small as possible . Note that the decimal representation of id:{n} has id:{f} + 1 digits , id:{n} is not divisible by 10 , and the least significant digit of id:{n} is not necessarily uniquely determined by these criteria ."
  |    18:??? "Let id:{m} be the String value consisting of the digits of the decimal representation of id:{n} ( in order , with no leading zeroes ) ."
  |  }
  |  19:if (! (== f 0i)) {
  |    20:??? "Let id:{a} be the first code unit of id:{m} ."
  |    21:??? "Let id:{b} be the other id:{f} code units of id:{m} ."
  |    22:m = (+ (+ a ".") b)
  |  } else 2:{}
  |  26:if (== e 0i) {
  |    24:let c = "+"
  |    25:let d = "0"
  |  } else {
  |    28:if (< 0i e) let c = "+" else {
  |      29:assert (< e 0i)
  |      30:let c = "-"
  |      31:e = (- e)
  |    }
  |    32:??? "Let id:{d} be the String value consisting of the digits of the decimal representation of id:{e} ( in order , with no leading zeroes ) ."
  |  }
  |  33:m = (+ (+ (+ m "e") c) d)
  |  34:return (+ s m)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _x_ be ? thisNumberValue(*this* value).""",
    """          1. Let _f_ be ? ToIntegerOrInfinity(_fractionDigits_).""",
    """          1. Assert: If _fractionDigits_ is *undefined*, then _f_ is 0.""",
    """          1. If _x_ is not finite, return ! Number::toString(_x_).""",
    """          1. If _f_ < 0 or _f_ > 100, throw a *RangeError* exception.""",
    """          1. Set _x_ to ℝ(_x_).""",
    """          1. Let _s_ be the empty String.""",
    """          1. If _x_ < 0, then""",
    """            1. Set _s_ to *"-"*.""",
    """            1. Set _x_ to -_x_.""",
    """          1. If _x_ = 0, then""",
    """            1. Let _m_ be the String value consisting of _f_ + 1 occurrences of the code unit 0x0030 (DIGIT ZERO).""",
    """            1. Let _e_ be 0.""",
    """          1. Else,""",
    """            1. If _fractionDigits_ is not *undefined*, then""",
    """              1. Let _e_ and _n_ be integers such that 10<sup>_f_</sup> ≤ _n_ < 10<sup>_f_ + 1</sup> and for which _n_ × 10<sup>_e_ - _n_</sup> - _x_ is as close to zero as possible. If there are two such sets of _e_ and _n_, pick the _e_ and _n_ for which _n_ × 10<sup>_e_ - _f_</sup> is larger.""",
    """            1. Else,""",
    """              1. [id="step-number-proto-toexponential-intermediate-values"] Let _e_, _n_, and _f_ be integers such that _f_ ≥ 0, 10<sup>_f_</sup> ≤ _n_ < 10<sup>_f_ + 1</sup>, _n_ × 10<sup>_e_ - _f_</sup> is _x_, and _f_ is as small as possible. Note that the decimal representation of _n_ has _f_ + 1 digits, _n_ is not divisible by 10, and the least significant digit of _n_ is not necessarily uniquely determined by these criteria.""",
    """            1. Let _m_ be the String value consisting of the digits of the decimal representation of _n_ (in order, with no leading zeroes).""",
    """          1. If _f_ ≠ 0, then""",
    """            1. Let _a_ be the first code unit of _m_.""",
    """            1. Let _b_ be the other _f_ code units of _m_.""",
    """            1. Set _m_ to the string-concatenation of _a_, *"."*, and _b_.""",
    """          1. If _e_ = 0, then""",
    """            1. Let _c_ be *"+"*.""",
    """            1. Let _d_ be *"0"*.""",
    """          1. Else,""",
    """            1. If _e_ > 0, let _c_ be *"+"*.""",
    """            1. Else,""",
    """              1. Assert: _e_ < 0.""",
    """              1. Let _c_ be *"-"*.""",
    """              1. Set _e_ to -_e_.""",
    """            1. Let _d_ be the String value consisting of the digits of the decimal representation of _e_ (in order, with no leading zeroes).""",
    """          1. Set _m_ to the string-concatenation of _m_, *"e"*, _c_, and _d_.""",
    """          1. Return the string-concatenation of _s_ and _m_.""",
  )
}
