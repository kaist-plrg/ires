package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SubstitutionTemplate[0,0].TemplateStrings` extends Algo {
  val head = SyntaxDirectedHead("SubstitutionTemplate", 0, 0, Rhs(List(NonTerminal("TemplateHead", List(""), false), NonTerminal("Expression", List(""), false), NonTerminal("TemplateSpans", List(""), false)), None), "TemplateStrings", List(Param("raw", Normal)))
  val ids = List(
    "sec-static-semantics-templatestrings",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  2:if (= raw false) {
  |    1:access __x0__ = (TemplateHead "TV")
  |    1:let head = __x0__
  |  } else {
  |    3:access __x1__ = (TemplateHead "TRV")
  |    3:let head = __x1__
  |  }
  |  4:access __x2__ = (TemplateSpans "TemplateStrings" raw)
  |  4:let tail = __x2__
  |  5:let list = (copy-obj tail)
  |  5:prepend head -> list
  |  5:return list
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _raw_ is *false*, then""",
    """            1. Let _head_ be the TV of |TemplateHead|.""",
    """          1. Else,""",
    """            1. Let _head_ be the TRV of |TemplateHead|.""",
    """          1. Let _tail_ be TemplateStrings of |TemplateSpans| with argument _raw_.""",
    """          1. Return a List whose elements are _head_ followed by the elements of _tail_.""",
  )
}
