package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Statement[2,0].HasCallInTailPosition` extends Algo {
  val head = SyntaxDirectedHead("Statement", 2, 0, Rhs(List(NonTerminal("EmptyStatement", List(""), false)), None), "HasCallInTailPosition", List(Param("call", Normal)))
  val ids = List(
    "sec-statement-rules",
    "sec-static-semantics-hascallintailposition",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """          1. Return *false*.""",
  )
}
