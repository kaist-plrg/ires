package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GlobalEnvironmentRecord.CreateMutableBinding` extends Algo {
  val head = MethodHead("GlobalEnvironmentRecord", "CreateMutableBinding", Param("envRec", Normal), List(Param("N", Normal), Param("D", Normal)))
  val ids = List(
    "sec-global-environment-records-createmutablebinding-n-d",
    "sec-global-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let DclRec = envRec.DeclarativeRecord
  |  1:app __x0__ = (DclRec.HasBinding DclRec N)
  |  1:if (= __x0__ true) throw TypeError else 0:{}
  |  2:app __x1__ = (DclRec.CreateMutableBinding DclRec N D)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _DclRec_ be _envRec_.[[DeclarativeRecord]].""",
    """            1. If _DclRec_.HasBinding(_N_) is *true*, throw a *TypeError* exception.""",
    """            1. Return _DclRec_.CreateMutableBinding(_N_, _D_).""",
  )
}
