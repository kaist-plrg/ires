package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetPromiseResolve` extends Algo {
  val head = NormalHead("GetPromiseResolve", List(Param("promiseConstructor", Normal)))
  val ids = List(
    "sec-getpromiseresolve",
    "sec-promise.all",
    "sec-properties-of-the-promise-constructor",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsConstructor promiseConstructor)
  |  0:assert (= __x0__ true)
  |  1:app __x1__ = (Get promiseConstructor "resolve")
  |  1:let promiseResolve = [? __x1__]
  |  2:app __x2__ = (IsCallable promiseResolve)
  |  2:if (= __x2__ false) throw TypeError else 10:{}
  |  3:return promiseResolve
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: IsConstructor(_promiseConstructor_) is *true*.""",
    """            1. Let _promiseResolve_ be ? Get(_promiseConstructor_, *"resolve"*).""",
    """            1. If IsCallable(_promiseResolve_) is *false*, throw a *TypeError* exception.""",
    """            1. Return _promiseResolve_.""",
  )
}
