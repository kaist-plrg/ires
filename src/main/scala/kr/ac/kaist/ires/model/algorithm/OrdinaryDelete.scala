package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryDelete` extends Algo {
  val head = NormalHead("OrdinaryDelete", List(Param("O", Normal), Param("P", Normal)))
  val ids = List(
    "sec-ordinarydelete",
    "sec-ordinary-object-internal-methods-and-internal-slots-delete-p",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:app __x1__ = (O.GetOwnProperty O P)
  |  1:let desc = [? __x1__]
  |  2:if (= desc undefined) return true else 17:{}
  |  3:if (= desc.Configurable true) {
  |    4:delete O[P]
  |    5:return true
  |  } else 17:{}
  |  6:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. Let _desc_ be ? _O_.[[GetOwnProperty]](_P_).""",
    """          1. If _desc_ is *undefined*, return *true*.""",
    """          1. If _desc_.[[Configurable]] is *true*, then""",
    """            1. Remove the own property with name _P_ from _O_.""",
    """            1. Return *true*.""",
    """          1. Return *false*.""",
  )
}
