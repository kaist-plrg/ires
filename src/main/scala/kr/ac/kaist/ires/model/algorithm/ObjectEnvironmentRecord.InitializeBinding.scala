package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectEnvironmentRecord.InitializeBinding` extends Algo {
  val head = MethodHead("ObjectEnvironmentRecord", "InitializeBinding", Param("envRec", Normal), List(Param("N", Normal), Param("V", Normal)))
  val ids = List(
    "sec-object-environment-records-initializebinding-n-v",
    "sec-object-environment-records",
    "sec-the-environment-record-type-hierarchy",
    "sec-environment-records",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (envRec.SetMutableBinding envRec N V false)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Return ? _envRec_.SetMutableBinding(_N_, _V_, *false*).""",
  )
}
