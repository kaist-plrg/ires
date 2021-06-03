package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ContinueStatement[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ContinueStatement", 0, 0, Rhs(List(Terminal("continue"), Terminal(";")), None), "EarlyErrors", List())
  val ids = List(
    "sec-continue-statement-static-semantics-early-errors",
    "sec-continue-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = false
  |  0:if (= absent (parse-syntax ContinueStatement "IterationStatement" (new []))) __x0__ = true else 2:{}
  |  0:if __x0__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if this |ContinueStatement| is not nested, directly or indirectly (but not crossing function boundaries), within an |IterationStatement|.""",
    """        </li>""",
  )
}
