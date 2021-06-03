package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IntegerIndexedObjectCreate` extends Algo {
  val head = NormalHead("IntegerIndexedObjectCreate", List(Param("prototype", Normal)))
  val ids = List(
    "sec-integerindexedobjectcreate",
    "sec-integer-indexed-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let internalSlotsList = (new ["Prototype", "Extensible", "ViewedArrayBuffer", "TypedArrayName", "ContentType", "ByteLength", "ByteOffset", "ArrayLength"])
  |  1:app __x0__ = (MakeBasicObject internalSlotsList)
  |  1:let A = [! __x0__]
  |  2:A.GetOwnProperty = IntegerIndexedExoticObjectDOTGetOwnProperty
  |  3:A.HasProperty = IntegerIndexedExoticObjectDOTHasProperty
  |  4:A.DefineOwnProperty = IntegerIndexedExoticObjectDOTDefineOwnProperty
  |  5:A.Get = IntegerIndexedExoticObjectDOTGet
  |  6:A.Set = IntegerIndexedExoticObjectDOTSet
  |  7:A.Delete = IntegerIndexedExoticObjectDOTDelete
  |  8:A.OwnPropertyKeys = IntegerIndexedExoticObjectDOTOwnPropertyKeys
  |  9:A.Prototype = prototype
  |  10:return A
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _internalSlotsList_ be « [[Prototype]], [[Extensible]], [[ViewedArrayBuffer]], [[TypedArrayName]], [[ContentType]], [[ByteLength]], [[ByteOffset]], [[ArrayLength]] ».""",
    """          1. Let _A_ be ! MakeBasicObject(_internalSlotsList_).""",
    """          1. Set _A_.[[GetOwnProperty]] as specified in <emu-xref href="#sec-integer-indexed-exotic-objects-getownproperty-p"></emu-xref>.""",
    """          1. Set _A_.[[HasProperty]] as specified in <emu-xref href="#sec-integer-indexed-exotic-objects-hasproperty-p"></emu-xref>.""",
    """          1. Set _A_.[[DefineOwnProperty]] as specified in <emu-xref href="#sec-integer-indexed-exotic-objects-defineownproperty-p-desc"></emu-xref>.""",
    """          1. Set _A_.[[Get]] as specified in <emu-xref href="#sec-integer-indexed-exotic-objects-get-p-receiver"></emu-xref>.""",
    """          1. Set _A_.[[Set]] as specified in <emu-xref href="#sec-integer-indexed-exotic-objects-set-p-v-receiver"></emu-xref>.""",
    """          1. Set _A_.[[Delete]] as specified in <emu-xref href="#sec-integer-indexed-exotic-objects-delete-p"></emu-xref>.""",
    """          1. Set _A_.[[OwnPropertyKeys]] as specified in <emu-xref href="#sec-integer-indexed-exotic-objects-ownpropertykeys"></emu-xref>.""",
    """          1. Set _A_.[[Prototype]] to _prototype_.""",
    """          1. Return _A_.""",
  )
}
