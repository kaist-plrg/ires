package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ThenFinallyFunctions` extends Algo {
  val head = NormalHead("ThenFinallyFunctions", List())
  val ids = List(
    "sec-thenfinallyfunctions",
    "sec-promise.prototype.finally",
    "sec-properties-of-the-promise-prototype-object",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let F = CONTEXT.Function
  |  1:let onFinally = F.OnFinally
  |  2:app __x0__ = (IsCallable onFinally)
  |  2:assert (= __x0__ true)
  |  3:app __x1__ = (Call onFinally undefined)
  |  3:let result = [? __x1__]
  |  4:let C = F.Constructor
  |  5:app __x2__ = (IsConstructor C)
  |  5:assert (= __x2__ true)
  |  6:app __x3__ = (PromiseResolve C result)
  |  6:let promise = [? __x3__]
  |  7:??? "Let id:{valueThunk} be equivalent to a function that returns id:{value} ."
  |  8:app __x4__ = (Invoke promise "then" (new [valueThunk]))
  |  8:return [? __x4__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _F_ be the active function object.""",
    """            1. Let _onFinally_ be _F_.[[OnFinally]].""",
    """            1. Assert: IsCallable(_onFinally_) is *true*.""",
    """            1. Let _result_ be ? Call(_onFinally_, *undefined*).""",
    """            1. Let _C_ be _F_.[[Constructor]].""",
    """            1. Assert: IsConstructor(_C_) is *true*.""",
    """            1. Let _promise_ be ? PromiseResolve(_C_, _result_).""",
    """            1. Let _valueThunk_ be equivalent to a function that returns _value_.""",
    """            1. Return ? Invoke(_promise_, *"then"*, « _valueThunk_ »).""",
  )
}
