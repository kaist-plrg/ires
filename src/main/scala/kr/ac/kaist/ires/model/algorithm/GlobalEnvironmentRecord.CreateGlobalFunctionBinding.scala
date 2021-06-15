package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.CreateGlobalFunctionBinding` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "CreateGlobalFunctionBinding", Param("envRec", Normal), List(Param("N", Normal), Param("V", Normal), Param("D", Normal)))
  val ids = List(
    "sec-createglobalfunctionbinding",
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
  |  5:if (|| (= existingProp undefined) (= existingProp.Configurable true)) let desc = (new PropertyDescriptor("Value" -> V, "Writable" -> true, "Enumerable" -> true, "Configurable" -> D)) else let desc = (new PropertyDescriptor("Value" -> V))
  |  7:app __x1__ = (DefinePropertyOrThrow globalObject N desc)
  |  7:[? __x1__]
  |  8:app __x2__ = (Set globalObject N V false)
  |  8:[? __x2__]
  |  9:let varDeclaredNames = envRec.VarNames
  |  10:if (! (contains varDeclaredNames N)) append N -> varDeclaredNames else 3:{}
  |  12:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _ObjRec_ be _envRec_.[[ObjectRecord]].""",
    """            1. Let _globalObject_ be the binding object for _ObjRec_.""",
    """            1. Let _existingProp_ be ? _globalObject_.[[GetOwnProperty]](_N_).""",
    """            1. If _existingProp_ is *undefined* or _existingProp_.[[Configurable]] is *true*, then""",
    """              1. Let _desc_ be the PropertyDescriptor { [[Value]]: _V_, [[Writable]]: *true*, [[Enumerable]]: *true*, [[Configurable]]: _D_ }.""",
    """            1. Else,""",
    """              1. Let _desc_ be the PropertyDescriptor { [[Value]]: _V_ }.""",
    """            1. Perform ? DefinePropertyOrThrow(_globalObject_, _N_, _desc_).""",
    """            1. [id="step-createglobalfunctionbinding-set"] Perform ? Set(_globalObject_, _N_, _V_, *false*).""",
    """            1. Let _varDeclaredNames_ be _envRec_.[[VarNames]].""",
    """            1. If _varDeclaredNames_ does not contain _N_, then""",
    """              1. Append _N_ to _varDeclaredNames_.""",
    """            1. Return NormalCompletion(~empty~).""",
  )
}
