package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Execution` extends Algo {
  val head = NormalHead("Execution", List())
  val ids = List(
    "sec-weakref-execution",
    "sec-weakref-processing-model",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = S
  |  0:let __x1__ = 0i
  |  0:while (< __x1__ __x0__.length) {
  |    let obj = __x0__[__x1__]
  |    1:??? "For each WeakRef id:{ref} such that id:{ref} . [ [ WeakRefTarget ] ] is id:{obj} , do in:{} out:{}"
  |    3:??? "For each FinalizationRegistry id:{fg} such that id:{fg} . [ [ Cells ] ] contains a Record id:{cell} such that id:{cell} . [ [ WeakRefTarget ] ] is id:{obj} , do in:{} out:{}"
  |    6:??? "For each WeakMap id:{map} such that id:{map} . [ [ WeakMapData ] ] contains a Record id:{r} such that id:{r} . [ [ Key ] ] is id:{obj} , do in:{} out:{}"
  |    9:??? "For each WeakSet id:{set} such that id:{set} . [ [ WeakSetData ] ] contains id:{obj} , do in:{} out:{}"
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. For each element _obj_ of _S_, do""",
    """          1. For each WeakRef _ref_ such that _ref_.[[WeakRefTarget]] is _obj_, do""",
    """            1. Set _ref_.[[WeakRefTarget]] to ~empty~.""",
    """          1. For each FinalizationRegistry _fg_ such that _fg_.[[Cells]] contains a Record _cell_ such that _cell_.[[WeakRefTarget]] is _obj_, do""",
    """            1. Set _cell_.[[WeakRefTarget]] to ~empty~.""",
    """            1. Optionally, perform ! HostEnqueueFinalizationRegistryCleanupJob(_fg_).""",
    """          1. For each WeakMap _map_ such that _map_.[[WeakMapData]] contains a Record _r_ such that _r_.[[Key]] is _obj_, do""",
    """            1. Set _r_.[[Key]] to ~empty~.""",
    """            1. Set _r_.[[Value]] to ~empty~.""",
    """          1. For each WeakSet _set_ such that _set_.[[WeakSetData]] contains _obj_, do""",
    """            1. Replace the element of _set_.[[WeakSetData]] whose value is _obj_ with an element whose value is ~empty~.""",
  )
}
