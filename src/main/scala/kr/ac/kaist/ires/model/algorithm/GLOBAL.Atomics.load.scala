package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Atomics.load` extends Algo {
  val head = BuiltinHead(parseRef("""Atomics.load"""), List(Param("typedArray", Normal), Param("index", Normal)))
  val ids = List(
    "sec-atomics.load",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ValidateIntegerTypedArray typedArray)
  |  0:let buffer = [? __x0__]
  |  1:app __x1__ = (ValidateAtomicAccess typedArray index)
  |  1:let indexedPosition = [? __x1__]
  |  2:app __x2__ = (IsDetachedBuffer buffer)
  |  2:if (= __x2__ true) throw TypeError else 3:{}
  |  4:let arrayTypeName = typedArray.TypedArrayName
  |  5:let elementType = CONST_Int8
  |  6:app __x3__ = (GetValueFromBuffer buffer indexedPosition elementType true CONST_SeqCst)
  |  6:return __x3__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _buffer_ be ? ValidateIntegerTypedArray(_typedArray_).""",
    """        1. Let _indexedPosition_ be ? ValidateAtomicAccess(_typedArray_, _index_).""",
    """        1. If IsDetachedBuffer(_buffer_) is *true*, throw a *TypeError* exception.""",
    """        1. NOTE: The above check is not redundant with the check in ValidateIntegerTypedArray because the call to ValidateAtomicAccess on the preceding line can have arbitrary side effects, which could cause the buffer to become detached.""",
    """        1. Let _arrayTypeName_ be _typedArray_.[[TypedArrayName]].""",
    """        1. Let _elementType_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _arrayTypeName_.""",
    """        1. Return GetValueFromBuffer(_buffer_, _indexedPosition_, _elementType_, *true*, ~SeqCst~).""",
  )
}
