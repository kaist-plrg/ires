package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinarySetPrototypeOf` extends Algo {
  val head = NormalHead("OrdinarySetPrototypeOf", List(Param("O", Normal), Param("V", Normal)))
  val ids = List(
    "sec-ordinarysetprototypeof",
    "sec-ordinary-object-internal-methods-and-internal-slots-setprototypeof-v",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:let current = O.Prototype
  |  2:app __x0__ = (SameValue V current)
  |  2:if (= __x0__ true) return true else 0:{}
  |  3:let extensible = O.Extensible
  |  4:if (= extensible false) return false else 0:{}
  |  5:let p = V
  |  6:let done = false
  |  7:while (= done false) if (= p null) done = true else {
  |    app __x1__ = (SameValue p O)
  |    if (= __x1__ true) return false else if (! (= p.GetPrototypeOf OrdinaryObjectDOTGetPrototypeOf)) done = true else p = p.Prototype
  |  }
  |  13:O.Prototype = V
  |  14:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Either Type(_V_) is Object or Type(_V_) is Null.""",
    """          1. Let _current_ be _O_.[[Prototype]].""",
    """          1. If SameValue(_V_, _current_) is *true*, return *true*.""",
    """          1. Let _extensible_ be _O_.[[Extensible]].""",
    """          1. If _extensible_ is *false*, return *false*.""",
    """          1. Let _p_ be _V_.""",
    """          1. Let _done_ be *false*.""",
    """          1. [id="step-ordinarysetprototypeof-loop"] Repeat, while _done_ is *false*,""",
    """            1. If _p_ is *null*, set _done_ to *true*.""",
    """            1. Else if SameValue(_p_, _O_) is *true*, return *false*.""",
    """            1. Else,""",
    """              1. If _p_.[[GetPrototypeOf]] is not the ordinary object internal method defined in <emu-xref href="#sec-ordinary-object-internal-methods-and-internal-slots-getprototypeof"></emu-xref>, set _done_ to *true*.""",
    """              1. Else, set _p_ to _p_.[[Prototype]].""",
    """          1. Set _O_.[[Prototype]] to _V_.""",
    """          1. Return *true*.""",
  )
}
