package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AllocateTypedArray` extends Algo {
  val head = NormalHead("AllocateTypedArray", List(Param("constructorName", Normal), Param("newTarget", Normal), Param("defaultProto", Normal), Param("length", Optional)))
  val ids = List(
    "sec-allocatetypedarray",
    "sec-typedarray",
    "sec-typedarray-constructors",
    "sec-typedarray-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetPrototypeFromConstructor newTarget defaultProto)
  |  0:let proto = [? __x0__]
  |  1:app __x1__ = (IntegerIndexedObjectCreate proto)
  |  1:let obj = [! __x1__]
  |  2:assert (= obj.ViewedArrayBuffer undefined)
  |  3:obj.TypedArrayName = constructorName
  |  5:if (|| (= constructorName "BigInt64Array") (= constructorName "BigUint64Array")) obj.ContentType = CONST_BigInt else obj.ContentType = CONST_Number
  |  10:if (= length absent) {
  |    7:obj.ByteLength = 0i
  |    8:obj.ByteOffset = 0i
  |    9:obj.ArrayLength = 0i
  |  } else {
  |    11:app __x2__ = (AllocateTypedArrayBuffer obj length)
  |    11:[? __x2__]
  |  }
  |  12:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _proto_ be ? GetPrototypeFromConstructor(_newTarget_, _defaultProto_).""",
    """            1. Let _obj_ be ! IntegerIndexedObjectCreate(_proto_).""",
    """            1. Assert: _obj_.[[ViewedArrayBuffer]] is *undefined*.""",
    """            1. Set _obj_.[[TypedArrayName]] to _constructorName_.""",
    """            1. If _constructorName_ is *"BigInt64Array"* or *"BigUint64Array"*, set _obj_.[[ContentType]] to ~BigInt~.""",
    """            1. Otherwise, set _obj_.[[ContentType]] to ~Number~.""",
    """            1. If _length_ is not present, then""",
    """              1. Set _obj_.[[ByteLength]] to 0.""",
    """              1. Set _obj_.[[ByteOffset]] to 0.""",
    """              1. Set _obj_.[[ArrayLength]] to 0.""",
    """            1. Else,""",
    """              1. Perform ? AllocateTypedArrayBuffer(_obj_, _length_).""",
    """            1. Return _obj_.""",
  )
}
