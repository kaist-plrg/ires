package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Atomics.and` extends Algo {
  val head = BuiltinHead(parseRef("""Atomics.and"""), List(Param("typedArray", Normal), Param("index", Normal), Param("value", Normal)))
  val ids = List(
    "sec-atomics.and",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:??? "Let id:{and} be a new read - modify - write modification function with parameters ( id:{xBytes} , id:{yBytes} ) that captures nothing and performs the following steps atomically when called : in:{} out:{}"
  |  2:app __x0__ = (AtomicReadModifyWrite typedArray index value and)
  |  2:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _and_ be a new read-modify-write modification function with parameters (_xBytes_, _yBytes_) that captures nothing and performs the following steps atomically when called:""",
    """          1. Return ByteListBitwiseOp(`&`, _xBytes_, _yBytes_).""",
    """        1. Return ? AtomicReadModifyWrite(_typedArray_, _index_, _value_, _and_).""",
  )
}
