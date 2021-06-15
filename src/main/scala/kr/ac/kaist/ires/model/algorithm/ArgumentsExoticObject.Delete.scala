package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArgumentsExoticObject.Delete` extends Algo {
  val head = MethodHead("ArgumentsExoticObject", "Delete", Param("args", Normal), List(Param("P", Normal)))
  val ids = List(
    "sec-arguments-exotic-objects-delete-p",
    "sec-arguments-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let map = args.ParameterMap
  |  1:app __x0__ = (HasOwnProperty map P)
  |  1:let isMapped = [! __x0__]
  |  2:app __x1__ = (OrdinaryDelete args P)
  |  2:let result = [? __x1__]
  |  3:if (&& (= result true) (= isMapped true)) {
  |    4:app __x2__ = (map.Delete map P)
  |    4:__x2__
  |  } else 7:{}
  |  5:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _map_ be _args_.[[ParameterMap]].""",
    """          1. Let _isMapped_ be ! HasOwnProperty(_map_, _P_).""",
    """          1. Let _result_ be ? OrdinaryDelete(_args_, _P_).""",
    """          1. If _result_ is *true* and _isMapped_ is *true*, then""",
    """            1. Call _map_.[[Delete]](_P_).""",
    """          1. Return _result_.""",
  )
}
