package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ConditionalExpression[1,0].HasCallInTailPosition` extends Algo {
  val head = SyntaxDirectedHead("ConditionalExpression", 1, 0, Rhs(List(NonTerminal("ShortCircuitExpression", List(""), false), Terminal("?"), NonTerminal("AssignmentExpression", List(""), false), Terminal(":"), NonTerminal("AssignmentExpression", List(""), false)), None), "HasCallInTailPosition", List(Param("call", Normal)))
  val ids = List(
    "sec-expression-rules",
    "sec-static-semantics-hascallintailposition",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AssignmentExpression0 "HasCallInTailPosition" call)
  |  0:let has = __x0__
  |  1:if (= has true) return true else 0:{}
  |  2:access __x1__ = (AssignmentExpression1 "HasCallInTailPosition" call)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _has_ be HasCallInTailPosition of the first |AssignmentExpression| with argument _call_.""",
    """          1. If _has_ is *true*, return *true*.""",
    """          1. Return HasCallInTailPosition of the second |AssignmentExpression| with argument _call_.""",
  )
}
