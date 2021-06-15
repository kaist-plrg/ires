package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ValidateAtomicAccess` extends Algo {
  val head = NormalHead("ValidateAtomicAccess", List(Param("typedArray", Normal), Param("requestIndex", Normal)))
  val ids = List(
    "sec-validateatomicaccess",
    "sec-abstract-operations-for-atomics",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  1:let length = typedArray.ArrayLength
  |  2:app __x0__ = (ToIndex requestIndex)
  |  2:let accessIndex = [? __x0__]
  |  3:assert (! (< accessIndex 0i))
  |  4:if (! (< accessIndex length)) throw RangeError else 0:{}
  |  5:let arrayTypeName = typedArray.TypedArrayName
  |  6:??? "Let id:{elementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for id:{arrayTypeName} ."
  |  7:let offset = typedArray.ByteOffset
  |  8:return (+ (* accessIndex elementSize) offset)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _typedArray_ is an Object that has a [[ViewedArrayBuffer]] internal slot.""",
    """          1. Let _length_ be _typedArray_.[[ArrayLength]].""",
    """          1. Let _accessIndex_ be ? ToIndex(_requestIndex_).""",
    """          1. Assert: _accessIndex_ ≥ 0.""",
    """          1. If _accessIndex_ ≥ _length_, throw a *RangeError* exception.""",
    """          1. Let _arrayTypeName_ be _typedArray_.[[TypedArrayName]].""",
    """          1. Let _elementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _arrayTypeName_.""",
    """          1. Let _offset_ be _typedArray_.[[ByteOffset]].""",
    """          1. Return (_accessIndex_ × _elementSize_) + _offset_.""",
  )
}
