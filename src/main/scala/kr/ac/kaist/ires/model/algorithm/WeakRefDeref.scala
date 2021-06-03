package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::WeakRefDeref` extends Algo {
  val head = NormalHead("WeakRefDeref", List(Param("weakRef", Normal)))
  val ids = List(
    "sec-weakrefderef",
    "sec-weakref-abstract-operations",
    "sec-weak-ref-objects",
    "sec-managing-memory",
  )
  val rawBody = parseInst("""{
  |  0:let target = weakRef.WeakRefTarget
  |  1:if (! (= target CONST_empty)) {
  |    2:app __x0__ = (AddToKeptObjects target)
  |    2:[! __x0__]
  |    3:return target
  |  } else 7:{}
  |  4:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _target_ be _weakRef_.[[WeakRefTarget]].""",
    """          1. If _target_ is not ~empty~, then""",
    """            1. Perform ! AddToKeptObjects(_target_).""",
    """            1. Return _target_.""",
    """          1. Return *undefined*.""",
  )
}
