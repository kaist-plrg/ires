package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EventSet` extends Algo {
  val head = NormalHead("EventSet", List(Param("execution", Normal)))
  val ids = List(
    "sec-event-set",
    "sec-abstract-operations-for-the-memory-model",
    "sec-memory-model",
  )
  val rawBody = parseInst("""{
  |  0:let events = (new [])
  |  1:let __x0__ = execution.EventsRecords
  |  1:let __x1__ = 0i
  |  1:while (< __x1__ __x0__.length) {
  |    let aer = __x0__[__x1__]
  |    2:let __x2__ = aer.EventList
  |    2:let __x3__ = 0i
  |    2:while (< __x3__ __x2__.length) {
  |      let E = __x2__[__x3__]
  |      3:append E -> events
  |      __x3__ = (+ __x3__ 1i)
  |    }
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  4:return events
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _events_ be an empty Set.""",
    """        1. For each Agent Events Record _aer_ of _execution_.[[EventsRecords]], do""",
    """          1. For each event _E_ of _aer_.[[EventList]], do""",
    """            1. Add _E_ to _events_.""",
    """        1. Return _events_.""",
  )
}
