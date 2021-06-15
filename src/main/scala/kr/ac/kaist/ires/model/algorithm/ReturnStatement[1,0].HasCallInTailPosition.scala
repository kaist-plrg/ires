package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ReturnStatement[1,0].HasCallInTailPosition` extends Algo {
  val head = SyntaxDirectedHead("ReturnStatement", 1, 0, Rhs(List(Terminal("return"), NonTerminal("Expression", List(""), false), Terminal(";")), None), "HasCallInTailPosition", List(Param("call", Normal)))
  val ids = List(
    "sec-statement-rules",
    "sec-static-semantics-hascallintailposition",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Expression "HasCallInTailPosition" call)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return HasCallInTailPosition of |Expression| with argument _call_.""",
  )
}
