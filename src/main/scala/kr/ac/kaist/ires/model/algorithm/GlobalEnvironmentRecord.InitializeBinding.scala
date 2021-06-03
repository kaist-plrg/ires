package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.InitializeBinding` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "InitializeBinding", Param("envRec", Normal), List(Param("N", Normal), Param("V", Normal)))
  val ids = List(
    "sec-global-environment-records-initializebinding-n-v",
    "sec-global-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let DclRec = envRec.DeclarativeRecord
  |  1:app __x0__ = (DclRec.HasBinding DclRec N)
  |  1:if (= __x0__ true) {
  |    2:app __x1__ = (DclRec.InitializeBinding DclRec N V)
  |    2:return __x1__
  |  } else 3:{}
  |  4:let ObjRec = envRec.ObjectRecord
  |  5:app __x2__ = (ObjRec.InitializeBinding ObjRec N V)
  |  5:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _DclRec_ be _envRec_.[[DeclarativeRecord]].""",
    """            1. If _DclRec_.HasBinding(_N_) is *true*, then""",
    """              1. Return _DclRec_.InitializeBinding(_N_, _V_).""",
    """            1. Assert: If the binding exists, it must be in the object Environment Record.""",
    """            1. Let _ObjRec_ be _envRec_.[[ObjectRecord]].""",
    """            1. Return ? _ObjRec_.InitializeBinding(_N_, _V_).""",
  )
}
