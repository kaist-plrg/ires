package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object GLOBALDOTINTRINSIC_MapIteratorPrototypeDOTnext {
  val length: Int = 0
  val func: Func = parseFunc(""""GLOBAL.INTRINSIC_MapIteratorPrototype.next" (this, argumentsList, NewTarget) => {
    let O = this
    app __x0__ = (Type O)
    if (! (= __x0__ Object)) {
      app __x1__ = (ThrowCompletion (new OrdinaryObject("Prototype" -> INTRINSIC_TypeErrorPrototype, "ErrorData" -> undefined, "SubMap" -> (new SubMap()))))
      return __x1__
    } else {}
    !!! "Etc"
    let m = O["Map"]
    let index = O["MapNextIndex"]
    let itemKind = O["MapIterationKind"]
    if (= m undefined) {
      app __x2__ = (CreateIterResultObject undefined true)
      app __x3__ = (WrapCompletion __x2__)
      return __x3__
    } else {}
    assert (! (= m["MapData"] absent))
    let entries = m["MapData"]
    let numEntries = entries["length"]
    while (< index numEntries) {
      !!! "Etc"
      index = (+ index 1i)
      O["MapNextIndex"] = index
      if (! (= e["Key"] CONST_empty)) {
        if (= itemKind "key") let result = e["Key"] else if (= itemKind "value") let result = e["Value"] else {
          assert (= itemKind "key+value")
          app __x4__ = (CreateArrayFromList (new [e["Key"], e["Value"]]))
          let result = __x4__
        }
        app __x5__ = (CreateIterResultObject result false)
        app __x6__ = (WrapCompletion __x5__)
        return __x6__
      } else {}
    }
    O["Map"] = undefined
    app __x7__ = (CreateIterResultObject undefined true)
    app __x8__ = (WrapCompletion __x7__)
    return __x8__
  }""")
}