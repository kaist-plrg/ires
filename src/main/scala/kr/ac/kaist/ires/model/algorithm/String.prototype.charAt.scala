package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.charAt` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.charAt"""), List(Param("pos", Normal)))
  val ids = List(
    "sec-string.prototype.charat",
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
  |  4:if (|| (< position 0i) (! (< position size))) return "" else 6:{}
  |  5:??? "Return the String value of length 1 , containing one code unit from id:{S} , namely the code unit at index id:{position} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _position_ be ? ToIntegerOrInfinity(_pos_).""",
    """          1. Let _size_ be the length of _S_.""",
    """          1. If _position_ < 0 or _position_ ≥ _size_, return the empty String.""",
    """          1. Return the String value of length 1, containing one code unit from _S_, namely the code unit at index _position_.""",
  )
}
