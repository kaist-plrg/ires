package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BreakableStatement[0,0].ContainsUndefinedContinueTarget` extends Algo {
  val head = SyntaxDirectedHead("BreakableStatement", 0, 0, Rhs(List(NonTerminal("IterationStatement", List(""), false)), None), "ContainsUndefinedContinueTarget", List(Param("iterationSet", Normal), Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsundefinedcontinuetarget",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = (copy-obj iterationSet)
  |  0:let __x1__ = labelSet
  |  0:let __x2__ = 0i
  |  0:while (< __x2__ __x1__.length) {
  |    let __x3__ = __x1__[__x2__]
  |    append __x3__ -> __x0__
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  0:let newIterationSet = __x0__
  |  1:access __x4__ = (IterationStatement "ContainsUndefinedContinueTarget" newIterationSet (new []))
  |  1:return __x4__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _newIterationSet_ be a copy of _iterationSet_ with all the elements of _labelSet_ appended.""",
    """        1. Return ContainsUndefinedContinueTarget of |IterationStatement| with arguments _newIterationSet_ and « ».""",
  )
}
