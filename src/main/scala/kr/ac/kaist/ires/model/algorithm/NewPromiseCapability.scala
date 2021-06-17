package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NewPromiseCapability` extends Algo {
  val head = NormalHead("NewPromiseCapability", List(Param("C", Normal)))
  val ids = List(
    "sec-newpromisecapability",
    "sec-promise-abstract-operations",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsConstructor C)
  |  0:if (= __x0__ false) throw TypeError else 1:{}
  |  2:let promiseCapability = (new PromiseCapabilityRecord("Promise" -> undefined, "Resolve" -> undefined, "Reject" -> undefined))
  |  3:let steps = GLOBALDOTGetCapabilitiesExecutorFunctions
  |  4:??? "Let id:{length} be the number of non - optional parameters of the function definition in GLOBALDOTGetCapabilitiesExecutorFunctions ."
  |  5:app __x1__ = (CreateBuiltinFunction steps length "" (new ["Capability"]))
  |  5:let executor = [! __x1__]
  |  6:executor.Capability = promiseCapability
  |  7:app __x2__ = (Construct C (new [executor]))
  |  7:let promise = [? __x2__]
  |  8:app __x3__ = (IsCallable promiseCapability.Resolve)
  |  8:if (= __x3__ false) throw TypeError else 1:{}
  |  9:app __x4__ = (IsCallable promiseCapability.Reject)
  |  9:if (= __x4__ false) throw TypeError else 1:{}
  |  10:promiseCapability.Promise = promise
  |  11:return promiseCapability
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If IsConstructor(_C_) is *false*, throw a *TypeError* exception.""",
    """          1. NOTE: _C_ is assumed to be a constructor function that supports the parameter conventions of the Promise constructor (see <emu-xref href="#sec-promise-executor"></emu-xref>).""",
    """          1. Let _promiseCapability_ be the PromiseCapability Record { [[Promise]]: *undefined*, [[Resolve]]: *undefined*, [[Reject]]: *undefined* }.""",
    """          1. Let _steps_ be the algorithm steps defined in <emu-xref href="#sec-getcapabilitiesexecutor-functions" title></emu-xref>.""",
    """          1. Let _length_ be the number of non-optional parameters of the function definition in <emu-xref href="#sec-getcapabilitiesexecutor-functions" title></emu-xref>.""",
    """          1. Let _executor_ be ! CreateBuiltinFunction(_steps_, _length_, *""*, « [[Capability]] »).""",
    """          1. Set _executor_.[[Capability]] to _promiseCapability_.""",
    """          1. Let _promise_ be ? Construct(_C_, « _executor_ »).""",
    """          1. If IsCallable(_promiseCapability_.[[Resolve]]) is *false*, throw a *TypeError* exception.""",
    """          1. If IsCallable(_promiseCapability_.[[Reject]]) is *false*, throw a *TypeError* exception.""",
    """          1. Set _promiseCapability_.[[Promise]] to _promise_.""",
    """          1. Return _promiseCapability_.""",
  )
}
