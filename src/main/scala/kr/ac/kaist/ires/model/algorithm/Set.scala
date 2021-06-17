package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Set` extends Algo {
  val head = NormalHead("Set", List(Param("O", Normal), Param("P", Normal), Param("V", Normal), Param("Throw", Normal)))
  val ids = List(
    "sec-set-o-p-v-throw",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:app __x0__ = (IsPropertyKey P)
  |  1:assert (= __x0__ true)
  |  2:assert (= (typeof Throw) Boolean)
  |  3:app __x1__ = (O.Set O P V O)
  |  3:let success = [? __x1__]
  |  4:if (&& (= success false) (= Throw true)) throw TypeError else 4:{}
  |  5:return success
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_O_) is Object.""",
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. Assert: Type(_Throw_) is Boolean.""",
    """        1. Let _success_ be ? _O_.[[Set]](_P_, _V_, _O_).""",
    """        1. If _success_ is *false* and _Throw_ is *true*, throw a *TypeError* exception.""",
    """        1. Return _success_.""",
  )
}
