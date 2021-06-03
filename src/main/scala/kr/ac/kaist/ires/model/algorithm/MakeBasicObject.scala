package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MakeBasicObject` extends Algo {
  val head = NormalHead("MakeBasicObject", List(Param("internalSlotsList", Normal)))
  val ids = List(
    "sec-makebasicobject",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  1:let obj = (new OrdinaryObject("SubMap" -> (new SubMap())))
  |  1:let __x0__ = internalSlotsList
  |  1:let __x1__ = 0i
  |  1:while (< __x1__ __x0__.length) {
  |    let __x2__ = __x0__[__x1__]
  |    obj[__x2__] = undefined
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  5:if (contains internalSlotsList "Extensible") obj.Extensible = true else 4:{}
  |  6:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _internalSlotsList_ is a List of internal slot names.""",
    """        1. Let _obj_ be a newly created object with an internal slot for each name in _internalSlotsList_.""",
    """        1. Set _obj_'s essential internal methods to the default ordinary object definitions specified in <emu-xref href="#sec-ordinary-object-internal-methods-and-internal-slots"></emu-xref>.""",
    """        1. Assert: If the caller will not be overriding both _obj_'s [[GetPrototypeOf]] and [[SetPrototypeOf]] essential internal methods, then _internalSlotsList_ contains [[Prototype]].""",
    """        1. Assert: If the caller will not be overriding all of _obj_'s [[SetPrototypeOf]], [[IsExtensible]], and [[PreventExtensions]] essential internal methods, then _internalSlotsList_ contains [[Extensible]].""",
    """        1. If _internalSlotsList_ contains [[Extensible]], set _obj_.[[Extensible]] to *true*.""",
    """        1. Return _obj_.""",
  )
}
