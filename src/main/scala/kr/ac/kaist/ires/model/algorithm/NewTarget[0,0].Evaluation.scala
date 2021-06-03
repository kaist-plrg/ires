package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NewTarget[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("NewTarget", 0, 0, Rhs(List(Terminal("new"), Terminal("."), Terminal("target")), None), "Evaluation", List())
  val ids = List(
    "sec-meta-properties-runtime-semantics-evaluation",
    "sec-meta-properties",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetNewTarget)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return GetNewTarget().""",
  )
}
