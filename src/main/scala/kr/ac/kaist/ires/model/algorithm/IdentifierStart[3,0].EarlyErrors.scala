package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IdentifierStart[3,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("IdentifierStart", 3, 0, Rhs(List(Terminal("\\"), NonTerminal("UnicodeEscapeSequence", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-identifier-names-static-semantics-early-errors",
    "sec-identifier-names",
    "sec-names-and-keywords",
    "sec-ecmascript-language-lexical-grammar",
  )
  val rawBody = parseInst("""??? "It is a Syntax Error if the SV of nt:{UnicodeEscapeSequence} is none of value:{\"$\"} , or value:{\"_\"} , or ! UTF16EncodeCodePoint ( id:{cp} ) for some Unicode code point id:{cp} matched by the nt:{UnicodeIDStart} lexical grammar production ."""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if the SV of |UnicodeEscapeSequence| is none of *"$"*, or *"_"*, or ! UTF16EncodeCodePoint(_cp_) for some Unicode code point _cp_ matched by the |UnicodeIDStart| lexical grammar production.""",
    """          </li>""",
  )
}
