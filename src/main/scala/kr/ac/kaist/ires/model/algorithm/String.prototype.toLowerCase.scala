package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.toLowerCase` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.toLowerCase"""), List())
  val ids = List(
    "sec-string.prototype.tolowercase",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ToString O)
  |  1:let S = [? __x1__]
  |  2:app __x2__ = (StringToCodePoints S)
  |  2:let sText = [! __x2__]
  |  3:??? "Let id:{lowerText} be the result of toLowercase ( id:{sText} ) , according to the Unicode Default Case Conversion algorithm ."
  |  4:app __x3__ = (CodePointsToString lowerText)
  |  4:let L = [! __x3__]
  |  5:return L
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _sText_ be ! StringToCodePoints(_S_).""",
    """          1. Let _lowerText_ be the result of toLowercase(_sText_), according to the Unicode Default Case Conversion algorithm.""",
    """          1. Let _L_ be ! CodePointsToString(_lowerText_).""",
    """          1. Return _L_.""",
  )
}
