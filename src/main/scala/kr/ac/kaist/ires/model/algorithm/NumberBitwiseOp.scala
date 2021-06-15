package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NumberBitwiseOp` extends Algo {
  val head = NormalHead("NumberBitwiseOp", List(Param("op", Normal), Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-numberbitwiseop",
    "sec-ecmascript-language-types-number-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:assert (|| (|| (= op "&") (= op "^")) (= op "|"))
  |  1:app __x0__ = (ToInt32 x)
  |  1:let lnum = [! __x0__]
  |  2:app __x1__ = (ToInt32 y)
  |  2:let rnum = [! __x1__]
  |  3:let lbits = lnum
  |  4:let rbits = rnum
  |  6:if (= op "&") let result = (& lbits rbits) else if (= op "^") let result = (^ lbits rbits) else 9:{}
  |  7:if (= op "|") let result = (| lbits rbits) else let result = 0i
  |  8:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _op_ is `&`, `^`, or `|`.""",
    """            1. Let _lnum_ be ! ToInt32(_x_).""",
    """            1. Let _rnum_ be ! ToInt32(_y_).""",
    """            1. Let _lbits_ be the 32-bit two's complement bit string representing ℝ(_lnum_).""",
    """            1. Let _rbits_ be the 32-bit two's complement bit string representing ℝ(_rnum_).""",
    """            1. If _op_ is `&`, let _result_ be the result of applying the bitwise AND operation to _lbits_ and _rbits_.""",
    """            1. Else if _op_ is `^`, let _result_ be the result of applying the bitwise exclusive OR (XOR) operation to _lbits_ and _rbits_.""",
    """            1. Else, _op_ is `|`. Let _result_ be the result of applying the bitwise inclusive OR operation to _lbits_ and _rbits_.""",
    """            1. Return the Number value for the integer represented by the 32-bit two's complement bit string _result_.""",
  )
}
