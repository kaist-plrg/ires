package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Promise.prototype.then` extends Algo {
  val head = BuiltinHead(parseRef("""Promise.prototype.then"""), List(Param("onFulfilled", Normal), Param("onRejected", Normal)))
  val ids = List(
    "sec-promise.prototype.then",
    "sec-properties-of-the-promise-prototype-object",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let promise = this
  |  1:app __x0__ = (IsPromise promise)
  |  1:if (= __x0__ false) throw TypeError else 10:{}
  |  2:app __x1__ = (SpeciesConstructor promise INTRINSIC_Promise)
  |  2:let C = [? __x1__]
  |  3:app __x2__ = (NewPromiseCapability C)
  |  3:let resultCapability = [? __x2__]
  |  4:app __x3__ = (PerformPromiseThen promise onFulfilled onRejected resultCapability)
  |  4:return __x3__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _promise_ be the *this* value.""",
    """          1. If IsPromise(_promise_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _C_ be ? SpeciesConstructor(_promise_, %Promise%).""",
    """          1. Let _resultCapability_ be ? NewPromiseCapability(_C_).""",
    """          1. Return PerformPromiseThen(_promise_, _onFulfilled_, _onRejected_, _resultCapability_).""",
  )
}
