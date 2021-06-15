package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.SetMutableBinding` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "SetMutableBinding", Param("envRec", Normal), List(Param("N", Normal), Param("V", Normal), Param("S", Normal)))
  val ids = List(
    "sec-global-environment-records-setmutablebinding-n-v-s",
    "sec-global-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let DclRec = envRec.DeclarativeRecord
  |  1:app __x0__ = (DclRec.HasBinding DclRec N)
  |  1:if (= __x0__ true) {
  |    2:app __x1__ = (DclRec.SetMutableBinding DclRec N V S)
  |    2:return __x1__
  |  } else 3:{}
  |  3:let ObjRec = envRec.ObjectRecord
  |  4:app __x2__ = (ObjRec.SetMutableBinding ObjRec N V S)
  |  4:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _DclRec_ be _envRec_.[[DeclarativeRecord]].""",
    """            1. If _DclRec_.HasBinding(_N_) is *true*, then""",
    """              1. Return _DclRec_.SetMutableBinding(_N_, _V_, _S_).""",
    """            1. Let _ObjRec_ be _envRec_.[[ObjectRecord]].""",
    """            1. Return ? _ObjRec_.SetMutableBinding(_N_, _V_, _S_).""",
  )
}
