package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.HasBinding` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "HasBinding", Param("envRec", Normal), List(Param("N", Normal)))
  val ids = List(
    "sec-global-environment-records-hasbinding-n",
    "sec-global-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let DclRec = envRec.DeclarativeRecord
  |  1:app __x0__ = (DclRec.HasBinding DclRec N)
  |  1:if (= __x0__ true) return true else 0:{}
  |  2:let ObjRec = envRec.ObjectRecord
  |  3:app __x1__ = (ObjRec.HasBinding ObjRec N)
  |  3:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _DclRec_ be _envRec_.[[DeclarativeRecord]].""",
    """            1. If _DclRec_.HasBinding(_N_) is *true*, return *true*.""",
    """            1. Let _ObjRec_ be _envRec_.[[ObjectRecord]].""",
    """            1. Return ? _ObjRec_.HasBinding(_N_).""",
  )
}
