package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ByteListEqual` extends Algo {
  val head = NormalHead("ByteListEqual", List(Param("xBytes", Normal), Param("yBytes", Normal)))
  val ids = List(
    "sec-bytelistequal",
    "sec-abstract-operations-for-atomics",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:??? "If id:{xBytes} and id:{yBytes} do not have the same number of elements , return value:{false} ."
  |  1:let i = 0i
  |  2:let __x0__ = xBytes
  |  2:let __x1__ = 0i
  |  2:while (< __x1__ __x0__.length) {
  |    let xByte = __x0__[__x1__]
  |    3:let yByte = yBytes[i]
  |    4:if (! (== xByte yByte)) return false else 1:{}
  |    5:i = (+ i 1i)
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  6:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _xBytes_ and _yBytes_ do not have the same number of elements, return *false*.""",
    """          1. Let _i_ be 0.""",
    """          1. For each element _xByte_ of _xBytes_, do""",
    """            1. Let _yByte_ be _yBytes_[_i_].""",
    """            1. If _xByte_ ≠ _yByte_, return *false*.""",
    """            1. Set _i_ to _i_ + 1.""",
    """          1. Return *true*.""",
  )
}
