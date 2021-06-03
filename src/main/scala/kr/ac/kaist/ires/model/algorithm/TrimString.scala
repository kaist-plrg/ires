package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TrimString` extends Algo {
  val head = NormalHead("TrimString", List(Param("string", Normal), Param("where", Normal)))
  val ids = List(
    "sec-trimstring",
    "sec-string.prototype.trim",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible string)
  |  0:let str = [? __x0__]
  |  1:app __x1__ = (ToString str)
  |  1:let S = [? __x1__]
  |  2:??? "If id:{where} is const:{start} , let id:{T} be the String value that is a copy of id:{S} with leading white space removed ."
  |  3:??? "Else if id:{where} is const:{end} , let id:{T} be the String value that is a copy of id:{S} with trailing white space removed ."
  |  4:??? "Else , in:{} out:{}"
  |  7:return T
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _str_ be ? RequireObjectCoercible(_string_).""",
    """            1. Let _S_ be ? ToString(_str_).""",
    """            1. If _where_ is ~start~, let _T_ be the String value that is a copy of _S_ with leading white space removed.""",
    """            1. Else if _where_ is ~end~, let _T_ be the String value that is a copy of _S_ with trailing white space removed.""",
    """            1. Else,""",
    """              1. Assert: _where_ is ~start+end~.""",
    """              1. Let _T_ be the String value that is a copy of _S_ with both leading and trailing white space removed.""",
    """            1. Return _T_.""",
  )
}
