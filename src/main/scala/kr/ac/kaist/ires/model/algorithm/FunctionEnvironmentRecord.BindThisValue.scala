package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionEnvironmentRecord.BindThisValue` extends Algo {
  val head = MethodHead("FunctionEnvironmentRecord", "BindThisValue", Param("envRec", Normal), List(Param("V", Normal)))
  val ids = List(
    "sec-bindthisvalue",
    "sec-function-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:assert (! (= envRec.ThisBindingStatus CONST_lexical))
  |  1:if (= envRec.ThisBindingStatus CONST_initialized) throw ReferenceError else 0:{}
  |  2:envRec.ThisValue = V
  |  3:envRec.ThisBindingStatus = CONST_initialized
  |  4:return V
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _envRec_.[[ThisBindingStatus]] is not ~lexical~.""",
    """            1. If _envRec_.[[ThisBindingStatus]] is ~initialized~, throw a *ReferenceError* exception.""",
    """            1. Set _envRec_.[[ThisValue]] to _V_.""",
    """            1. Set _envRec_.[[ThisBindingStatus]] to ~initialized~.""",
    """            1. Return _V_.""",
  )
}
