package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportMeta[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ImportMeta", 0, 0, Rhs(List(Terminal("import"), Terminal("."), Terminal("meta")), None), "EarlyErrors", List())
  val ids = List(
    "sec-left-hand-side-expressions-static-semantics-early-errors",
    "sec-static-semantics",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = false
  |  0:if (= absent (parse-syntax this "Module" (new []))) __x0__ = true else 2:{}
  |  0:if __x0__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if the syntactic goal symbol is not |Module|.""",
    """          </li>""",
  )
}
