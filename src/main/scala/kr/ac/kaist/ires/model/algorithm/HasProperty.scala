package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HasProperty` extends Algo {
  val head = NormalHead("HasProperty", List(Param("O", Normal), Param("P", Normal)))
  val ids = List(
    "sec-hasproperty",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:app __x0__ = (IsPropertyKey P)
  |  1:assert (= __x0__ true)
  |  2:app __x1__ = (O.HasProperty O P)
  |  2:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_O_) is Object.""",
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. Return ? _O_.[[HasProperty]](_P_).""",
  )
}
