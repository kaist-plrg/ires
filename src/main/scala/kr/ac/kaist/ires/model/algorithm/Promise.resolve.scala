package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Promise.resolve` extends Algo {
  val head = BuiltinHead(parseRef("""Promise.resolve"""), List(Param("x", Normal)))
  val ids = List(
    "sec-promise.resolve",
    "sec-properties-of-the-promise-constructor",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let C = this
  |  1:if (! (= (typeof C) Object)) throw TypeError else 10:{}
  |  2:app __x0__ = (PromiseResolve C x)
  |  2:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _C_ be the *this* value.""",
    """          1. If Type(_C_) is not Object, throw a *TypeError* exception.""",
    """          1. Return ? PromiseResolve(_C_, _x_).""",
  )
}
