package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateUnmappedArgumentsObject` extends Algo {
  val head = NormalHead("CreateUnmappedArgumentsObject", List(Param("argumentsList", Normal)))
  val ids = List(
    "sec-createunmappedargumentsobject",
    "sec-arguments-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let len = argumentsList.length
  |  1:app __x0__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype (new ["ParameterMap"]))
  |  1:let obj = [! __x0__]
  |  2:obj.ParameterMap = undefined
  |  3:app __x1__ = (DefinePropertyOrThrow obj "length" (new PropertyDescriptor("Value" -> len, "Writable" -> true, "Enumerable" -> false, "Configurable" -> true)))
  |  3:__x1__
  |  4:let index = 0i
  |  5:while (< index len) {
  |    6:let val = argumentsList[index]
  |    7:app __x2__ = (ToString index)
  |    7:app __x3__ = (CreateDataPropertyOrThrow obj [! __x2__] val)
  |    7:[! __x3__]
  |    8:index = (+ index 1i)
  |  }
  |  9:app __x4__ = (DefinePropertyOrThrow obj SYMBOL_iterator (new PropertyDescriptor("Value" -> INTRINSIC_Array_prototype_values, "Writable" -> true, "Enumerable" -> false, "Configurable" -> true)))
  |  9:[! __x4__]
  |  10:app __x5__ = (DefinePropertyOrThrow obj "callee" (new PropertyDescriptor("Get" -> INTRINSIC_ThrowTypeError, "Set" -> INTRINSIC_ThrowTypeError, "Enumerable" -> false, "Configurable" -> false)))
  |  10:[! __x5__]
  |  11:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _len_ be the number of elements in _argumentsList_.""",
    """          1. Let _obj_ be ! OrdinaryObjectCreate(%Object.prototype%, « [[ParameterMap]] »).""",
    """          1. Set _obj_.[[ParameterMap]] to *undefined*.""",
    """          1. Perform DefinePropertyOrThrow(_obj_, *"length"*, PropertyDescriptor { [[Value]]: 𝔽(_len_), [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *true* }).""",
    """          1. Let _index_ be 0.""",
    """          1. Repeat, while _index_ < _len_,""",
    """            1. Let _val_ be _argumentsList_[_index_].""",
    """            1. Perform ! CreateDataPropertyOrThrow(_obj_, ! ToString(𝔽(_index_)), _val_).""",
    """            1. Set _index_ to _index_ + 1.""",
    """          1. Perform ! DefinePropertyOrThrow(_obj_, @@iterator, PropertyDescriptor { [[Value]]: %Array.prototype.values%, [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *true* }).""",
    """          1. Perform ! DefinePropertyOrThrow(_obj_, *"callee"*, PropertyDescriptor { [[Get]]: %ThrowTypeError%, [[Set]]: %ThrowTypeError%, [[Enumerable]]: *false*, [[Configurable]]: *false* }).""",
    """          1. Return _obj_.""",
  )
}
