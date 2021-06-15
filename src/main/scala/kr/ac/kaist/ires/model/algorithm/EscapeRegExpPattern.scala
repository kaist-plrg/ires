package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EscapeRegExpPattern` extends Algo {
  val head = NormalHead("EscapeRegExpPattern", List(Param("P", Normal), Param("F", Normal)))
  val ids = List(
    "sec-escaperegexppattern",
    "sec-abstract-operations-for-the-regexp-constructor",
    "sec-regexp-constructor",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:??? "Let id:{S} be a String in the form of a nt:{Pattern[~U]} ( nt:{Pattern[+U]} if id:{F} contains value:{\"u\"} ) equivalent to id:{P} interpreted as UTF - 16 encoded Unicode code points ( link:{unhandled: sec-ecmascript-language-types-string-type} ) , in which certain code points are escaped as described below . id:{S} may or may not be identical to id:{P} ; however , the Abstract Closure that would result from evaluating id:{S} as a nt:{Pattern[~U]} ( nt:{Pattern[+U]} if id:{F} contains value:{\"u\"} ) must behave identically to the Abstract Closure given by the constructed object ' s [ [ RegExpMatcher ] ] internal slot . Multiple calls to this abstract operation using the same values for id:{P} and id:{F} must produce identical results ."
  |  1:??? "The code points code:{/} or any nt:{LineTerminator} occurring in the pattern shall be escaped in id:{S} as necessary to ensure that the string - concatenation of value:{\"/\"} , id:{S} , value:{\"/\"} , and id:{F} can be parsed ( in an appropriate lexical context ) as a nt:{RegularExpressionLiteral} that behaves identically to the constructed regular expression . For example , if id:{P} is value:{\"/\"} , then id:{S} could be value:{\"/\"} or value:{\"u002F\"} , among other possibilities , but not value:{\"/\"} , because code:{///} followed by id:{F} would be parsed as a nt:{SingleLineComment} rather than a nt:{RegularExpressionLiteral} . If id:{P} is the empty String , this specification can be met by letting id:{S} be value:{\"(?:)\"} ."
  |  2:return S
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _S_ be a String in the form of a |Pattern[~U]| (|Pattern[+U]| if _F_ contains *"u"*) equivalent to _P_ interpreted as UTF-16 encoded Unicode code points (<emu-xref href="#sec-ecmascript-language-types-string-type"></emu-xref>), in which certain code points are escaped as described below. _S_ may or may not be identical to _P_; however, the Abstract Closure that would result from evaluating _S_ as a |Pattern[~U]| (|Pattern[+U]| if _F_ contains *"u"*) must behave identically to the Abstract Closure given by the constructed object's [[RegExpMatcher]] internal slot. Multiple calls to this abstract operation using the same values for _P_ and _F_ must produce identical results.""",
    """            1. The code points `/` or any |LineTerminator| occurring in the pattern shall be escaped in _S_ as necessary to ensure that the string-concatenation of *"/"*, _S_, *"/"*, and _F_ can be parsed (in an appropriate lexical context) as a |RegularExpressionLiteral| that behaves identically to the constructed regular expression. For example, if _P_ is *"/"*, then _S_ could be *"\\/"* or *"\\u002F"*, among other possibilities, but not *"/"*, because `///` followed by _F_ would be parsed as a |SingleLineComment| rather than a |RegularExpressionLiteral|. If _P_ is the empty String, this specification can be met by letting _S_ be *"(?:)"*.""",
    """            1. Return _S_.""",
  )
}
