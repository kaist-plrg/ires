package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Promise.prototype.finally` extends Algo {
  val head = BuiltinHead(parseRef("""Promise.prototype.finally"""), List(Param("onFinally", Normal)))
  val ids = List(
    "sec-promise.prototype.finally",
    "sec-properties-of-the-promise-prototype-object",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let promise = this
  |  1:if (! (= (typeof promise) Object)) throw TypeError else 10:{}
  |  2:app __x0__ = (SpeciesConstructor promise INTRINSIC_Promise)
  |  2:let C = [? __x0__]
  |  3:app __x1__ = (IsConstructor C)
  |  3:assert (= __x1__ true)
  |  7:app __x2__ = (IsCallable onFinally)
  |  7:if (= __x2__ false) {
  |    5:let thenFinally = onFinally
  |    6:let catchFinally = onFinally
  |  } else {
  |    8:let stepsThenFinally = ThenFinallyFunctions
  |    9:??? "Let id:{lengthThenFinally} be the number of non - optional parameters of the function definition in ThenFinallyFunctions ."
  |    10:app __x3__ = (CreateBuiltinFunction stepsThenFinally lengthThenFinally "" (new ["Constructor", "OnFinally"]))
  |    10:let thenFinally = [! __x3__]
  |    11:thenFinally.Constructor = C
  |    12:thenFinally.OnFinally = onFinally
  |    13:let stepsCatchFinally = CatchFinallyFunctions
  |    14:??? "Let id:{lengthCatchFinally} be the number of non - optional parameters of the function definition in CatchFinallyFunctions ."
  |    15:app __x4__ = (CreateBuiltinFunction stepsCatchFinally lengthCatchFinally "" (new ["Constructor", "OnFinally"]))
  |    15:let catchFinally = [! __x4__]
  |    16:catchFinally.Constructor = C
  |    17:catchFinally.OnFinally = onFinally
  |  }
  |  18:app __x5__ = (Invoke promise "then" (new [thenFinally, catchFinally]))
  |  18:return [? __x5__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _promise_ be the *this* value.""",
    """          1. If Type(_promise_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _C_ be ? SpeciesConstructor(_promise_, %Promise%).""",
    """          1. Assert: IsConstructor(_C_) is *true*.""",
    """          1. If IsCallable(_onFinally_) is *false*, then""",
    """            1. Let _thenFinally_ be _onFinally_.""",
    """            1. Let _catchFinally_ be _onFinally_.""",
    """          1. Else,""",
    """            1. Let _stepsThenFinally_ be the algorithm steps defined in <emu-xref href="#sec-thenfinallyfunctions" title></emu-xref>.""",
    """            1. Let _lengthThenFinally_ be the number of non-optional parameters of the function definition in <emu-xref href="#sec-thenfinallyfunctions" title></emu-xref>.""",
    """            1. Let _thenFinally_ be ! CreateBuiltinFunction(_stepsThenFinally_, _lengthThenFinally_, *""*, « [[Constructor]], [[OnFinally]] »).""",
    """            1. Set _thenFinally_.[[Constructor]] to _C_.""",
    """            1. Set _thenFinally_.[[OnFinally]] to _onFinally_.""",
    """            1. Let _stepsCatchFinally_ be the algorithm steps defined in <emu-xref href="#sec-catchfinallyfunctions" title></emu-xref>.""",
    """            1. Let _lengthCatchFinally_ be the number of non-optional parameters of the function definition in <emu-xref href="#sec-catchfinallyfunctions" title></emu-xref>.""",
    """            1. Let _catchFinally_ be ! CreateBuiltinFunction(_stepsCatchFinally_, _lengthCatchFinally_, *""*, « [[Constructor]], [[OnFinally]] »).""",
    """            1. Set _catchFinally_.[[Constructor]] to _C_.""",
    """            1. Set _catchFinally_.[[OnFinally]] to _onFinally_.""",
    """          1. Return ? Invoke(_promise_, *"then"*, « _thenFinally_, _catchFinally_ »).""",
  )
}
