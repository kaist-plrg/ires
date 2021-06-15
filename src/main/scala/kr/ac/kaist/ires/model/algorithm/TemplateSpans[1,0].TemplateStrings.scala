package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateSpans[1,0].TemplateStrings` extends Algo {
  val head = SyntaxDirectedHead("TemplateSpans", 1, 0, Rhs(List(NonTerminal("TemplateMiddleList", List(""), false), NonTerminal("TemplateTail", List(""), false)), None), "TemplateStrings", List(Param("raw", Normal)))
  val ids = List(
    "sec-static-semantics-templatestrings",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (TemplateMiddleList "TemplateStrings" raw)
  |  0:let middle = __x0__
  |  3:if (= raw false) {
  |    2:access __x1__ = (TemplateTail "TV")
  |    2:let tail = __x1__
  |  } else {
  |    4:access __x2__ = (TemplateTail "TRV")
  |    4:let tail = __x2__
  |  }
  |  5:let __x3__ = (copy-obj middle)
  |  5:append tail -> __x3__
  |  5:return __x3__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _middle_ be TemplateStrings of |TemplateMiddleList| with argument _raw_.""",
    """          1. If _raw_ is *false*, then""",
    """            1. Let _tail_ be the TV of |TemplateTail|.""",
    """          1. Else,""",
    """            1. Let _tail_ be the TRV of |TemplateTail|.""",
    """          1. Return a List whose elements are the elements of _middle_ followed by _tail_.""",
  )
}
