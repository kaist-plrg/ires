package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateResolvingFunctions` extends Algo {
  val head = NormalHead("CreateResolvingFunctions", List(Param("promise", Normal)))
  val ids = List(
    "sec-createresolvingfunctions",
    "sec-promise-abstract-operations",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let alreadyResolved = (new Record("Value" -> false))
  |  1:let stepsResolve = PromiseResolveFunctions
  |  2:??? "Let id:{lengthResolve} be the number of non - optional parameters of the function definition in PromiseResolveFunctions ."
  |  3:app __x0__ = (CreateBuiltinFunction stepsResolve lengthResolve "" (new ["Promise", "AlreadyResolved"]))
  |  3:let resolve = [! __x0__]
  |  4:resolve.Promise = promise
  |  5:resolve.AlreadyResolved = alreadyResolved
  |  6:let stepsReject = PromiseRejectFunctions
  |  7:??? "Let id:{lengthReject} be the number of non - optional parameters of the function definition in PromiseRejectFunctions ."
  |  8:app __x1__ = (CreateBuiltinFunction stepsReject lengthReject "" (new ["Promise", "AlreadyResolved"]))
  |  8:let reject = [! __x1__]
  |  9:reject.Promise = promise
  |  10:reject.AlreadyResolved = alreadyResolved
  |  11:return (new Record("Resolve" -> resolve, "Reject" -> reject))
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _alreadyResolved_ be the Record { [[Value]]: *false* }.""",
    """          1. Let _stepsResolve_ be the algorithm steps defined in <emu-xref href="#sec-promise-resolve-functions" title></emu-xref>.""",
    """          1. Let _lengthResolve_ be the number of non-optional parameters of the function definition in <emu-xref href="#sec-promise-resolve-functions" title></emu-xref>.""",
    """          1. Let _resolve_ be ! CreateBuiltinFunction(_stepsResolve_, _lengthResolve_, *""*, « [[Promise]], [[AlreadyResolved]] »).""",
    """          1. Set _resolve_.[[Promise]] to _promise_.""",
    """          1. Set _resolve_.[[AlreadyResolved]] to _alreadyResolved_.""",
    """          1. Let _stepsReject_ be the algorithm steps defined in <emu-xref href="#sec-promise-reject-functions" title></emu-xref>.""",
    """          1. Let _lengthReject_ be the number of non-optional parameters of the function definition in <emu-xref href="#sec-promise-reject-functions" title></emu-xref>.""",
    """          1. Let _reject_ be ! CreateBuiltinFunction(_stepsReject_, _lengthReject_, *""*, « [[Promise]], [[AlreadyResolved]] »).""",
    """          1. Set _reject_.[[Promise]] to _promise_.""",
    """          1. Set _reject_.[[AlreadyResolved]] to _alreadyResolved_.""",
    """          1. Return the Record { [[Resolve]]: _resolve_, [[Reject]]: _reject_ }.""",
  )
}
