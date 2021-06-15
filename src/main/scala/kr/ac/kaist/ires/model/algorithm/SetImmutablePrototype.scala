package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SetImmutablePrototype` extends Algo {
  val head = NormalHead("SetImmutablePrototype", List(Param("O", Normal), Param("V", Normal)))
  val ids = List(
    "sec-set-immutable-prototype",
    "sec-immutable-prototype-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (O.GetPrototypeOf O)
  |  1:let current = [? __x0__]
  |  2:app __x1__ = (SameValue V current)
  |  2:if (= __x1__ true) return true else 0:{}
  |  3:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Either Type(_V_) is Object or Type(_V_) is Null.""",
    """          1. Let _current_ be ? _O_.[[GetPrototypeOf]]().""",
    """          1. If SameValue(_V_, _current_) is *true*, return *true*.""",
    """          1. Return *false*.""",
  )
}
