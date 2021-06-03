package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetMethod` extends Algo {
  val head = NormalHead("GetMethod", List(Param("V", Normal), Param("P", Normal)))
  val ids = List(
    "sec-getmethod",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:app __x1__ = (GetV V P)
  |  1:let func = [? __x1__]
  |  2:if (|| (= func undefined) (= func null)) return undefined else 4:{}
  |  3:app __x2__ = (IsCallable func)
  |  3:if (= __x2__ false) throw TypeError else 4:{}
  |  4:return func
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. Let _func_ be ? GetV(_V_, _P_).""",
    """        1. If _func_ is either *undefined* or *null*, return *undefined*.""",
    """        1. If IsCallable(_func_) is *false*, throw a *TypeError* exception.""",
    """        1. Return _func_.""",
  )
}
