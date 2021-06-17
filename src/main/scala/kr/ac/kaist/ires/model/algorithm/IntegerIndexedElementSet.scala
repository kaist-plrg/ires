package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IntegerIndexedElementSet` extends Algo {
  val head = NormalHead("IntegerIndexedElementSet", List(Param("O", Normal), Param("index", Normal), Param("value", Normal)))
  val ids = List(
    "sec-integerindexedelementset",
    "sec-integer-indexed-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  2:if (= O.ContentType CONST_BigInt) {
  |    app __x0__ = (ToBigInt value)
  |    let numValue = [? __x0__]
  |  } else {
  |    app __x1__ = (ToNumber value)
  |    let numValue = [? __x1__]
  |  }
  |  3:app __x2__ = (IsValidIntegerIndex O index)
  |  3:if (= [! __x2__] true) {
  |    4:let offset = O.ByteOffset
  |    5:let arrayTypeName = O.TypedArrayName
  |    6:??? "Let id:{elementSize} be the Element Size value specified in link:{table-the-typedarray-constructors} for id:{arrayTypeName} ."
  |    7:let indexedPosition = (+ (* index elementSize) offset)
  |    8:let elementType = CONST_Int8
  |    9:app __x3__ = (SetValueInBuffer O.ViewedArrayBuffer indexedPosition elementType numValue true CONST_Unordered)
  |    9:__x3__
  |  } else 0:{}
  |  10:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _O_ is an Integer-Indexed exotic object.""",
    """          1. If _O_.[[ContentType]] is ~BigInt~, let _numValue_ be ? ToBigInt(_value_).""",
    """          1. Otherwise, let _numValue_ be ? ToNumber(_value_).""",
    """          1. If ! IsValidIntegerIndex(_O_, _index_) is *true*, then""",
    """            1. Let _offset_ be _O_.[[ByteOffset]].""",
    """            1. Let _arrayTypeName_ be the String value of _O_.[[TypedArrayName]].""",
    """            1. Let _elementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _arrayTypeName_.""",
    """            1. Let _indexedPosition_ be (ℝ(_index_) × _elementSize_) + _offset_.""",
    """            1. Let _elementType_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _arrayTypeName_.""",
    """            1. Perform SetValueInBuffer(_O_.[[ViewedArrayBuffer]], _indexedPosition_, _elementType_, _numValue_, *true*, ~Unordered~).""",
    """          1. Return NormalCompletion(*undefined*).""",
  )
}
