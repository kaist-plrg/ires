package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object NewPromiseCapability {
  val length: Int = 1
  val func: Func = parseFunc(""""NewPromiseCapability" (C) => {
    app __x0__ = (IsConstructor C)
    if (= __x0__ false) {
      app __x1__ = (ThrowCompletion (new OrdinaryObject("Prototype" -> INTRINSIC_TypeErrorPrototype, "ErrorData" -> undefined, "SubMap" -> (new SubMap()))))
      return __x1__
    } else {}
    let promiseCapability = (new PromiseCapability("Promise" -> undefined, "Resolve" -> undefined, "Reject" -> undefined))
    let steps = (new algorithm("name" -> "", "length" -> 2i, "step" -> GLOBALDOTGetCapabilitiesExecutorFunctions))
    app __x2__ = (CreateBuiltinFunction steps (new ["Capability"]))
    let executor = __x2__
    executor["Capability"] = promiseCapability
    app __x3__ = (Construct C (new [executor]))
    if (is-completion __x3__) if (= __x3__["Type"] CONST_normal) __x3__ = __x3__["Value"] else return __x3__ else {}
    let promise = __x3__
    app __x4__ = (IsCallable promiseCapability["Resolve"])
    if (= __x4__ false) {
      app __x5__ = (ThrowCompletion (new OrdinaryObject("Prototype" -> INTRINSIC_TypeErrorPrototype, "ErrorData" -> undefined, "SubMap" -> (new SubMap()))))
      return __x5__
    } else {}
    app __x6__ = (IsCallable promiseCapability["Reject"])
    if (= __x6__ false) {
      app __x7__ = (ThrowCompletion (new OrdinaryObject("Prototype" -> INTRINSIC_TypeErrorPrototype, "ErrorData" -> undefined, "SubMap" -> (new SubMap()))))
      return __x7__
    } else {}
    promiseCapability["Promise"] = promise
    app __x8__ = (WrapCompletion promiseCapability)
    return __x8__
  }""")
}