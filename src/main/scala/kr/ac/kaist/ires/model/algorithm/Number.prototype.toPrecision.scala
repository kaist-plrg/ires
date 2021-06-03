package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number.prototype.toPrecision` extends Algo {
  val head = BuiltinHead(parseRef("""Number.prototype.toPrecision"""), List(Param("precision", Normal)))
  val ids = List(
    "sec-number.prototype.toprecision",
    "sec-properties-of-the-number-prototype-object",
    "sec-number-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisNumberValue this)
  |  0:let x = [? __x0__]
  |  1:if (= precision undefined) {
  |    app __x1__ = (ToString x)
  |    return [! __x1__]
  |  } else 2:{}
  |  2:app __x2__ = (ToIntegerOrInfinity precision)
  |  2:let p = [? __x2__]
  |  3:if (|| (= x Infinity) (= x -Infinity)) {
  |    app __x3__ = (PRIMITIVE[Number].toString x)
  |    return [! __x3__]
  |  } else 2:{}
  |  4:if (|| (< p 1i) (< 100i p)) throw RangeError else 2:{}
  |  5:x = x
  |  6:let s = ""
  |  7:if (< x 0i) {
  |    8:s = "-"
  |    9:x = (- x)
  |  } else 2:{}
  |  13:if (== x 0i) {
  |    11:??? "Let id:{m} be the String value consisting of id:{p} occurrences of the code unit 0x0030 ( DIGIT ZERO ) ."
  |    12:let e = 0i
  |  } else {
  |    14:??? "Let id:{e} and id:{n} be integers such that 10 sup:{id:{p} - 1} ≤ id:{n} < 10 sup:{id:{p}} and for which id:{n} × 10 sup:{id:{e} - id:{p} + 1} - id:{x} is as close to zero as possible . If there are two such sets of id:{e} and id:{n} , pick the id:{e} and id:{n} for which id:{n} × 10 sup:{id:{e} - id:{p} + 1} is larger ."
  |    15:??? "Let id:{m} be the String value consisting of the digits of the decimal representation of id:{n} ( in order , with no leading zeroes ) ."
  |    16:if (|| (< e -6i) (! (< e p))) {
  |      17:assert (! (== e 0i))
  |      18:if (! (== p 1i)) {
  |        19:??? "Let id:{a} be the first code unit of id:{m} ."
  |        20:??? "Let id:{b} be the other id:{p} - 1 code units of id:{m} ."
  |        21:m = (+ (+ a ".") b)
  |      } else 2:{}
  |      24:if (< 0i e) let c = "+" else {
  |        25:assert (< e 0i)
  |        26:let c = "-"
  |        27:e = (- e)
  |      }
  |      28:??? "Let id:{d} be the String value consisting of the digits of the decimal representation of id:{e} ( in order , with no leading zeroes ) ."
  |      29:return (+ (+ (+ (+ s m) "e") c) d)
  |    } else 2:{}
  |  }
  |  30:if (== e (- p 1i)) return (+ s m) else 2:{}
  |  33:if (! (< e 0i)) ??? "Set id:{m} to the string - concatenation of the first id:{e} + 1 code units of id:{m} , the code unit 0x002E ( FULL STOP ) , and the remaining id:{p} - ( id:{e} + 1 ) code units of id:{m} ." else ??? "Set id:{m} to the string - concatenation of the code unit 0x0030 ( DIGIT ZERO ) , the code unit 0x002E ( FULL STOP ) , - ( id:{e} + 1 ) occurrences of the code unit 0x0030 ( DIGIT ZERO ) , and the String id:{m} ."
  |  35:return (+ s m)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _x_ be ? thisNumberValue(*this* value).""",
    """          1. If _precision_ is *undefined*, return ! ToString(_x_).""",
    """          1. Let _p_ be ? ToIntegerOrInfinity(_precision_).""",
    """          1. If _x_ is not finite, return ! Number::toString(_x_).""",
    """          1. If _p_ < 1 or _p_ > 100, throw a *RangeError* exception.""",
    """          1. Set _x_ to ℝ(_x_).""",
    """          1. Let _s_ be the empty String.""",
    """          1. If _x_ < 0, then""",
    """            1. Set _s_ to the code unit 0x002D (HYPHEN-MINUS).""",
    """            1. Set _x_ to -_x_.""",
    """          1. If _x_ = 0, then""",
    """            1. Let _m_ be the String value consisting of _p_ occurrences of the code unit 0x0030 (DIGIT ZERO).""",
    """            1. Let _e_ be 0.""",
    """          1. Else,""",
    """            1. Let _e_ and _n_ be integers such that 10<sup>_p_ - 1</sup> ≤ _n_ < 10<sup>_p_</sup> and for which _n_ × 10<sup>_e_ - _p_ + 1</sup> - _x_ is as close to zero as possible. If there are two such sets of _e_ and _n_, pick the _e_ and _n_ for which _n_ × 10<sup>_e_ - _p_ + 1</sup> is larger.""",
    """            1. Let _m_ be the String value consisting of the digits of the decimal representation of _n_ (in order, with no leading zeroes).""",
    """            1. If _e_ < -6 or _e_ ≥ _p_, then""",
    """              1. Assert: _e_ ≠ 0.""",
    """              1. If _p_ ≠ 1, then""",
    """                1. Let _a_ be the first code unit of _m_.""",
    """                1. Let _b_ be the other _p_ - 1 code units of _m_.""",
    """                1. Set _m_ to the string-concatenation of _a_, *"."*, and _b_.""",
    """              1. If _e_ > 0, then""",
    """                1. Let _c_ be the code unit 0x002B (PLUS SIGN).""",
    """              1. Else,""",
    """                1. Assert: _e_ < 0.""",
    """                1. Let _c_ be the code unit 0x002D (HYPHEN-MINUS).""",
    """                1. Set _e_ to -_e_.""",
    """              1. Let _d_ be the String value consisting of the digits of the decimal representation of _e_ (in order, with no leading zeroes).""",
    """              1. Return the string-concatenation of _s_, _m_, the code unit 0x0065 (LATIN SMALL LETTER E), _c_, and _d_.""",
    """          1. If _e_ = _p_ - 1, return the string-concatenation of _s_ and _m_.""",
    """          1. If _e_ ≥ 0, then""",
    """            1. Set _m_ to the string-concatenation of the first _e_ + 1 code units of _m_, the code unit 0x002E (FULL STOP), and the remaining _p_ - (_e_ + 1) code units of _m_.""",
    """          1. Else,""",
    """            1. Set _m_ to the string-concatenation of the code unit 0x0030 (DIGIT ZERO), the code unit 0x002E (FULL STOP), -(_e_ + 1) occurrences of the code unit 0x0030 (DIGIT ZERO), and the String _m_.""",
    """          1. Return the string-concatenation of _s_ and _m_.""",
  )
}
