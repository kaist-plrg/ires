package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IntegerIndexedElementGet` extends Algo {
  val head = NormalHead("IntegerIndexedElementGet", List(Param("O", Normal), Param("index", Normal)))
  val ids = List(
    "sec-integerindexedelementget",
    "sec-integer-indexed-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (IsValidIntegerIndex O index)
  |  1:if (= [! __x0__] false) return undefined else 0:{}
  |  2:let offset = O.ByteOffset
  |  3:let arrayTypeName = O.TypedArrayName
  |  4:??? "Let id:{elementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for id:{arrayTypeName} ."
  |  5:let indexedPosition = (+ (* index elementSize) offset)
  |  6:let elementType = CONST_Int8
  |  7:app __x1__ = (GetValueFromBuffer O.ViewedArrayBuffer indexedPosition elementType true CONST_Unordered)
  |  7:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _O_ is an Integer-Indexed exotic object.""",
    """          1. If ! IsValidIntegerIndex(_O_, _index_) is *false*, return *undefined*.""",
    """          1. Let _offset_ be _O_.[[ByteOffset]].""",
    """          1. Let _arrayTypeName_ be the String value of _O_.[[TypedArrayName]].""",
    """          1. Let _elementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _arrayTypeName_.""",
    """          1. Let _indexedPosition_ be (ℝ(_index_) × _elementSize_) + _offset_.""",
    """          1. Let _elementType_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _arrayTypeName_.""",
    """          1. Return GetValueFromBuffer(_O_.[[ViewedArrayBuffer]], _indexedPosition_, _elementType_, *true*, ~Unordered~).""",
  )
}
