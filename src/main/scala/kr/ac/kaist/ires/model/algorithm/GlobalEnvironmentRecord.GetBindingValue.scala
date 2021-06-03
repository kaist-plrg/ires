package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.GetBindingValue` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "GetBindingValue", Param("envRec", Normal), List(Param("N", Normal), Param("S", Normal)))
  val ids = List(
    "sec-global-environment-records-getbindingvalue-n-s",
    "sec-global-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let DclRec = envRec.DeclarativeRecord
  |  1:app __x0__ = (DclRec.HasBinding DclRec N)
  |  1:if (= __x0__ true) {
  |    2:app __x1__ = (DclRec.GetBindingValue DclRec N S)
  |    2:return __x1__
  |  } else 3:{}
  |  3:let ObjRec = envRec.ObjectRecord
  |  4:app __x2__ = (ObjRec.GetBindingValue ObjRec N S)
  |  4:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _DclRec_ be _envRec_.[[DeclarativeRecord]].""",
    """            1. If _DclRec_.HasBinding(_N_) is *true*, then""",
    """              1. Return _DclRec_.GetBindingValue(_N_, _S_).""",
    """            1. Let _ObjRec_ be _envRec_.[[ObjectRecord]].""",
    """            1. Return ? _ObjRec_.GetBindingValue(_N_, _S_).""",
  )
}
