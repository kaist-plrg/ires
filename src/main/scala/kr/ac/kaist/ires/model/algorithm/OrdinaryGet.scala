package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryGet` extends Algo {
  val head = NormalHead("OrdinaryGet", List(Param("O", Normal), Param("P", Normal), Param("Receiver", Normal)))
  val ids = List(
    "sec-ordinaryget",
    "sec-ordinary-object-internal-methods-and-internal-slots-get-p-receiver",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:app __x1__ = (O.GetOwnProperty O P)
  |  1:let desc = [? __x1__]
  |  2:if (= desc undefined) {
  |    3:app __x2__ = (O.GetPrototypeOf O)
  |    3:let parent = [? __x2__]
  |    4:if (= parent null) return undefined else 15:{}
  |    5:app __x3__ = (parent.Get parent P Receiver)
  |    5:return [? __x3__]
  |  } else 15:{}
  |  6:app __x4__ = (IsDataDescriptor desc)
  |  6:if (= __x4__ true) return desc.Value else 15:{}
  |  7:app __x5__ = (IsAccessorDescriptor desc)
  |  7:assert (= __x5__ true)
  |  8:let getter = desc.Get
  |  9:if (= getter undefined) return undefined else 15:{}
  |  10:app __x6__ = (Call getter Receiver)
  |  10:return [? __x6__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. Let _desc_ be ? _O_.[[GetOwnProperty]](_P_).""",
    """          1. If _desc_ is *undefined*, then""",
    """            1. Let _parent_ be ? _O_.[[GetPrototypeOf]]().""",
    """            1. If _parent_ is *null*, return *undefined*.""",
    """            1. Return ? _parent_.[[Get]](_P_, _Receiver_).""",
    """          1. If IsDataDescriptor(_desc_) is *true*, return _desc_.[[Value]].""",
    """          1. Assert: IsAccessorDescriptor(_desc_) is *true*.""",
    """          1. Let _getter_ be _desc_.[[Get]].""",
    """          1. If _getter_ is *undefined*, return *undefined*.""",
    """          1. Return ? Call(_getter_, _Receiver_).""",
  )
}
