package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AllocateTypedArrayBuffer` extends Algo {
  val head = NormalHead("AllocateTypedArrayBuffer", List(Param("O", Normal), Param("length", Normal)))
  val ids = List(
    "sec-allocatetypedarraybuffer",
    "sec-typedarray",
    "sec-typedarray-constructors",
    "sec-typedarray-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  1:assert (= O.ViewedArrayBuffer undefined)
  |  2:let constructorName = O.TypedArrayName
  |  3:??? "Let id:{elementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for id:{constructorName} ."
  |  4:let byteLength = (* elementSize length)
  |  5:app __x0__ = (AllocateArrayBuffer INTRINSIC_ArrayBuffer byteLength)
  |  5:let data = [? __x0__]
  |  6:O.ViewedArrayBuffer = data
  |  7:O.ByteLength = byteLength
  |  8:O.ByteOffset = 0i
  |  9:O.ArrayLength = length
  |  10:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _O_ is an Object that has a [[ViewedArrayBuffer]] internal slot.""",
    """            1. Assert: _O_.[[ViewedArrayBuffer]] is *undefined*.""",
    """            1. Let _constructorName_ be the String value of _O_.[[TypedArrayName]].""",
    """            1. Let _elementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _constructorName_.""",
    """            1. Let _byteLength_ be _elementSize_ × _length_.""",
    """            1. Let _data_ be ? AllocateArrayBuffer(%ArrayBuffer%, _byteLength_).""",
    """            1. Set _O_.[[ViewedArrayBuffer]] to _data_.""",
    """            1. Set _O_.[[ByteLength]] to _byteLength_.""",
    """            1. Set _O_.[[ByteOffset]] to 0.""",
    """            1. Set _O_.[[ArrayLength]] to _length_.""",
    """            1. Return _O_.""",
  )
}
