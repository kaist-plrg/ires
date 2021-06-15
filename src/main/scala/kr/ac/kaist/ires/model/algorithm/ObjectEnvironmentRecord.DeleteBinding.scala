package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectEnvironmentRecord.DeleteBinding` extends Algo {
  val head = MethodHead("ObjectEnvironmentRecord", "DeleteBinding", Param("envRec", Normal), List(Param("N", Normal)))
  val ids = List(
    "sec-object-environment-records-deletebinding-n",
    "sec-object-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let bindings = envRec.BindingObject
  |  1:app __x0__ = (bindings.Delete bindings N)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _bindings_ be the binding object for _envRec_.""",
    """            1. Return ? _bindings_.[[Delete]](_N_).""",
  )
}
