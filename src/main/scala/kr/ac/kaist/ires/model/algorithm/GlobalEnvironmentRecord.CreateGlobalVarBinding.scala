package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.CreateGlobalVarBinding` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "CreateGlobalVarBinding", Param("envRec", Normal), List(Param("N", Normal), Param("D", Normal)))
  val ids = List(
    "sec-createglobalvarbinding",
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
  |  3:app __x1__ = (IsExtensible globalObject)
  |  3:let extensible = [? __x1__]
  |  4:if (&& (= hasProperty false) (= extensible true)) {
  |    5:app __x2__ = (ObjRec.CreateMutableBinding ObjRec N D)
  |    5:[? __x2__]
  |    6:app __x3__ = (ObjRec.InitializeBinding ObjRec N undefined)
  |    6:[? __x3__]
  |  } else 3:{}
  |  7:let varDeclaredNames = envRec.VarNames
  |  8:if (! (contains varDeclaredNames N)) append N -> varDeclaredNames else 3:{}
  |  10:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _ObjRec_ be _envRec_.[[ObjectRecord]].""",
    """            1. Let _globalObject_ be the binding object for _ObjRec_.""",
    """            1. Let _hasProperty_ be ? HasOwnProperty(_globalObject_, _N_).""",
    """            1. Let _extensible_ be ? IsExtensible(_globalObject_).""",
    """            1. If _hasProperty_ is *false* and _extensible_ is *true*, then""",
    """              1. Perform ? _ObjRec_.CreateMutableBinding(_N_, _D_).""",
    """              1. Perform ? _ObjRec_.InitializeBinding(_N_, *undefined*).""",
    """            1. Let _varDeclaredNames_ be _envRec_.[[VarNames]].""",
    """            1. If _varDeclaredNames_ does not contain _N_, then""",
    """              1. Append _N_ to _varDeclaredNames_.""",
    """            1. Return NormalCompletion(~empty~).""",
  )
}
