package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForInOfStatement[7,0].HasCallInTailPosition` extends Algo {
  val head = SyntaxDirectedHead("ForInOfStatement", 7, 0, Rhs(List(Terminal("for"), Terminal("await"), Terminal("("), Terminal("var"), NonTerminal("ForBinding", List(""), false), Terminal("of"), NonTerminal("AssignmentExpression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "HasCallInTailPosition", List(Param("call", Normal)))
  val ids = List(
    "sec-statement-rules",
    "sec-static-semantics-hascallintailposition",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Statement "HasCallInTailPosition" call)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return HasCallInTailPosition of |Statement| with argument _call_.""",
  )
}
