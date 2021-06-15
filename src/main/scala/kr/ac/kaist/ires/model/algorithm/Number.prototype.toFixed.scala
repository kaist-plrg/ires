package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number.prototype.toFixed` extends Algo {
  val head = BuiltinHead(parseRef("""Number.prototype.toFixed"""), List(Param("fractionDigits", Normal)))
  val ids = List(
    "sec-number.prototype.tofixed",
    "sec-properties-of-the-number-prototype-object",
    "sec-number-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisNumberValue this)
  |  0:let x = [? __x0__]
  |  1:app __x1__ = (ToIntegerOrInfinity fractionDigits)
  |  1:let f = [? __x1__]
  |  3:if (|| (= f Infinity) (= f -Infinity)) throw RangeError else 2:{}
  |  4:if (|| (< f 0i) (< 100i f)) throw RangeError else 2:{}
  |  5:if (|| (= x Infinity) (= x -Infinity)) {
  |    app __x2__ = (PRIMITIVE[Number].toString x)
  |    return [! __x2__]
  |  } else 2:{}
  |  6:x = x
  |  7:let s = ""
  |  8:if (< x 0i) {
  |    9:s = "-"
  |    10:x = (- x)
  |  } else 2:{}
  |  13:if (! (< x (** 10.0 21i))) {
  |    12:app __x3__ = (ToString x)
  |    12:let m = [! __x3__]
  |  } else {
  |    14:??? "Let id:{n} be an integer for which id:{n} / 10 sup:{id:{f}} - id:{x} is as close to zero as possible . If there are two such id:{n} , pick the larger id:{n} ."
  |    15:??? "If id:{n} = 0 , let id:{m} be the String value:{\"0\"} . Otherwise , let id:{m} be the String value consisting of the digits of the decimal representation of id:{n} ( in order , with no leading zeroes ) ."
  |    16:if (! (== f 0i)) {
  |      17:let k = m.length
  |      18:if (! (< f k)) {
  |        19:??? "Let id:{z} be the String value consisting of id:{f} + 1 - id:{k} occurrences of the code unit 0x0030 ( DIGIT ZERO ) ."
  |        20:m = (+ z m)
  |        21:k = (+ f 1i)
  |      } else 2:{}
  |      22:??? "Let id:{a} be the first id:{k} - id:{f} code units of id:{m} ."
  |      23:??? "Let id:{b} be the other id:{f} code units of id:{m} ."
  |      24:m = (+ (+ a ".") b)
  |    } else 2:{}
  |  }
  |  25:return (+ s m)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _x_ be ? thisNumberValue(*this* value).""",
    """          1. Let _f_ be ? ToIntegerOrInfinity(_fractionDigits_).""",
    """          1. Assert: If _fractionDigits_ is *undefined*, then _f_ is 0.""",
    """          1. If _f_ is not finite, throw a *RangeError* exception.""",
    """          1. If _f_ < 0 or _f_ > 100, throw a *RangeError* exception.""",
    """          1. If _x_ is not finite, return ! Number::toString(_x_).""",
    """          1. Set _x_ to ℝ(_x_).""",
    """          1. Let _s_ be the empty String.""",
    """          1. If _x_ < 0, then""",
    """            1. Set _s_ to *"-"*.""",
    """            1. Set _x_ to -_x_.""",
    """          1. If _x_ ≥ 10<sup>21</sup>, then""",
    """            1. Let _m_ be ! ToString(𝔽(_x_)).""",
    """          1. Else,""",
    """            1. Let _n_ be an integer for which _n_ / 10<sup>_f_</sup> - _x_ is as close to zero as possible. If there are two such _n_, pick the larger _n_.""",
    """            1. If _n_ = 0, let _m_ be the String *"0"*. Otherwise, let _m_ be the String value consisting of the digits of the decimal representation of _n_ (in order, with no leading zeroes).""",
    """            1. If _f_ ≠ 0, then""",
    """              1. Let _k_ be the length of _m_.""",
    """              1. If _k_ ≤ _f_, then""",
    """                1. Let _z_ be the String value consisting of _f_ + 1 - _k_ occurrences of the code unit 0x0030 (DIGIT ZERO).""",
    """                1. Set _m_ to the string-concatenation of _z_ and _m_.""",
    """                1. Set _k_ to _f_ + 1.""",
    """              1. Let _a_ be the first _k_ - _f_ code units of _m_.""",
    """              1. Let _b_ be the other _f_ code units of _m_.""",
    """              1. Set _m_ to the string-concatenation of _a_, *"."*, and _b_.""",
    """          1. Return the string-concatenation of _s_ and _m_.""",
  )
}
