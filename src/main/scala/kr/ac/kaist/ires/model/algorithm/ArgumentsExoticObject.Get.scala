package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArgumentsExoticObject.Get` extends Algo {
  val head = MethodHead("ArgumentsExoticObject", "Get", Param("args", Normal), List(Param("P", Normal), Param("Receiver", Normal)))
  val ids = List(
    "sec-arguments-exotic-objects-get-p-receiver",
    "sec-arguments-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let map = args.ParameterMap
  |  1:app __x0__ = (HasOwnProperty map P)
  |  1:let isMapped = [! __x0__]
  |  4:if (= isMapped false) {
  |    3:app __x1__ = (OrdinaryGet args P Receiver)
  |    3:return [? __x1__]
  |  } else {
  |    6:app __x2__ = (Get map P)
  |    6:return __x2__
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _map_ be _args_.[[ParameterMap]].""",
    """          1. Let _isMapped_ be ! HasOwnProperty(_map_, _P_).""",
    """          1. If _isMapped_ is *false*, then""",
    """            1. Return ? OrdinaryGet(_args_, _P_, _Receiver_).""",
    """          1. Else,""",
    """            1. Assert: _map_ contains a formal parameter mapping for _P_.""",
    """            1. Return Get(_map_, _P_).""",
  )
}
