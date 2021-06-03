package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Atomics.exchange` extends Algo {
  val head = BuiltinHead(parseRef("""Atomics.exchange"""), List(Param("typedArray", Normal), Param("index", Normal), Param("value", Normal)))
  val ids = List(
    "sec-atomics.exchange",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:??? "Let id:{second} be a new read - modify - write modification function with parameters ( id:{oldBytes} , id:{newBytes} ) that captures nothing and performs the following steps atomically when called : in:{} out:{}"
  |  2:app __x0__ = (AtomicReadModifyWrite typedArray index value second)
  |  2:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _second_ be a new read-modify-write modification function with parameters (_oldBytes_, _newBytes_) that captures nothing and performs the following steps atomically when called:""",
    """          1. Return _newBytes_.""",
    """        1. Return ? AtomicReadModifyWrite(_typedArray_, _index_, _value_, _second_).""",
  )
}
