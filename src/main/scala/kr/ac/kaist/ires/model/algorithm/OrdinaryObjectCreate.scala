package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryObjectCreate` extends Algo {
  val head = NormalHead("OrdinaryObjectCreate", List(Param("proto", Normal), Param("additionalInternalSlotsList", Optional)))
  val ids = List(
    "sec-ordinaryobjectcreate",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let internalSlotsList = (new ["Prototype", "Extensible"])
  |  1:if (! (= additionalInternalSlotsList absent)) {
  |    let __x0__ = additionalInternalSlotsList
  |    let __x1__ = 0i
  |    while (< __x1__ __x0__.length) {
  |      let __x2__ = __x0__[__x1__]
  |      append __x2__ -> internalSlotsList
  |      __x1__ = (+ __x1__ 1i)
  |    }
  |  } else 17:{}
  |  2:app __x3__ = (MakeBasicObject internalSlotsList)
  |  2:let O = [! __x3__]
  |  3:O.Prototype = proto
  |  4:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _internalSlotsList_ be « [[Prototype]], [[Extensible]] ».""",
    """        1. If _additionalInternalSlotsList_ is present, append each of its elements to _internalSlotsList_.""",
    """        1. Let _O_ be ! MakeBasicObject(_internalSlotsList_).""",
    """        1. Set _O_.[[Prototype]] to _proto_.""",
    """        1. Return _O_.""",
  )
}
