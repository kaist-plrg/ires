package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Atomics.add` extends Algo {
  val head = BuiltinHead(parseRef("""Atomics.add"""), List(Param("typedArray", Normal), Param("index", Normal), Param("value", Normal)))
  val ids = List(
    "sec-atomics.add",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:??? "Let id:{type} be the Element Type value in link:{unhandled: table-the-typedarray-constructors} for id:{typedArray} . [ [ TypedArrayName ] ] ."
  |  1:let isLittleEndian = AGENT.LittleEndian
  |  2:??? "Let id:{add} be a new read - modify - write modification function with parameters ( id:{xBytes} , id:{yBytes} ) that captures id:{type} and id:{isLittleEndian} and performs the following steps atomically when called : in:{} out:{}"
  |  10:app __x0__ = (AtomicReadModifyWrite typedArray index value add)
  |  10:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _type_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _typedArray_.[[TypedArrayName]].""",
    """        1. Let _isLittleEndian_ be the value of the [[LittleEndian]] field of the surrounding agent's Agent Record.""",
    """        1. Let _add_ be a new read-modify-write modification function with parameters (_xBytes_, _yBytes_) that captures _type_ and _isLittleEndian_ and performs the following steps atomically when called:""",
    """          1. Let _x_ be RawBytesToNumeric(_type_, _xBytes_, _isLittleEndian_).""",
    """          1. Let _y_ be RawBytesToNumeric(_type_, _yBytes_, _isLittleEndian_).""",
    """          1. Let _T_ be Type(_x_).""",
    """          1. Let _sum_ be _T_::add(_x_, _y_).""",
    """          1. Let _sumBytes_ be NumericToRawBytes(_type_, _sum_, _isLittleEndian_).""",
    """          1. Assert: _sumBytes_, _xBytes_, and _yBytes_ have the same number of elements.""",
    """          1. Return _sumBytes_.""",
    """        1. Return ? AtomicReadModifyWrite(_typedArray_, _index_, _value_, _add_).""",
  )
}
