package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RemoveWaiters` extends Algo {
  val head = NormalHead("RemoveWaiters", List(Param("WL", Normal), Param("c", Normal)))
  val ids = List(
    "sec-removewaiters",
    "sec-abstract-operations-for-atomics",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  1:let L = (new [])
  |  2:??? "Let id:{S} be a reference to the list of waiters in id:{WL} ."
  |  3:while (&& (< 0i c) (< 0i S.length)) {
  |    4:let W = S[0i]
  |    5:append W -> L
  |    6:let __x0__ = 0i
  |    6:while (< __x0__ S.length) if (= S[__x0__] W) (pop S __x0__) else __x0__ = (+ __x0__ 1i)
  |    7:if (! (|| (= c Infinity) (= c -Infinity))) c = (- c 1i) else 0:{}
  |  }
  |  8:return L
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: The calling agent is in the critical section for _WL_.""",
    """          1. Let _L_ be a new empty List.""",
    """          1. Let _S_ be a reference to the list of waiters in _WL_.""",
    """          1. Repeat, while _c_ > 0 and _S_ is not an empty List,""",
    """            1. Let _W_ be the first waiter in _S_.""",
    """            1. Add _W_ to the end of _L_.""",
    """            1. Remove _W_ from _S_.""",
    """            1. If _c_ is finite, set _c_ to _c_ - 1.""",
    """          1. Return _L_.""",
  )
}
