package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorResolve` extends Algo {
  val head = NormalHead("AsyncGeneratorResolve", List(Param("generator", Normal), Param("value", Normal), Param("done", Normal)))
  val ids = List(
    "sec-asyncgeneratorresolve",
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
  |  6:app __x1__ = (CreateIterResultObject value done)
  |  6:let iteratorResult = [! __x1__]
  |  7:app __x2__ = (Call promiseCapability.Resolve undefined (new [iteratorResult]))
  |  7:[! __x2__]
  |  8:app __x3__ = (AsyncGeneratorResumeNext generator)
  |  8:[! __x3__]
  |  9:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _generator_ is an AsyncGenerator instance.""",
    """          1. Let _queue_ be _generator_.[[AsyncGeneratorQueue]].""",
    """          1. Assert: _queue_ is not an empty List.""",
    """          1. Let _next_ be the first element of _queue_.""",
    """          1. Remove the first element from _queue_.""",
    """          1. Let _promiseCapability_ be _next_.[[Capability]].""",
    """          1. Let _iteratorResult_ be ! CreateIterResultObject(_value_, _done_).""",
    """          1. Perform ! Call(_promiseCapability_.[[Resolve]], *undefined*, « _iteratorResult_ »).""",
    """          1. Perform ! AsyncGeneratorResumeNext(_generator_).""",
    """          1. Return *undefined*.""",
  )
}
