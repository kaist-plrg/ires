package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ResolveThisBinding` extends Algo {
  val head = NormalHead("ResolveThisBinding", List())
  val ids = List(
    "sec-resolvethisbinding",
    "sec-execution-contexts",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetThisEnvironment)
  |  0:let envRec = __x0__
  |  1:app __x1__ = (envRec.GetThisBinding envRec)
  |  1:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _envRec_ be GetThisEnvironment().""",
    """        1. Return ? _envRec_.GetThisBinding().""",
  )
}
