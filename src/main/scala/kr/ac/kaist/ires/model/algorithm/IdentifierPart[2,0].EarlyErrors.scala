package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IdentifierPart[2,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("IdentifierPart", 2, 0, Rhs(List(Terminal("\\"), NonTerminal("UnicodeEscapeSequence", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-identifier-names-static-semantics-early-errors",
    "sec-identifier-names",
    "sec-names-and-keywords",
    "sec-ecmascript-language-lexical-grammar",
  )
  val rawBody = parseInst("""??? "It is a Syntax Error if the SV of nt:{UnicodeEscapeSequence} is none of value:{\"$\"} , value:{\"_\"} , ! UTF16EncodeCodePoint ( < ZWNJ > ) , ! UTF16EncodeCodePoint ( < ZWJ > ) , or ! UTF16EncodeCodePoint ( id:{cp} ) for some Unicode code point id:{cp} that would be matched by the nt:{UnicodeIDContinue} lexical grammar production ."""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if the SV of |UnicodeEscapeSequence| is none of *"$"*, *"_"*, ! UTF16EncodeCodePoint(<ZWNJ>), ! UTF16EncodeCodePoint(<ZWJ>), or ! UTF16EncodeCodePoint(_cp_) for some Unicode code point _cp_ that would be matched by the |UnicodeIDContinue| lexical grammar production.""",
    """          </li>""",
  )
}
