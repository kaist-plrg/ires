package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IdentifierName[1,0].StringValue` extends Algo {
  val head = SyntaxDirectedHead("IdentifierName", 1, 0, Rhs(List(NonTerminal("IdentifierName", List(""), false), NonTerminal("IdentifierPart", List(""), false)), None), "StringValue", List())
  val ids = List(
    "sec-static-semantics-stringvalue",
    "sec-identifiers",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:let idText = (get-syntax IdentifierName)
  |  1:??? "Let id:{idTextUnescaped} be the result of replacing any occurrences of code:{\\\\} nt:{UnicodeEscapeSequence} in id:{idText} with the code point represented by the nt:{UnicodeEscapeSequence} ."
  |  2:app __x0__ = (CodePointsToString idTextUnescaped)
  |  2:return [! __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _idText_ be the source text matched by |IdentifierName|.""",
    """        1. Let _idTextUnescaped_ be the result of replacing any occurrences of `\\` |UnicodeEscapeSequence| in _idText_ with the code point represented by the |UnicodeEscapeSequence|.""",
    """        1. Return ! CodePointsToString(_idTextUnescaped_).""",
  )
}
