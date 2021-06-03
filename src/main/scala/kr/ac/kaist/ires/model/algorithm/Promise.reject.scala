package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Promise.reject` extends Algo {
  val head = BuiltinHead(parseRef("""Promise.reject"""), List(Param("r", Normal)))
  val ids = List(
    "sec-promise.reject",
    "sec-properties-of-the-promise-constructor",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let C = this
  |  1:app __x0__ = (NewPromiseCapability C)
  |  1:let promiseCapability = [? __x0__]
  |  2:app __x1__ = (Call promiseCapability.Reject undefined (new [r]))
  |  2:[? __x1__]
  |  3:return promiseCapability.Promise
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _C_ be the *this* value.""",
    """          1. Let _promiseCapability_ be ? NewPromiseCapability(_C_).""",
    """          1. Perform ? Call(_promiseCapability_.[[Reject]], *undefined*, « _r_ »).""",
    """          1. Return _promiseCapability_.[[Promise]].""",
  )
}
