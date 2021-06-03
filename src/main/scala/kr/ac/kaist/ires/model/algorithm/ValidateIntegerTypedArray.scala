package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ValidateIntegerTypedArray` extends Algo {
  val head = NormalHead("ValidateIntegerTypedArray", List(Param("typedArray", Normal), Param("waitable", Optional)))
  val ids = List(
    "sec-validateintegertypedarray",
    "sec-abstract-operations-for-atomics",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:if (= waitable absent) waitable = false else 0:{}
  |  1:app __x0__ = (ValidateTypedArray typedArray)
  |  1:let buffer = [? __x0__]
  |  2:let typeName = typedArray.TypedArrayName
  |  3:??? "Let id:{type} be the Element Type value in link:{unhandled: table-the-typedarray-constructors} for id:{typeName} ."
  |  6:if (= waitable true) if (! (|| (= typeName "Int32Array") (= typeName "BigInt64Array"))) throw TypeError else 0:{} else {
  |    7:let __x1__ = true
  |    7:app __x2__ = (IsUnclampedIntegerElementType type)
  |    7:__x1__ = (= [! __x2__] false)
  |    7:if __x1__ {
  |      app __x3__ = (IsBigIntElementType type)
  |      __x1__ = (= [! __x3__] false)
  |    } else 0:{}
  |    7:if __x1__ throw TypeError else 0:{}
  |  }
  |  8:return buffer
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _waitable_ is not present, set _waitable_ to *false*.""",
    """          1. Let _buffer_ be ? ValidateTypedArray(_typedArray_).""",
    """          1. Let _typeName_ be _typedArray_.[[TypedArrayName]].""",
    """          1. Let _type_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _typeName_.""",
    """          1. If _waitable_ is *true*, then""",
    """            1. If _typeName_ is not *"Int32Array"* or *"BigInt64Array"*, throw a *TypeError* exception.""",
    """          1. Else,""",
    """            1. If ! IsUnclampedIntegerElementType(_type_) is *false* and ! IsBigIntElementType(_type_) is *false*, throw a *TypeError* exception.""",
    """          1. Return _buffer_.""",
  )
}
