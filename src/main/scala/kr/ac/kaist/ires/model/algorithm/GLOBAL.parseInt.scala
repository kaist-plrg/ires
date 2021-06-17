package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.parseInt` extends Algo {
  val head = BuiltinHead(parseRef("""parseInt"""), List(Param("string", Normal), Param("radix", Normal)))
  val ids = List(
    "sec-parseint-string-radix",
    "sec-function-properties-of-the-global-object",
    "sec-global-object",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToString string)
  |  0:let inputString = [? __x0__]
  |  1:app __x1__ = (TrimString inputString CONST_start)
  |  1:let S = [! __x1__]
  |  2:let sign = 1i
  |  3:??? "If id:{S} is not empty and the first code unit of id:{S} is the code unit 0x002D ( HYPHEN - MINUS ) , set id:{sign} to - 1 ."
  |  4:??? "If id:{S} is not empty and the first code unit of id:{S} is the code unit 0x002B ( PLUS SIGN ) or the code unit 0x002D ( HYPHEN - MINUS ) , remove the first code unit from id:{S} ."
  |  5:app __x2__ = (ToInt32 radix)
  |  5:let R = [? __x2__]
  |  6:let stripPrefix = true
  |  10:if (! (== R 0i)) {
  |    8:if (|| (< R 2i) (< 36i R)) return NaN else 71:{}
  |    9:if (! (== R 16i)) stripPrefix = false else 71:{}
  |  } else R = 10i
  |  12:if (= stripPrefix true) ??? "If the length of id:{S} is at least 2 and the first two code units of id:{S} are either value:{\"0x\"} or value:{\"0X\"} , then in:{} out:{}" else 71:{}
  |  16:??? "If id:{S} contains a code unit that is not a radix - id:{R} digit , let id:{end} be the index within id:{S} of the first such code unit ; otherwise , let id:{end} be the length of id:{S} ."
  |  17:let __x3__ = ""
  |  17:let __x4__ = 0i
  |  17:while (< __x4__ (+ end 1i)) {
  |    access __x5__ = (S __x4__)
  |    __x3__ = (+ __x3__ __x5__)
  |    __x4__ = (+ __x4__ 1i)
  |  }
  |  17:let Z = __x3__
  |  18:if (= Z.length 0i) return NaN else 71:{}
  |  19:??? "Let id:{mathInt} be the integer value that is represented by id:{Z} in radix - id:{R} notation , using the letters < b > A < / b > - < b > Z < / b > and < b > a < / b > - < b > z < / b > for digits with values 10 through 35 . ( However , if id:{R} is 10 and id:{Z} contains more than 20 significant digits , every significant digit after the 20th may be replaced by a 0 digit , at the option of the implementation ; and if id:{R} is not 2 , 4 , 8 , 10 , 16 , or 32 , then id:{mathInt} may be an implementation - approximated value representing the integer value that is represented by id:{Z} in radix - id:{R} notation . )"
  |  20:if (== mathInt 0i) {
  |    21:if (== sign -1i) return -0.0 else 71:{}
  |    22:return 0i
  |  } else 71:{}
  |  23:return (* sign mathInt)
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _inputString_ be ? ToString(_string_).""",
    """        1. Let _S_ be ! TrimString(_inputString_, ~start~).""",
    """        1. Let _sign_ be 1.""",
    """        1. If _S_ is not empty and the first code unit of _S_ is the code unit 0x002D (HYPHEN-MINUS), set _sign_ to -1.""",
    """        1. If _S_ is not empty and the first code unit of _S_ is the code unit 0x002B (PLUS SIGN) or the code unit 0x002D (HYPHEN-MINUS), remove the first code unit from _S_.""",
    """        1. Let _R_ be ℝ(? ToInt32(_radix_)).""",
    """        1. Let _stripPrefix_ be *true*.""",
    """        1. If _R_ ≠ 0, then""",
    """          1. If _R_ < 2 or _R_ > 36, return *NaN*.""",
    """          1. If _R_ ≠ 16, set _stripPrefix_ to *false*.""",
    """        1. Else,""",
    """          1. Set _R_ to 10.""",
    """        1. If _stripPrefix_ is *true*, then""",
    """          1. If the length of _S_ is at least 2 and the first two code units of _S_ are either *"0x"* or *"0X"*, then""",
    """            1. Remove the first two code units from _S_.""",
    """            1. Set _R_ to 16.""",
    """        1. If _S_ contains a code unit that is not a radix-_R_ digit, let _end_ be the index within _S_ of the first such code unit; otherwise, let _end_ be the length of _S_.""",
    """        1. Let _Z_ be the substring of _S_ from 0 to _end_.""",
    """        1. If _Z_ is empty, return *NaN*.""",
    """        1. Let _mathInt_ be the integer value that is represented by _Z_ in radix-_R_ notation, using the letters <b>A</b>-<b>Z</b> and <b>a</b>-<b>z</b> for digits with values 10 through 35. (However, if _R_ is 10 and _Z_ contains more than 20 significant digits, every significant digit after the 20th may be replaced by a 0 digit, at the option of the implementation; and if _R_ is not 2, 4, 8, 10, 16, or 32, then _mathInt_ may be an implementation-approximated value representing the integer value that is represented by _Z_ in radix-_R_ notation.)""",
    """        1. If _mathInt_ = 0, then""",
    """          1. If _sign_ = -1, return *-0*<sub>𝔽</sub>.""",
    """          1. Return *+0*<sub>𝔽</sub>.""",
    """        1. Return 𝔽(_sign_ × _mathInt_).""",
  )
}
