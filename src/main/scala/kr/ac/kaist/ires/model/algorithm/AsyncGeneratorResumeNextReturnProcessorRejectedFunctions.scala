package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorResumeNextReturnProcessorRejectedFunctions` extends Algo {
  val head = BuiltinHead(parseRef("""AsyncGeneratorResumeNextReturnProcessorRejectedFunctions"""), List())
  val ids = List(
    "async-generator-resume-next-return-processor-rejected",
    "sec-asyncgeneratorresumenext",
    "sec-asyncgenerator-abstract-operations",
    "sec-asyncgenerator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let F = CONTEXT.Function
  |  1:F.Generator.AsyncGeneratorState = CONST_completed
  |  2:app __x0__ = (AsyncGeneratorReject F.Generator reason)
  |  2:return [! __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _F_ be the active function object.""",
    """            1. Set _F_.[[Generator]].[[AsyncGeneratorState]] to ~completed~.""",
    """            1. Return ! AsyncGeneratorReject(_F_.[[Generator]], _reason_).""",
  )
}
