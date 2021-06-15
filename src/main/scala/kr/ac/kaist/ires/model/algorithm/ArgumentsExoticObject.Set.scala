package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArgumentsExoticObject.Set` extends Algo {
  val head = MethodHead("ArgumentsExoticObject", "Set", Param("args", Normal), List(Param("P", Normal), Param("V", Normal), Param("Receiver", Normal)))
  val ids = List(
    "sec-arguments-exotic-objects-set-p-v-receiver",
    "sec-arguments-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  2:app __x0__ = (SameValue args Receiver)
  |  2:if (= __x0__ false) let isMapped = false else {
  |    3:let map = args.ParameterMap
  |    4:app __x1__ = (HasOwnProperty map P)
  |    4:let isMapped = [! __x1__]
  |  }
  |  5:if (= isMapped true) {
  |    6:app __x2__ = (Set map P V false)
  |    6:let setStatus = __x2__
  |  } else 7:{}
  |  8:app __x3__ = (OrdinarySet args P V Receiver)
  |  8:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If SameValue(_args_, _Receiver_) is *false*, then""",
    """            1. Let _isMapped_ be *false*.""",
    """          1. Else,""",
    """            1. Let _map_ be _args_.[[ParameterMap]].""",
    """            1. Let _isMapped_ be ! HasOwnProperty(_map_, _P_).""",
    """          1. If _isMapped_ is *true*, then""",
    """            1. Let _setStatus_ be Set(_map_, _P_, _V_, *false*).""",
    """            1. Assert: _setStatus_ is *true* because formal parameters mapped by argument objects are always writable.""",
    """          1. Return ? OrdinarySet(_args_, _P_, _V_, _Receiver_).""",
  )
}
