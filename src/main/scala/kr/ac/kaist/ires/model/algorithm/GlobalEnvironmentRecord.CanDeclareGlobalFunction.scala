package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.CanDeclareGlobalFunction` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "CanDeclareGlobalFunction", Param("envRec", Normal), List(Param("N", Normal)))
  val ids = List(
    "sec-candeclareglobalfunction",
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
  |  3:if (= existingProp undefined) {
  |    app __x1__ = (IsExtensible globalObject)
  |    return [? __x1__]
  |  } else 3:{}
  |  4:if (= existingProp.Configurable true) return true else 3:{}
  |  5:??? "If IsDataDescriptor ( id:{existingProp} ) is value:{true} and id:{existingProp} has attribute values { [ [ Writable ] ] : value:{true} , [ [ Enumerable ] ] : value:{true} } , return value:{true} ."
  |  6:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _ObjRec_ be _envRec_.[[ObjectRecord]].""",
    """            1. Let _globalObject_ be the binding object for _ObjRec_.""",
    """            1. Let _existingProp_ be ? _globalObject_.[[GetOwnProperty]](_N_).""",
    """            1. If _existingProp_ is *undefined*, return ? IsExtensible(_globalObject_).""",
    """            1. If _existingProp_.[[Configurable]] is *true*, return *true*.""",
    """            1. If IsDataDescriptor(_existingProp_) is *true* and _existingProp_ has attribute values { [[Writable]]: *true*, [[Enumerable]]: *true* }, return *true*.""",
    """            1. Return *false*.""",
  )
}
