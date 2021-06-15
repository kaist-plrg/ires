package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.charCodeAt` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.charCodeAt"""), List(Param("pos", Normal)))
  val ids = List(
    "sec-string.prototype.charcodeat",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ToString O)
  |  1:let S = [? __x1__]
  |  2:app __x2__ = (ToIntegerOrInfinity pos)
  |  2:let position = [? __x2__]
  |  3:let size = S.length
  |  4:if (|| (< position 0i) (! (< position size))) return NaN else 6:{}
  |  5:??? "Return the Number value for the numeric value of the code unit at index id:{position} within the String id:{S} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _position_ be ? ToIntegerOrInfinity(_pos_).""",
    """          1. Let _size_ be the length of _S_.""",
    """          1. If _position_ < 0 or _position_ ≥ _size_, return *NaN*.""",
    """          1. Return the Number value for the numeric value of the code unit at index _position_ within the String _S_.""",
  )
}
