package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StringPad` extends Algo {
  val head = NormalHead("StringPad", List(Param("O", Normal), Param("maxLength", Normal), Param("fillString", Normal), Param("placement", Normal)))
  val ids = List(
    "sec-stringpad",
    "sec-string.prototype.padstart",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:assert (|| (= placement CONST_start) (= placement CONST_end))
  |  1:app __x0__ = (ToString O)
  |  1:let S = [? __x0__]
  |  2:app __x1__ = (ToLength maxLength)
  |  2:let intMaxLength = [? __x1__]
  |  3:let stringLength = S.length
  |  4:if (! (< stringLength intMaxLength)) return S else 4:{}
  |  6:if (= fillString undefined) let filler = " " else {
  |    app __x2__ = (ToString fillString)
  |    let filler = [? __x2__]
  |  }
  |  7:if (= filler "") return S else 4:{}
  |  8:let fillLen = (- intMaxLength stringLength)
  |  9:??? "Let id:{truncatedStringFiller} be the String value consisting of repeated concatenations of id:{filler} truncated to length id:{fillLen} ."
  |  11:if (= placement CONST_start) return (+ truncatedStringFiller S) else return (+ S truncatedStringFiller)
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _placement_ is ~start~ or ~end~.""",
    """            1. Let _S_ be ? ToString(_O_).""",
    """            1. Let _intMaxLength_ be ℝ(? ToLength(_maxLength_)).""",
    """            1. Let _stringLength_ be the length of _S_.""",
    """            1. If _intMaxLength_ ≤ _stringLength_, return _S_.""",
    """            1. If _fillString_ is *undefined*, let _filler_ be the String value consisting solely of the code unit 0x0020 (SPACE).""",
    """            1. Else, let _filler_ be ? ToString(_fillString_).""",
    """            1. If _filler_ is the empty String, return _S_.""",
    """            1. Let _fillLen_ be _intMaxLength_ - _stringLength_.""",
    """            1. Let _truncatedStringFiller_ be the String value consisting of repeated concatenations of _filler_ truncated to length _fillLen_.""",
    """            1. If _placement_ is ~start~, return the string-concatenation of _truncatedStringFiller_ and _S_.""",
    """            1. Else, return the string-concatenation of _S_ and _truncatedStringFiller_.""",
  )
}
