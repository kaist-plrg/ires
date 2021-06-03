package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayCreate` extends Algo {
  val head = NormalHead("ArrayCreate", List(Param("length", Normal), Param("proto", Optional)))
  val ids = List(
    "sec-arraycreate",
    "sec-array-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:if (< (- (** 2.0 32i) 1i) length) throw RangeError else 8:{}
  |  1:if (= proto absent) proto = INTRINSIC_Array_prototype else 8:{}
  |  2:app __x0__ = (MakeBasicObject (new ["Prototype", "Extensible"]))
  |  2:let A = [! __x0__]
  |  3:A.Prototype = proto
  |  4:A.DefineOwnProperty = ArrayExoticObjectDOTDefineOwnProperty
  |  5:app __x1__ = (OrdinaryDefineOwnProperty A "length" (new PropertyDescriptor("Value" -> length, "Writable" -> true, "Enumerable" -> false, "Configurable" -> false)))
  |  5:[! __x1__]
  |  6:return A
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _length_ > 2<sup>32</sup> - 1, throw a *RangeError* exception.""",
    """          1. If _proto_ is not present, set _proto_ to %Array.prototype%.""",
    """          1. Let _A_ be ! MakeBasicObject(« [[Prototype]], [[Extensible]] »).""",
    """          1. Set _A_.[[Prototype]] to _proto_.""",
    """          1. Set _A_.[[DefineOwnProperty]] as specified in <emu-xref href="#sec-array-exotic-objects-defineownproperty-p-desc"></emu-xref>.""",
    """          1. Perform ! OrdinaryDefineOwnProperty(_A_, *"length"*, PropertyDescriptor { [[Value]]: 𝔽(_length_), [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *false* }).""",
    """          1. Return _A_.""",
  )
}
