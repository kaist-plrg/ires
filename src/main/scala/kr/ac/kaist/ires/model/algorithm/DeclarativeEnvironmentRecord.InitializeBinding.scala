package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DeclarativeEnvironmentRecord.InitializeBinding` extends Algo {
  val head = MethodHead("DeclarativeEnvironmentRecord", "InitializeBinding", Param("envRec", Normal), List(Param("N", Normal), Param("V", Normal)))
  val ids = List(
    "sec-declarative-environment-records-initializebinding-n-v",
    "sec-declarative-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  1:envRec.SubMap[N].BoundValue = V
  |  3:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _envRec_ must have an uninitialized binding for _N_.""",
    """            1. Set the bound value for _N_ in _envRec_ to _V_.""",
    """            1. <emu-not-ref>Record</emu-not-ref> that the binding for _N_ in _envRec_ has been initialized.""",
    """            1. Return NormalCompletion(~empty~).""",
  )
}
