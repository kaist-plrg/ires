package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionEnvironmentRecord.GetThisBinding` extends Algo {
  val head = MethodHead("FunctionEnvironmentRecord", "GetThisBinding", Param("envRec", Normal), List())
  val ids = List(
    "sec-function-environment-records-getthisbinding",
    "sec-function-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:assert (! (= envRec.ThisBindingStatus CONST_lexical))
  |  1:if (= envRec.ThisBindingStatus CONST_uninitialized) throw ReferenceError else 0:{}
  |  2:return envRec.ThisValue
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _envRec_.[[ThisBindingStatus]] is not ~lexical~.""",
    """            1. If _envRec_.[[ThisBindingStatus]] is ~uninitialized~, throw a *ReferenceError* exception.""",
    """            1. Return _envRec_.[[ThisValue]].""",
  )
}
