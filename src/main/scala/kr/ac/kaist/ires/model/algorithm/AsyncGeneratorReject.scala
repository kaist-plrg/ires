package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorReject` extends Algo {
  val head = NormalHead("AsyncGeneratorReject", List(Param("generator", Normal), Param("exception", Normal)))
  val ids = List(
    "sec-asyncgeneratorreject",
    "sec-asyncgenerator-abstract-operations",
    "sec-asyncgenerator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  1:let queue = generator.AsyncGeneratorQueue
  |  2:assert (< 0i queue.length)
  |  3:let next = queue[0i]
  |  4:let __x0__ = (pop queue 0i)
  |  5:let promiseCapability = next.Capability
  |  6:app __x1__ = (Call promiseCapability.Reject undefined (new [exception]))
  |  6:[! __x1__]
  |  7:app __x2__ = (AsyncGeneratorResumeNext generator)
  |  7:[! __x2__]
  |  8:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _generator_ is an AsyncGenerator instance.""",
    """          1. Let _queue_ be _generator_.[[AsyncGeneratorQueue]].""",
    """          1. Assert: _queue_ is not an empty List.""",
    """          1. Let _next_ be the first element of _queue_.""",
    """          1. Remove the first element from _queue_.""",
    """          1. Let _promiseCapability_ be _next_.[[Capability]].""",
    """          1. Perform ! Call(_promiseCapability_.[[Reject]], *undefined*, « _exception_ »).""",
    """          1. Perform ! AsyncGeneratorResumeNext(_generator_).""",
    """          1. Return *undefined*.""",
  )
}
