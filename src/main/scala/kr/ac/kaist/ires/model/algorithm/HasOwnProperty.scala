package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HasOwnProperty` extends Algo {
  val head = NormalHead("HasOwnProperty", List(Param("O", Normal), Param("P", Normal)))
  val ids = List(
    "sec-hasownproperty",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:app __x0__ = (IsPropertyKey P)
  |  1:assert (= __x0__ true)
  |  2:app __x1__ = (O.GetOwnProperty O P)
  |  2:let desc = [? __x1__]
  |  3:if (= desc undefined) return false else 4:{}
  |  4:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_O_) is Object.""",
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. Let _desc_ be ? _O_.[[GetOwnProperty]](_P_).""",
    """        1. If _desc_ is *undefined*, return *false*.""",
    """        1. Return *true*.""",
  )
}
