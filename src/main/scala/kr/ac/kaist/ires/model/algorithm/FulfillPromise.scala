package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FulfillPromise` extends Algo {
  val head = NormalHead("FulfillPromise", List(Param("promise", Normal), Param("value", Normal)))
  val ids = List(
    "sec-fulfillpromise",
    "sec-promise-abstract-operations",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:assert (= promise.PromiseState CONST_pending)
  |  1:let reactions = promise.PromiseFulfillReactions
  |  2:promise.PromiseResult = value
  |  3:promise.PromiseFulfillReactions = undefined
  |  4:promise.PromiseRejectReactions = undefined
  |  5:promise.PromiseState = CONST_fulfilled
  |  6:app __x0__ = (TriggerPromiseReactions reactions value)
  |  6:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: The value of _promise_.[[PromiseState]] is ~pending~.""",
    """          1. Let _reactions_ be _promise_.[[PromiseFulfillReactions]].""",
    """          1. Set _promise_.[[PromiseResult]] to _value_.""",
    """          1. Set _promise_.[[PromiseFulfillReactions]] to *undefined*.""",
    """          1. Set _promise_.[[PromiseRejectReactions]] to *undefined*.""",
    """          1. Set _promise_.[[PromiseState]] to ~fulfilled~.""",
    """          1. Return TriggerPromiseReactions(_reactions_, _value_).""",
  )
}
