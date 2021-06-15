package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetNewTarget` extends Algo {
  val head = NormalHead("GetNewTarget", List())
  val ids = List(
    "sec-getnewtarget",
    "sec-execution-contexts",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetThisEnvironment)
  |  0:let envRec = __x0__
  |  1:assert (! (= envRec.NewTarget absent))
  |  2:return envRec.NewTarget
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _envRec_ be GetThisEnvironment().""",
    """        1. Assert: _envRec_ has a [[NewTarget]] field.""",
    """        1. Return _envRec_.[[NewTarget]].""",
  )
}
