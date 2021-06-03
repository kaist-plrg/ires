package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StringExoticObject.GetOwnProperty` extends Algo {
  val head = MethodHead("StringExoticObject", "GetOwnProperty", Param("S", Normal), List(Param("P", Normal)))
  val ids = List(
    "sec-string-exotic-objects-getownproperty-p",
    "sec-string-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:app __x1__ = (OrdinaryGetOwnProperty S P)
  |  1:let desc = __x1__
  |  2:if (! (= desc undefined)) return desc else 16:{}
  |  3:app __x2__ = (StringGetOwnProperty S P)
  |  3:return [! __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. Let _desc_ be OrdinaryGetOwnProperty(_S_, _P_).""",
    """          1. If _desc_ is not *undefined*, return _desc_.""",
    """          1. Return ! StringGetOwnProperty(_S_, _P_).""",
  )
}
