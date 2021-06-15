package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.DeleteBinding` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "DeleteBinding", Param("envRec", Normal), List(Param("N", Normal)))
  val ids = List(
    "sec-global-environment-records-deletebinding-n",
    "sec-global-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let DclRec = envRec.DeclarativeRecord
  |  1:app __x0__ = (DclRec.HasBinding DclRec N)
  |  1:if (= __x0__ true) {
  |    2:app __x1__ = (DclRec.DeleteBinding DclRec N)
  |    2:return __x1__
  |  } else 3:{}
  |  3:let ObjRec = envRec.ObjectRecord
  |  4:let globalObject = ObjRec.BindingObject
  |  5:app __x2__ = (HasOwnProperty globalObject N)
  |  5:let existingProp = [? __x2__]
  |  6:if (= existingProp true) {
  |    7:app __x3__ = (ObjRec.DeleteBinding ObjRec N)
  |    7:let status = [? __x3__]
  |    8:if (= status true) {
  |      9:let varNames = envRec.VarNames
  |      10:??? "If id:{N} is an element of id:{varNames} , remove that element from the id:{varNames} ."
  |    } else 3:{}
  |    11:return status
  |  } else 3:{}
  |  12:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _DclRec_ be _envRec_.[[DeclarativeRecord]].""",
    """            1. If _DclRec_.HasBinding(_N_) is *true*, then""",
    """              1. Return _DclRec_.DeleteBinding(_N_).""",
    """            1. Let _ObjRec_ be _envRec_.[[ObjectRecord]].""",
    """            1. Let _globalObject_ be the binding object for _ObjRec_.""",
    """            1. Let _existingProp_ be ? HasOwnProperty(_globalObject_, _N_).""",
    """            1. If _existingProp_ is *true*, then""",
    """              1. Let _status_ be ? _ObjRec_.DeleteBinding(_N_).""",
    """              1. If _status_ is *true*, then""",
    """                1. Let _varNames_ be _envRec_.[[VarNames]].""",
    """                1. If _N_ is an element of _varNames_, remove that element from the _varNames_.""",
    """              1. Return _status_.""",
    """            1. Return *true*.""",
  )
}
