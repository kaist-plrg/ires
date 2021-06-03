package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateMappedArgumentsObject` extends Algo {
  val head = NormalHead("CreateMappedArgumentsObject", List(Param("func", Normal), Param("formals", Normal), Param("argumentsList", Normal), Param("env", Normal)))
  val ids = List(
    "sec-createmappedargumentsobject",
    "sec-arguments-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:let len = argumentsList.length
  |  2:app __x0__ = (MakeBasicObject (new ["Prototype", "Extensible", "ParameterMap"]))
  |  2:let obj = [! __x0__]
  |  3:obj.GetOwnProperty = ArgumentsExoticObjectDOTGetOwnProperty
  |  4:obj.DefineOwnProperty = ArgumentsExoticObjectDOTDefineOwnProperty
  |  5:obj.Get = ArgumentsExoticObjectDOTGet
  |  6:obj.Set = ArgumentsExoticObjectDOTSet
  |  7:obj.Delete = ArgumentsExoticObjectDOTDelete
  |  8:obj.Prototype = INTRINSIC_Object_prototype
  |  9:app __x1__ = (OrdinaryObjectCreate null)
  |  9:let map = [! __x1__]
  |  10:obj.ParameterMap = map
  |  11:access __x2__ = (formals "BoundNames")
  |  11:let parameterNames = __x2__
  |  12:let numberOfParameters = parameterNames.length
  |  13:let index = 0i
  |  14:while (< index len) {
  |    15:let val = argumentsList[index]
  |    16:app __x3__ = (ToString index)
  |    16:app __x4__ = (CreateDataPropertyOrThrow obj [! __x3__] val)
  |    16:[! __x4__]
  |    17:index = (+ index 1i)
  |  }
  |  18:app __x5__ = (DefinePropertyOrThrow obj "length" (new PropertyDescriptor("Value" -> len, "Writable" -> true, "Enumerable" -> false, "Configurable" -> true)))
  |  18:[! __x5__]
  |  19:let mappedNames = (new [])
  |  20:let index = (- numberOfParameters 1i)
  |  21:while (! (< index 0i)) {
  |    22:let name = parameterNames[index]
  |    23:if (! (contains mappedNames name)) {
  |      24:append name -> mappedNames
  |      25:if (< index len) {
  |        26:app __x6__ = (MakeArgGetter name env)
  |        26:let g = __x6__
  |        27:app __x7__ = (MakeArgSetter name env)
  |        27:let p = __x7__
  |        28:app __x8__ = (ToString index)
  |        28:app __x9__ = (map.DefineOwnProperty map [! __x8__] (new PropertyDescriptor("Set" -> p, "Get" -> g, "Enumerable" -> false, "Configurable" -> true)))
  |        28:__x9__
  |      } else 0:{}
  |    } else 0:{}
  |    29:index = (- index 1i)
  |  }
  |  30:app __x10__ = (DefinePropertyOrThrow obj SYMBOL_iterator (new PropertyDescriptor("Value" -> INTRINSIC_Array_prototype_values, "Writable" -> true, "Enumerable" -> false, "Configurable" -> true)))
  |  30:[! __x10__]
  |  31:app __x11__ = (DefinePropertyOrThrow obj "callee" (new PropertyDescriptor("Value" -> func, "Writable" -> true, "Enumerable" -> false, "Configurable" -> true)))
  |  31:[! __x11__]
  |  32:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _formals_ does not contain a rest parameter, any binding patterns, or any initializers. It may contain duplicate identifiers.""",
    """          1. Let _len_ be the number of elements in _argumentsList_.""",
    """          1. Let _obj_ be ! MakeBasicObject(« [[Prototype]], [[Extensible]], [[ParameterMap]] »).""",
    """          1. Set _obj_.[[GetOwnProperty]] as specified in <emu-xref href="#sec-arguments-exotic-objects-getownproperty-p"></emu-xref>.""",
    """          1. Set _obj_.[[DefineOwnProperty]] as specified in <emu-xref href="#sec-arguments-exotic-objects-defineownproperty-p-desc"></emu-xref>.""",
    """          1. Set _obj_.[[Get]] as specified in <emu-xref href="#sec-arguments-exotic-objects-get-p-receiver"></emu-xref>.""",
    """          1. Set _obj_.[[Set]] as specified in <emu-xref href="#sec-arguments-exotic-objects-set-p-v-receiver"></emu-xref>.""",
    """          1. Set _obj_.[[Delete]] as specified in <emu-xref href="#sec-arguments-exotic-objects-delete-p"></emu-xref>.""",
    """          1. Set _obj_.[[Prototype]] to %Object.prototype%.""",
    """          1. Let _map_ be ! OrdinaryObjectCreate(*null*).""",
    """          1. Set _obj_.[[ParameterMap]] to _map_.""",
    """          1. Let _parameterNames_ be the BoundNames of _formals_.""",
    """          1. Let _numberOfParameters_ be the number of elements in _parameterNames_.""",
    """          1. Let _index_ be 0.""",
    """          1. Repeat, while _index_ < _len_,""",
    """            1. Let _val_ be _argumentsList_[_index_].""",
    """            1. Perform ! CreateDataPropertyOrThrow(_obj_, ! ToString(𝔽(_index_)), _val_).""",
    """            1. Set _index_ to _index_ + 1.""",
    """          1. Perform ! DefinePropertyOrThrow(_obj_, *"length"*, PropertyDescriptor { [[Value]]: 𝔽(_len_), [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *true* }).""",
    """          1. Let _mappedNames_ be a new empty List.""",
    """          1. Let _index_ be _numberOfParameters_ - 1.""",
    """          1. Repeat, while _index_ ≥ 0,""",
    """            1. Let _name_ be _parameterNames_[_index_].""",
    """            1. If _name_ is not an element of _mappedNames_, then""",
    """              1. Add _name_ as an element of the list _mappedNames_.""",
    """              1. If _index_ < _len_, then""",
    """                1. Let _g_ be MakeArgGetter(_name_, _env_).""",
    """                1. Let _p_ be MakeArgSetter(_name_, _env_).""",
    """                1. Perform _map_.[[DefineOwnProperty]](! ToString(𝔽(_index_)), PropertyDescriptor { [[Set]]: _p_, [[Get]]: _g_, [[Enumerable]]: *false*, [[Configurable]]: *true* }).""",
    """            1. Set _index_ to _index_ - 1.""",
    """          1. Perform ! DefinePropertyOrThrow(_obj_, @@iterator, PropertyDescriptor { [[Value]]: %Array.prototype.values%, [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *true* }).""",
    """          1. Perform ! DefinePropertyOrThrow(_obj_, *"callee"*, PropertyDescriptor { [[Value]]: _func_, [[Writable]]: *true*, [[Enumerable]]: *false*, [[Configurable]]: *true* }).""",
    """          1. Return _obj_.""",
  )
}
