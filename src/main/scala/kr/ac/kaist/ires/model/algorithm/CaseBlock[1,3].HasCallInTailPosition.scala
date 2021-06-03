package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseBlock[1,3].HasCallInTailPosition` extends Algo {
  val head = SyntaxDirectedHead("CaseBlock", 1, 3, Rhs(List(Terminal("{"), NonTerminal("CaseClauses", List(""), true), NonTerminal("DefaultClause", List(""), false), NonTerminal("CaseClauses", List(""), true), Terminal("}")), None), "HasCallInTailPosition", List(Param("call", Normal)))
  val ids = List(
    "sec-statement-rules",
    "sec-static-semantics-hascallintailposition",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:let has = false
  |  1:if (! (= CaseClauses0 absent)) {
  |    access __x0__ = (CaseClauses0 "HasCallInTailPosition" call)
  |    let has = __x0__
  |  } else 0:{}
  |  2:if (= has true) return true else 0:{}
  |  3:access __x1__ = (DefaultClause "HasCallInTailPosition" call)
  |  3:let has = __x1__
  |  4:if (= has true) return true else 0:{}
  |  5:if (! (= CaseClauses1 absent)) {
  |    access __x2__ = (CaseClauses1 "HasCallInTailPosition" call)
  |    let has = __x2__
  |  } else 0:{}
  |  6:return has
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _has_ be *false*.""",
    """          1. If the first |CaseClauses| is present, let _has_ be HasCallInTailPosition of the first |CaseClauses| with argument _call_.""",
    """          1. If _has_ is *true*, return *true*.""",
    """          1. Let _has_ be HasCallInTailPosition of |DefaultClause| with argument _call_.""",
    """          1. If _has_ is *true*, return *true*.""",
    """          1. If the second |CaseClauses| is present, let _has_ be HasCallInTailPosition of the second |CaseClauses| with argument _call_.""",
    """          1. Return _has_.""",
  )
}
