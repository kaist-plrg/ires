package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DeclarativeEnvironmentRecord.HasBinding` extends Algo {
  val head = MethodHead("DeclarativeEnvironmentRecord", "HasBinding", Param("envRec", Normal), List(Param("N", Normal)))
  val ids = List(
    "sec-declarative-environment-records-hasbinding-n",
    "sec-declarative-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:if (= envRec.SubMap[N] absent) {} else return true
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _envRec_ has a binding for the name that is the value of _N_, return *true*.""",
    """            1. Return *false*.""",
  )
}
