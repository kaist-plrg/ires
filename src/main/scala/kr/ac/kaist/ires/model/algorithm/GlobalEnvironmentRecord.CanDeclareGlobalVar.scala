package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.CanDeclareGlobalVar` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "CanDeclareGlobalVar", Param("envRec", Normal), List(Param("N", Normal)))
  val ids = List(
    "sec-candeclareglobalvar",
    "sec-global-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let ObjRec = envRec.ObjectRecord
  |  1:let globalObject = ObjRec.BindingObject
  |  2:app __x0__ = (HasOwnProperty globalObject N)
  |  2:let hasProperty = [? __x0__]
  |  3:if (= hasProperty true) return true else 3:{}
  |  4:app __x1__ = (IsExtensible globalObject)
  |  4:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _ObjRec_ be _envRec_.[[ObjectRecord]].""",
    """            1. Let _globalObject_ be the binding object for _ObjRec_.""",
    """            1. Let _hasProperty_ be ? HasOwnProperty(_globalObject_, _N_).""",
    """            1. If _hasProperty_ is *true*, return *true*.""",
    """            1. Return ? IsExtensible(_globalObject_).""",
  )
}
