package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DeletePropertyOrThrow` extends Algo {
  val head = NormalHead("DeletePropertyOrThrow", List(Param("O", Normal), Param("P", Normal)))
  val ids = List(
    "sec-deletepropertyorthrow",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:app __x0__ = (IsPropertyKey P)
  |  1:assert (= __x0__ true)
  |  2:app __x1__ = (O.Delete O P)
  |  2:let success = [? __x1__]
  |  3:if (= success false) throw TypeError else 4:{}
  |  4:return success
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_O_) is Object.""",
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. Let _success_ be ? _O_.[[Delete]](_P_).""",
    """        1. If _success_ is *false*, throw a *TypeError* exception.""",
    """        1. Return _success_.""",
  )
}
