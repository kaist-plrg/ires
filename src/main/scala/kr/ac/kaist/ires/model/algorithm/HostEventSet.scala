package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HostEventSet` extends Algo {
  val head = NormalHead("HostEventSet", List(Param("execution", Normal)))
  val ids = List(
    "sec-hosteventset",
    "sec-abstract-operations-for-the-memory-model",
    "sec-memory-model",
  )
  val rawBody = parseInst("""{
  |  0:let events = (new [])
  |  1:app __x0__ = (EventSet execution)
  |  1:let __x1__ = __x0__
  |  1:let __x2__ = 0i
  |  1:while (< __x2__ __x1__.length) {
  |    let E = __x1__[__x2__]
  |    2:app __x3__ = (SharedDataBlockEventSet execution)
  |    2:if (! (contains __x3__ E)) append E -> events else 4:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  3:return events
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _events_ be an empty Set.""",
    """        1. For each event _E_ of EventSet(_execution_), do""",
    """          1. If _E_ is not in SharedDataBlockEventSet(_execution_), add _E_ to _events_.""",
    """        1. Return _events_.""",
  )
}
