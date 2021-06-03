package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ByteListBitwiseOp` extends Algo {
  val head = NormalHead("ByteListBitwiseOp", List(Param("op", Normal), Param("xBytes", Normal), Param("yBytes", Normal)))
  val ids = List(
    "sec-bytelistbitwiseop",
    "sec-abstract-operations-for-atomics",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:assert (|| (|| (= op "&") (= op "^")) (= op "|"))
  |  2:let result = (new [])
  |  3:let i = 0i
  |  4:let __x0__ = xBytes
  |  4:let __x1__ = 0i
  |  4:while (< __x1__ __x0__.length) {
  |    let xByte = __x0__[__x1__]
  |    5:let yByte = yBytes[i]
  |    7:if (= op "&") let resultByte = (& xByte yByte) else if (= op "^") let resultByte = (^ xByte yByte) else 1:{}
  |    8:??? "Else , id:{op} is code:{|} . Let id:{resultByte} be the result of applying the bitwise inclusive OR operation to id:{xByte} and id:{yByte} ."
  |    9:i = (+ i 1i)
  |    10:append resultByte -> result
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  11:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _op_ is `&`, `^`, or `|`.""",
    """          1. Assert: _xBytes_ and _yBytes_ have the same number of elements.""",
    """          1. Let _result_ be a new empty List.""",
    """          1. Let _i_ be 0.""",
    """          1. For each element _xByte_ of _xBytes_, do""",
    """            1. Let _yByte_ be _yBytes_[_i_].""",
    """            1. If _op_ is `&`, let _resultByte_ be the result of applying the bitwise AND operation to _xByte_ and _yByte_.""",
    """            1. Else if _op_ is `^`, let _resultByte_ be the result of applying the bitwise exclusive OR (XOR) operation to _xByte_ and _yByte_.""",
    """            1. Else, _op_ is `|`. Let _resultByte_ be the result of applying the bitwise inclusive OR operation to _xByte_ and _yByte_.""",
    """            1. Set _i_ to _i_ + 1.""",
    """            1. Append _resultByte_ to the end of _result_.""",
    """          1. Return _result_.""",
  )
}
