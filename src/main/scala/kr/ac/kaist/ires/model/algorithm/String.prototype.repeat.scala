package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.repeat` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.repeat"""), List(Param("count", Normal)))
  val ids = List(
    "sec-string.prototype.repeat",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ToString O)
  |  1:let S = [? __x1__]
  |  2:app __x2__ = (ToIntegerOrInfinity count)
  |  2:let n = [? __x2__]
  |  3:if (|| (< n 0i) (= n Infinity)) throw RangeError else 4:{}
  |  4:if (= n 0i) return "" else 4:{}
  |  5:??? "Return the String value that is made from id:{n} copies of id:{S} appended together ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _n_ be ? ToIntegerOrInfinity(_count_).""",
    """          1. If _n_ < 0 or _n_ is +∞, throw a *RangeError* exception.""",
    """          1. If _n_ is 0, return the empty String.""",
    """          1. Return the String value that is made from _n_ copies of _S_ appended together.""",
  )
}
