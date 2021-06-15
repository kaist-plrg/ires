package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionEnvironmentRecord.HasSuperBinding` extends Algo {
  val head = MethodHead("FunctionEnvironmentRecord", "HasSuperBinding", Param("envRec", Normal), List())
  val ids = List(
    "sec-function-environment-records-hassuperbinding",
    "sec-function-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:if (= envRec.ThisBindingStatus CONST_lexical) return false else 0:{}
  |  1:if (= envRec.FunctionObject.HomeObject undefined) return false else return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _envRec_.[[ThisBindingStatus]] is ~lexical~, return *false*.""",
    """            1. If _envRec_.[[FunctionObject]].[[HomeObject]] has the value *undefined*, return *false*; otherwise, return *true*.""",
  )
}
