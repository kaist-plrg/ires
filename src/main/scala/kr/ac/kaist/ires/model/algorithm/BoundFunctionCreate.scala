package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BoundFunctionCreate` extends Algo {
  val head = NormalHead("BoundFunctionCreate", List(Param("targetFunction", Normal), Param("boundThis", Normal), Param("boundArgs", Normal)))
  val ids = List(
    "sec-boundfunctioncreate",
    "sec-bound-function-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof targetFunction) Object)
  |  1:app __x0__ = (targetFunction.GetPrototypeOf targetFunction)
  |  1:let proto = [? __x0__]
  |  2:let internalSlotsList = StrList
  |  3:app __x1__ = (MakeBasicObject internalSlotsList)
  |  3:let obj = [! __x1__]
  |  4:obj.Prototype = proto
  |  5:obj.Call = BoundFunctionExoticObjectDOTCall
  |  6:app __x2__ = (IsConstructor targetFunction)
  |  6:if (= __x2__ true) obj.Construct = BoundFunctionExoticObjectDOTConstruct else 0:{}
  |  8:obj.BoundTargetFunction = targetFunction
  |  9:obj.BoundThis = boundThis
  |  10:obj.BoundArguments = boundArgs
  |  11:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Type(_targetFunction_) is Object.""",
    """          1. Let _proto_ be ? _targetFunction_.[[GetPrototypeOf]]().""",
    """          1. Let _internalSlotsList_ be the internal slots listed in <emu-xref href="#table-internal-slots-of-bound-function-exotic-objects"></emu-xref>, plus [[Prototype]] and [[Extensible]].""",
    """          1. Let _obj_ be ! MakeBasicObject(_internalSlotsList_).""",
    """          1. Set _obj_.[[Prototype]] to _proto_.""",
    """          1. Set _obj_.[[Call]] as described in <emu-xref href="#sec-bound-function-exotic-objects-call-thisargument-argumentslist"></emu-xref>.""",
    """          1. If IsConstructor(_targetFunction_) is *true*, then""",
    """            1. Set _obj_.[[Construct]] as described in <emu-xref href="#sec-bound-function-exotic-objects-construct-argumentslist-newtarget"></emu-xref>.""",
    """          1. Set _obj_.[[BoundTargetFunction]] to _targetFunction_.""",
    """          1. Set _obj_.[[BoundThis]] to _boundThis_.""",
    """          1. Set _obj_.[[BoundArguments]] to _boundArgs_.""",
    """          1. Return _obj_.""",
  )
}
