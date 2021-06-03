package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryHasInstance` extends Algo {
  val head = NormalHead("OrdinaryHasInstance", List(Param("C", Normal), Param("O", Normal)))
  val ids = List(
    "sec-ordinaryhasinstance",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsCallable C)
  |  0:if (= __x0__ false) return false else 0:{}
  |  1:if (! (= C.BoundTargetFunction absent)) {
  |    2:let BC = C.BoundTargetFunction
  |    3:app __x1__ = (InstanceofOperator O BC)
  |    3:return [? __x1__]
  |  } else 0:{}
  |  4:if (! (= (typeof O) Object)) return false else 0:{}
  |  5:app __x2__ = (Get C "prototype")
  |  5:let P = [? __x2__]
  |  6:if (! (= (typeof P) Object)) throw TypeError else 0:{}
  |  7:while true {
  |    8:app __x3__ = (O.GetPrototypeOf O)
  |    8:O = [? __x3__]
  |    9:if (= O null) return false else 0:{}
  |    10:app __x4__ = (SameValue P O)
  |    10:if (= __x4__ true) return true else 0:{}
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If IsCallable(_C_) is *false*, return *false*.""",
    """        1. If _C_ has a [[BoundTargetFunction]] internal slot, then""",
    """          1. Let _BC_ be _C_.[[BoundTargetFunction]].""",
    """          1. Return ? InstanceofOperator(_O_, _BC_).""",
    """        1. If Type(_O_) is not Object, return *false*.""",
    """        1. Let _P_ be ? Get(_C_, *"prototype"*).""",
    """        1. If Type(_P_) is not Object, throw a *TypeError* exception.""",
    """        1. Repeat,""",
    """          1. Set _O_ to ? _O_.[[GetPrototypeOf]]().""",
    """          1. If _O_ is *null*, return *false*.""",
    """          1. If SameValue(_P_, _O_) is *true*, return *true*.""",
  )
}
