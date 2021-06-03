package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DeclarativeEnvironmentRecord.CreateMutableBinding` extends Algo {
  val head = MethodHead("DeclarativeEnvironmentRecord", "CreateMutableBinding", Param("envRec", Normal), List(Param("N", Normal), Param("D", Normal)))
  val ids = List(
    "sec-declarative-environment-records-createmutablebinding-n-d",
    "sec-declarative-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  1:??? "Create a mutable binding in id:{envRec} for id:{N} and record that it is uninitialized . If id:{D} is value:{true} , record that the newly created binding may be deleted by a subsequent DeleteBinding call ."
  |  2:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _envRec_ does not already have a binding for _N_.""",
    """            1. Create a mutable binding in _envRec_ for _N_ and record that it is uninitialized. If _D_ is *true*, record that the newly created binding may be deleted by a subsequent DeleteBinding call.""",
    """            1. Return NormalCompletion(~empty~).""",
  )
}
