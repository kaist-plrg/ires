package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StatementList[1,0].HasCallInTailPosition` extends Algo {
  val head = SyntaxDirectedHead("StatementList", 1, 0, Rhs(List(NonTerminal("StatementList", List(""), false), NonTerminal("StatementListItem", List(""), false)), None), "HasCallInTailPosition", List(Param("call", Normal)))
  val ids = List(
    "sec-statement-rules",
    "sec-static-semantics-hascallintailposition",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (StatementList "HasCallInTailPosition" call)
  |  0:let has = __x0__
  |  1:if (= has true) return true else 0:{}
  |  2:access __x1__ = (StatementListItem "HasCallInTailPosition" call)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _has_ be HasCallInTailPosition of |StatementList| with argument _call_.""",
    """          1. If _has_ is *true*, return *true*.""",
    """          1. Return HasCallInTailPosition of |StatementListItem| with argument _call_.""",
  )
}
