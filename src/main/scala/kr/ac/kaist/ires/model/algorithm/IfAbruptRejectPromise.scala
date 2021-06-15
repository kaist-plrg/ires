package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IfAbruptRejectPromise` extends Algo {
  val head = BuiltinHead(parseRef("""IfAbruptRejectPromise"""), List(Param("value", Normal), Param("capability", Normal)))
  val ids = List(
    "sec-ifabruptrejectpromise",
    "sec-promisecapability-records",
    "sec-promise-abstract-operations",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  3:app __x0__ = (IsAbruptCompletion value)
  |  3:if __x0__ {
  |    1:app __x1__ = (Call capability.Reject undefined (new [value.Value]))
  |    1:[? __x1__]
  |    2:return capability.Promise
  |  } else if (is-completion value) value = value.Value else 7:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _value_ is an abrupt completion, then""",
    """              1. Perform ? Call(_capability_.[[Reject]], *undefined*, « _value_.[[Value]] »).""",
    """              1. Return _capability_.[[Promise]].""",
    """            1. Else if _value_ is a Completion Record, set _value_ to _value_.[[Value]].""",
  )
}
