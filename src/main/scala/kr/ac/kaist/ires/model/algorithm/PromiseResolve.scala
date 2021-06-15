package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PromiseResolve` extends Algo {
  val head = NormalHead("PromiseResolve", List(Param("C", Normal), Param("x", Normal)))
  val ids = List(
    "sec-promise-resolve",
    "sec-promise.resolve",
    "sec-properties-of-the-promise-constructor",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof C) Object)
  |  1:app __x0__ = (IsPromise x)
  |  1:if (= __x0__ true) {
  |    2:app __x1__ = (Get x "constructor")
  |    2:let xConstructor = [? __x1__]
  |    3:app __x2__ = (SameValue xConstructor C)
  |    3:if (= __x2__ true) return x else 10:{}
  |  } else 10:{}
  |  4:app __x3__ = (NewPromiseCapability C)
  |  4:let promiseCapability = [? __x3__]
  |  5:app __x4__ = (Call promiseCapability.Resolve undefined (new [x]))
  |  5:[? __x4__]
  |  6:return promiseCapability.Promise
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: Type(_C_) is Object.""",
    """            1. If IsPromise(_x_) is *true*, then""",
    """              1. Let _xConstructor_ be ? Get(_x_, *"constructor"*).""",
    """              1. If SameValue(_xConstructor_, _C_) is *true*, return _x_.""",
    """            1. Let _promiseCapability_ be ? NewPromiseCapability(_C_).""",
    """            1. Perform ? Call(_promiseCapability_.[[Resolve]], *undefined*, « _x_ »).""",
    """            1. Return _promiseCapability_.[[Promise]].""",
  )
}
