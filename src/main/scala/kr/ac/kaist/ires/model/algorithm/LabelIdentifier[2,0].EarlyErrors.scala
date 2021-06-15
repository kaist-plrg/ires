package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelIdentifier[2,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("LabelIdentifier", 2, 0, Rhs(List(Terminal("await")), None), "EarlyErrors", List())
  val ids = List(
    "sec-identifiers-static-semantics-early-errors",
    "sec-identifiers",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = false
  |  0:if (= absent (parse-syntax this "Module" (new []))) __x0__ = true else 2:{}
  |  0:if __x0__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if the goal symbol of the syntactic grammar is |Module|.""",
    """        </li>""",
  )
}
