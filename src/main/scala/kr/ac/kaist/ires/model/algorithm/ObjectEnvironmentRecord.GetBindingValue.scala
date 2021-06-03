package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectEnvironmentRecord.GetBindingValue` extends Algo {
  val head = MethodHead("ObjectEnvironmentRecord", "GetBindingValue", Param("envRec", Normal), List(Param("N", Normal), Param("S", Normal)))
  val ids = List(
    "sec-object-environment-records-getbindingvalue-n-s",
    "sec-object-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let bindings = envRec.BindingObject
  |  1:app __x0__ = (HasProperty bindings N)
  |  1:let value = [? __x0__]
  |  2:if (= value false) if (= S false) return undefined else throw ReferenceError else 0:{}
  |  4:app __x1__ = (Get bindings N)
  |  4:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _bindings_ be the binding object for _envRec_.""",
    """            1. Let _value_ be ? HasProperty(_bindings_, _N_).""",
    """            1. If _value_ is *false*, then""",
    """              1. If _S_ is *false*, return the value *undefined*; otherwise throw a *ReferenceError* exception.""",
    """            1. Return ? Get(_bindings_, _N_).""",
  )
}
