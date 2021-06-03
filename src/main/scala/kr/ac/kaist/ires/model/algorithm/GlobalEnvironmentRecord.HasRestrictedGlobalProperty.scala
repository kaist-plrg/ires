package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.HasRestrictedGlobalProperty` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "HasRestrictedGlobalProperty", Param("envRec", Normal), List(Param("N", Normal)))
  val ids = List(
    "sec-hasrestrictedglobalproperty",
    "sec-global-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let ObjRec = envRec.ObjectRecord
  |  1:let globalObject = ObjRec.BindingObject
  |  2:app __x0__ = (globalObject.GetOwnProperty globalObject N)
  |  2:let existingProp = [? __x0__]
  |  3:if (= existingProp undefined) return false else 3:{}
  |  4:if (= existingProp.Configurable true) return false else 3:{}
  |  5:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _ObjRec_ be _envRec_.[[ObjectRecord]].""",
    """            1. Let _globalObject_ be the binding object for _ObjRec_.""",
    """            1. Let _existingProp_ be ? _globalObject_.[[GetOwnProperty]](_N_).""",
    """            1. If _existingProp_ is *undefined*, return *false*.""",
    """            1. If _existingProp_.[[Configurable]] is *true*, return *false*.""",
    """            1. Return *true*.""",
  )
}
