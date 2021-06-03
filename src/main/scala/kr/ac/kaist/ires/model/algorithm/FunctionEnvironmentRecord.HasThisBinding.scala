package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionEnvironmentRecord.HasThisBinding` extends Algo {
  val head = MethodHead("FunctionEnvironmentRecord", "HasThisBinding", Param("envRec", Normal), List())
  val ids = List(
    "sec-function-environment-records-hasthisbinding",
    "sec-function-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""if (= envRec.ThisBindingStatus CONST_lexical) return false else return true""".stripMargin)
  val code = scala.Array[String](
    """            1. If _envRec_.[[ThisBindingStatus]] is ~lexical~, return *false*; otherwise, return *true*.""",
  )
}
