package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinarySet` extends Algo {
  val head = NormalHead("OrdinarySet", List(Param("O", Normal), Param("P", Normal), Param("V", Normal), Param("Receiver", Normal)))
  val ids = List(
    "sec-ordinaryset",
    "sec-ordinary-object-internal-methods-and-internal-slots-set-p-v-receiver",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:app __x1__ = (O.GetOwnProperty O P)
  |  1:let ownDesc = [? __x1__]
  |  2:app __x2__ = (OrdinarySetWithOwnDescriptor O P V Receiver ownDesc)
  |  2:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. Let _ownDesc_ be ? _O_.[[GetOwnProperty]](_P_).""",
    """          1. Return OrdinarySetWithOwnDescriptor(_O_, _P_, _V_, _Receiver_, _ownDesc_).""",
  )
}
