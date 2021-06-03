package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigIntBitwiseOp` extends Algo {
  val head = NormalHead("BigIntBitwiseOp", List(Param("op", Normal), Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-bigintbitwiseop",
    "sec-ecmascript-language-types-bigint-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:assert (|| (|| (= op "&") (= op "^")) (= op "|"))
  |  1:x = x
  |  2:y = y
  |  3:let result = 0i
  |  4:let shift = 0i
  |  5:while AnyBool {
  |    6:let xDigit = (%% x 2i)
  |    7:let yDigit = (%% y 2i)
  |    10:if (= op "&") {
  |      app __x0__ = (BinaryAnd xDigit yDigit)
  |      result = (* (+ result (** 2.0 shift)) __x0__)
  |    } else if (= op "|") {
  |      app __x1__ = (BinaryOr xDigit yDigit)
  |      result = (* (+ result (** 2.0 shift)) __x1__)
  |    } else {
  |      11:assert (= op "^")
  |      12:app __x2__ = (BinaryXor xDigit yDigit)
  |      12:result = (* (+ result (** 2.0 shift)) __x2__)
  |    }
  |    13:shift = (+ shift 1i)
  |    14:x = (/ (- x xDigit) 2i)
  |    15:y = (/ (- y yDigit) 2i)
  |  }
  |  18:if (= op "&") {
  |    app __x3__ = (BinaryAnd (%% x 2i) (%% y 2i))
  |    let tmp = __x3__
  |  } else if (= op "|") {
  |    app __x4__ = (BinaryOr (%% x 2i) (%% y 2i))
  |    let tmp = __x4__
  |  } else {
  |    19:assert (= op "^")
  |    20:app __x5__ = (BinaryXor (%% x 2i) (%% y 2i))
  |    20:let tmp = __x5__
  |  }
  |  21:if (! (== tmp 0i)) result = (- result (** 2.0 shift)) else 23:{}
  |  24:return (convert result num2bigint )
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _op_ is `&`, `^`, or `|`.""",
    """            1. Set _x_ to ℝ(_x_).""",
    """            1. Set _y_ to ℝ(_y_).""",
    """            1. Let _result_ be 0.""",
    """            1. Let _shift_ be 0.""",
    """            1. Repeat, until (_x_ = 0 or _x_ = -1) and (_y_ = 0 or _y_ = -1),""",
    """              1. Let _xDigit_ be _x_ modulo 2.""",
    """              1. Let _yDigit_ be _y_ modulo 2.""",
    """              1. If _op_ is `&`, set _result_ to _result_ + 2<sup>_shift_</sup> × BinaryAnd(_xDigit_, _yDigit_).""",
    """              1. Else if _op_ is `|`, set _result_ to _result_ + 2<sup>_shift_</sup> × BinaryOr(_xDigit_, _yDigit_).""",
    """              1. Else,""",
    """                1. Assert: _op_ is `^`.""",
    """                1. Set _result_ to _result_ + 2<sup>_shift_</sup> × BinaryXor(_xDigit_, _yDigit_).""",
    """              1. Set _shift_ to _shift_ + 1.""",
    """              1. Set _x_ to (_x_ - _xDigit_) / 2.""",
    """              1. Set _y_ to (_y_ - _yDigit_) / 2.""",
    """            1. If _op_ is `&`, let _tmp_ be BinaryAnd(_x_ modulo 2, _y_ modulo 2).""",
    """            1. Else if _op_ is `|`, let _tmp_ be BinaryOr(_x_ modulo 2, _y_ modulo 2).""",
    """            1. Else,""",
    """              1. Assert: _op_ is `^`.""",
    """              1. Let _tmp_ be BinaryXor(_x_ modulo 2, _y_ modulo 2).""",
    """            1. If _tmp_ ≠ 0, then""",
    """              1. Set _result_ to _result_ - 2<sup>_shift_</sup>.""",
    """              1. NOTE: This extends the sign.""",
    """            1. Return the BigInt value for _result_.""",
  )
}
