package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArgumentsExoticObject.GetOwnProperty` extends Algo {
  val head = MethodHead("ArgumentsExoticObject", "GetOwnProperty", Param("args", Normal), List(Param("P", Normal)))
  val ids = List(
    "sec-arguments-exotic-objects-getownproperty-p",
    "sec-arguments-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinaryGetOwnProperty args P)
  |  0:let desc = __x0__
  |  1:if (= desc undefined) return desc else 0:{}
  |  2:let map = args.ParameterMap
  |  3:app __x1__ = (HasOwnProperty map P)
  |  3:let isMapped = [! __x1__]
  |  4:if (= isMapped true) {
  |    5:app __x2__ = (Get map P)
  |    5:desc.Value = __x2__
  |  } else 0:{}
  |  6:return desc
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _desc_ be OrdinaryGetOwnProperty(_args_, _P_).""",
    """          1. If _desc_ is *undefined*, return _desc_.""",
    """          1. Let _map_ be _args_.[[ParameterMap]].""",
    """          1. Let _isMapped_ be ! HasOwnProperty(_map_, _P_).""",
    """          1. If _isMapped_ is *true*, then""",
    """            1. Set _desc_.[[Value]] to Get(_map_, _P_).""",
    """          1. Return _desc_.""",
  )
}
