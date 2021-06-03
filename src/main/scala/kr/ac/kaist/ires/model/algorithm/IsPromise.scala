package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsPromise` extends Algo {
  val head = NormalHead("IsPromise", List(Param("x", Normal)))
  val ids = List(
    "sec-ispromise",
    "sec-promise-abstract-operations",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof x) Object)) return false else 1:{}
  |  1:if (= x.PromiseState absent) return false else 1:{}
  |  2:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_x_) is not Object, return *false*.""",
    """          1. If _x_ does not have a [[PromiseState]] internal slot, return *false*.""",
    """          1. Return *true*.""",
  )
}
