package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.indexOf` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.indexOf"""), List(Param("searchString", Normal), Param("position", Optional)))
  val ids = List(
    "sec-string.prototype.indexof",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ToString O)
  |  1:let S = [? __x1__]
  |  2:app __x2__ = (ToString searchString)
  |  2:let searchStr = [? __x2__]
  |  3:app __x3__ = (ToIntegerOrInfinity position)
  |  3:let pos = [? __x3__]
  |  5:let len = S.length
  |  6:??? "Let id:{start} be the result of clamping id:{pos} between 0 and id:{len} ."
  |  7:app __x4__ = (StringIndexOf S searchStr start)
  |  7:return [! __x4__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _searchStr_ be ? ToString(_searchString_).""",
    """          1. Let _pos_ be ? ToIntegerOrInfinity(_position_).""",
    """          1. Assert: If _position_ is *undefined*, then _pos_ is 0.""",
    """          1. Let _len_ be the length of _S_.""",
    """          1. Let _start_ be the result of clamping _pos_ between 0 and _len_.""",
    """          1. Return 𝔽(! StringIndexOf(_S_, _searchStr_, _start_)).""",
  )
}
