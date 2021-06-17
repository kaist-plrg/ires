package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Atomics.store` extends Algo {
  val head = BuiltinHead(parseRef("""Atomics.store"""), List(Param("typedArray", Normal), Param("index", Normal), Param("value", Normal)))
  val ids = List(
    "sec-atomics.store",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ValidateIntegerTypedArray typedArray)
  |  0:let buffer = [? __x0__]
  |  1:app __x1__ = (ValidateAtomicAccess typedArray index)
  |  1:let indexedPosition = [? __x1__]
  |  2:let arrayTypeName = typedArray.TypedArrayName
  |  4:if (|| (= arrayTypeName "BigUint64Array") (= arrayTypeName "BigInt64Array")) {
  |    app __x2__ = (ToBigInt value)
  |    let v = [? __x2__]
  |  } else {
  |    app __x3__ = (ToIntegerOrInfinity value)
  |    let v = [? __x3__]
  |  }
  |  5:app __x4__ = (IsDetachedBuffer buffer)
  |  5:if (= __x4__ true) throw TypeError else 6:{}
  |  7:let elementType = CONST_Int8
  |  8:app __x5__ = (SetValueInBuffer buffer indexedPosition elementType v true CONST_SeqCst)
  |  8:__x5__
  |  9:return v
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _buffer_ be ? ValidateIntegerTypedArray(_typedArray_).""",
    """        1. Let _indexedPosition_ be ? ValidateAtomicAccess(_typedArray_, _index_).""",
    """        1. Let _arrayTypeName_ be _typedArray_.[[TypedArrayName]].""",
    """        1. If _arrayTypeName_ is *"BigUint64Array"* or *"BigInt64Array"*, let _v_ be ? ToBigInt(_value_).""",
    """        1. Otherwise, let _v_ be 𝔽(? ToIntegerOrInfinity(_value_)).""",
    """        1. If IsDetachedBuffer(_buffer_) is *true*, throw a *TypeError* exception.""",
    """        1. NOTE: The above check is not redundant with the check in ValidateIntegerTypedArray because the call to ToBigInt or ToIntegerOrInfinity on the preceding lines can have arbitrary side effects, which could cause the buffer to become detached.""",
    """        1. Let _elementType_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _arrayTypeName_.""",
    """        1. Perform SetValueInBuffer(_buffer_, _indexedPosition_, _elementType_, _v_, *true*, ~SeqCst~).""",
    """        1. Return _v_.""",
  )
}
