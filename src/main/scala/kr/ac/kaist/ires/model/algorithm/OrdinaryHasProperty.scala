package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryHasProperty` extends Algo {
  val head = NormalHead("OrdinaryHasProperty", List(Param("O", Normal), Param("P", Normal)))
  val ids = List(
    "sec-ordinaryhasproperty",
    "sec-ordinary-object-internal-methods-and-internal-slots-hasproperty-p",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:app __x1__ = (O.GetOwnProperty O P)
  |  1:let hasOwn = [? __x1__]
  |  2:if (! (= hasOwn undefined)) return true else 15:{}
  |  3:app __x2__ = (O.GetPrototypeOf O)
  |  3:let parent = [? __x2__]
  |  4:if (! (= parent null)) {
  |    5:app __x3__ = (parent.HasProperty parent P)
  |    5:return [? __x3__]
  |  } else 15:{}
  |  6:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. Let _hasOwn_ be ? _O_.[[GetOwnProperty]](_P_).""",
    """          1. If _hasOwn_ is not *undefined*, return *true*.""",
    """          1. Let _parent_ be ? _O_.[[GetPrototypeOf]]().""",
    """          1. If _parent_ is not *null*, then""",
    """            1. Return ? _parent_.[[HasProperty]](_P_).""",
    """          1. Return *false*.""",
  )
}
