package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StringCreate` extends Algo {
  val head = NormalHead("StringCreate", List(Param("value", Normal), Param("prototype", Normal)))
  val ids = List(
    "sec-stringcreate",
    "sec-string-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (MakeBasicObject (new ["Prototype", "Extensible", "StringData"]))
  |  0:let S = [! __x0__]
  |  1:S.Prototype = prototype
  |  2:S.StringData = value
  |  3:S.GetOwnProperty = ALGORITHM["StringExoticObject.GetOwnProperty"]
  |  4:S.DefineOwnProperty = ALGORITHM["StringExoticObject.DefineOwnProperty"]
  |  5:S.OwnPropertyKeys = ALGORITHM["StringExoticObject.OwnPropertyKeys"]
  |  6:let length = value.length
  |  7:app __x1__ = (DefinePropertyOrThrow S "length" (new PropertyDescriptor("Value" -> length, "Writable" -> false, "Enumerable" -> false, "Configurable" -> false)))
  |  7:[! __x1__]
  |  8:return S
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _S_ be ! MakeBasicObject(« [[Prototype]], [[Extensible]], [[StringData]] »).""",
    """          1. Set _S_.[[Prototype]] to _prototype_.""",
    """          1. Set _S_.[[StringData]] to _value_.""",
    """          1. Set _S_.[[GetOwnProperty]] as specified in <emu-xref href="#sec-string-exotic-objects-getownproperty-p"></emu-xref>.""",
    """          1. Set _S_.[[DefineOwnProperty]] as specified in <emu-xref href="#sec-string-exotic-objects-defineownproperty-p-desc"></emu-xref>.""",
    """          1. Set _S_.[[OwnPropertyKeys]] as specified in <emu-xref href="#sec-string-exotic-objects-ownpropertykeys"></emu-xref>.""",
    """          1. Let _length_ be the number of code unit elements in _value_.""",
    """          1. Perform ! DefinePropertyOrThrow(_S_, *"length"*, PropertyDescriptor { [[Value]]: 𝔽(_length_), [[Writable]]: *false*, [[Enumerable]]: *false*, [[Configurable]]: *false* }).""",
    """          1. Return _S_.""",
  )
}
