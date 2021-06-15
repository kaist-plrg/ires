package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetV` extends Algo {
  val head = NormalHead("GetV", List(Param("V", Normal), Param("P", Normal)))
  val ids = List(
    "sec-getv",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:app __x1__ = (ToObject V)
  |  1:let O = [? __x1__]
  |  2:app __x2__ = (O.Get O P V)
  |  2:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. Let _O_ be ? ToObject(_V_).""",
    """        1. Return ? _O_.[[Get]](_P_, _V_).""",
  )
}
