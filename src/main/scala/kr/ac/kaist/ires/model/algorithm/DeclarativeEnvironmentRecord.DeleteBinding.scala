package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DeclarativeEnvironmentRecord.DeleteBinding` extends Algo {
  val head = MethodHead("DeclarativeEnvironmentRecord", "DeleteBinding", Param("envRec", Normal), List(Param("N", Normal)))
  val ids = List(
    "sec-declarative-environment-records-deletebinding-n",
    "sec-declarative-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  1:??? "If the binding for id:{N} in id:{envRec} cannot be deleted , return value:{false} ."
  |  2:delete envRec[N]
  |  3:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _envRec_ has a binding for the name that is the value of _N_.""",
    """            1. If the binding for _N_ in _envRec_ cannot be deleted, return *false*.""",
    """            1. Remove the binding for _N_ from _envRec_.""",
    """            1. Return *true*.""",
  )
}
