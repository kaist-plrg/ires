package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MakeConstructor` extends Algo {
  val head = NormalHead("MakeConstructor", List(Param("F", Normal), Param("writablePrototype", Optional), Param("prototype", Optional)))
  val ids = List(
    "sec-makeconstructor",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:??? "If id:{F} is an ECMAScript function object , then in:{} out:{}"
  |  5:F.ConstructorKind = CONST_base
  |  6:if (= writablePrototype absent) writablePrototype = true else 3:{}
  |  7:if (= prototype absent) {
  |    8:app __x0__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |    8:prototype = [! __x0__]
  |    9:app __x1__ = (DefinePropertyOrThrow prototype "constructor" (new PropertyDescriptor("Value" -> F, "Writable" -> writablePrototype, "Enumerable" -> false, "Configurable" -> true)))
  |    9:[! __x1__]
  |  } else 3:{}
  |  10:app __x2__ = (DefinePropertyOrThrow F "prototype" (new PropertyDescriptor("Value" -> prototype, "Writable" -> writablePrototype, "Enumerable" -> false, "Configurable" -> false)))
  |  10:[! __x2__]
  |  11:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _F_ is an ECMAScript function object or a built-in function object.""",
    """        1. If _F_ is an ECMAScript function object, then""",
    """          1. Assert: IsConstructor(_F_) is *false*.""",
    """          1. Assert: _F_ is an extensible object that does not have a *"prototype"* own property.""",
    """          1. Set _F_.[[Construct]] to the definition specified in <emu-xref href="#sec-ecmascript-function-objects-construct-argumentslist-newtarget"></emu-xref>.""",
    """        1. Set _F_.[[ConstructorKind]] to ~base~.""",
    """        1. If _writablePrototype_ is not present, set _writablePrototype_ to *true*.""",
    """        1. If _prototype_ is not present, then""",
    """          1. Set _prototype_ to ! OrdinaryObjectCreate(%Object.prototype%).""",
    """          1. Perform ! DefinePropertyOrThrow(_prototype_, *"constructor"*, PropertyDescriptor { [[Value]]: _F_, [[Writable]]: _writablePrototype_, [[Enumerable]]: *false*, [[Configurable]]: *true* }).""",
    """        1. Perform ! DefinePropertyOrThrow(_F_, *"prototype"*, PropertyDescriptor { [[Value]]: _prototype_, [[Writable]]: _writablePrototype_, [[Enumerable]]: *false*, [[Configurable]]: *false* }).""",
    """        1. Return NormalCompletion(*undefined*).""",
  )
}
