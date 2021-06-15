package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorEnqueue` extends Algo {
  val head = NormalHead("AsyncGeneratorEnqueue", List(Param("generator", Normal), Param("completion", Normal), Param("generatorBrand", Normal)))
  val ids = List(
    "sec-asyncgeneratorenqueue",
    "sec-asyncgenerator-abstract-operations",
    "sec-asyncgenerator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (NewPromiseCapability INTRINSIC_Promise)
  |  0:let promiseCapability = [! __x0__]
  |  1:app __x1__ = (AsyncGeneratorValidate generator generatorBrand)
  |  1:let check = __x1__
  |  2:app __x2__ = (IsAbruptCompletion check)
  |  2:if __x2__ {
  |    3:let badGeneratorError = (new OrdinaryObject())
  |    4:app __x3__ = (Call promiseCapability.Reject undefined (new [badGeneratorError]))
  |    4:[! __x3__]
  |    5:return promiseCapability.Promise
  |  } else 40:{}
  |  6:let queue = generator.AsyncGeneratorQueue
  |  7:let request = (new AsyncGeneratorRequest("Completion" -> completion, "Capability" -> promiseCapability))
  |  8:append request -> queue
  |  9:let state = generator.AsyncGeneratorState
  |  10:if (! (= state CONST_executing)) {
  |    11:app __x4__ = (AsyncGeneratorResumeNext generator)
  |    11:[! __x4__]
  |  } else 40:{}
  |  12:return promiseCapability.Promise
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _promiseCapability_ be ! NewPromiseCapability(%Promise%).""",
    """          1. Let _check_ be AsyncGeneratorValidate(_generator_, _generatorBrand_).""",
    """          1. If _check_ is an abrupt completion, then""",
    """            1. Let _badGeneratorError_ be a newly created *TypeError* object.""",
    """            1. Perform ! Call(_promiseCapability_.[[Reject]], *undefined*, « _badGeneratorError_ »).""",
    """            1. Return _promiseCapability_.[[Promise]].""",
    """          1. Let _queue_ be _generator_.[[AsyncGeneratorQueue]].""",
    """          1. Let _request_ be AsyncGeneratorRequest { [[Completion]]: _completion_, [[Capability]]: _promiseCapability_ }.""",
    """          1. Append _request_ to the end of _queue_.""",
    """          1. Let _state_ be _generator_.[[AsyncGeneratorState]].""",
    """          1. If _state_ is not ~executing~, then""",
    """            1. Perform ! AsyncGeneratorResumeNext(_generator_).""",
    """          1. Return _promiseCapability_.[[Promise]].""",
  )
}
