package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseClause[0,1].HasCallInTailPosition` extends Algo {
  val head = SyntaxDirectedHead("CaseClause", 0, 1, Rhs(List(Terminal("case"), NonTerminal("Expression", List(""), false), Terminal(":"), NonTerminal("StatementList", List(""), true)), None), "HasCallInTailPosition", List(Param("call", Normal)))
  val ids = List(
    "sec-statement-rules",
    "sec-static-semantics-hascallintailposition",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= StatementList absent)) {
  |    access __x0__ = (StatementList "HasCallInTailPosition" call)
  |    return __x0__
  |  } else 0:{}
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If |StatementList| is present, return HasCallInTailPosition of |StatementList| with argument _call_.""",
    """          1. Return *false*.""",
  )
}
