package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateSpans[0,0].TemplateStrings` extends Algo {
  val head = SyntaxDirectedHead("TemplateSpans", 0, 0, Rhs(List(NonTerminal("TemplateTail", List(""), false)), None), "TemplateStrings", List(Param("raw", Normal)))
  val ids = List(
    "sec-static-semantics-templatestrings",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  2:if (= raw false) {
  |    1:access __x0__ = (TemplateTail "TV")
  |    1:let tail = __x0__
  |  } else {
  |    3:access __x1__ = (TemplateTail "TRV")
  |    3:let tail = __x1__
  |  }
  |  4:return (new [tail])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _raw_ is *false*, then""",
    """            1. Let _tail_ be the TV of |TemplateTail|.""",
    """          1. Else,""",
    """            1. Let _tail_ be the TRV of |TemplateTail|.""",
    """          1. Return a List whose sole element is _tail_.""",
  )
}
