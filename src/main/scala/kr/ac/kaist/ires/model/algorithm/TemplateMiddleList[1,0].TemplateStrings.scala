package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateMiddleList[1,0].TemplateStrings` extends Algo {
  val head = SyntaxDirectedHead("TemplateMiddleList", 1, 0, Rhs(List(NonTerminal("TemplateMiddleList", List(""), false), NonTerminal("TemplateMiddle", List(""), false), NonTerminal("Expression", List(""), false)), None), "TemplateStrings", List(Param("raw", Normal)))
  val ids = List(
    "sec-static-semantics-templatestrings",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (TemplateMiddleList "TemplateStrings" raw)
  |  0:let front = __x0__
  |  3:if (= raw false) {
  |    2:access __x1__ = (TemplateMiddle "TV")
  |    2:let last = __x1__
  |  } else {
  |    4:access __x2__ = (TemplateMiddle "TRV")
  |    4:let last = __x2__
  |  }
  |  5:append last -> front
  |  6:return front
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _front_ be TemplateStrings of |TemplateMiddleList| with argument _raw_.""",
    """          1. If _raw_ is *false*, then""",
    """            1. Let _last_ be the TV of |TemplateMiddle|.""",
    """          1. Else,""",
    """            1. Let _last_ be the TRV of |TemplateMiddle|.""",
    """          1. Append _last_ as the last element of the List _front_.""",
    """          1. Return _front_.""",
  )
}
