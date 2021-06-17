package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Array.prototype.flat` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.flat"""), List(Param("depth", Optional)))
  val ids = List(
    "sec-array.prototype.flat",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let sourceLen = [? __x1__]
  |  2:let depthNum = 1i
  |  3:if (! (= depth undefined)) {
  |    4:app __x2__ = (ToIntegerOrInfinity depth)
  |    4:depthNum = [? __x2__]
  |    5:if (< depthNum 0i) depthNum = 0i else 19:{}
  |  } else 19:{}
  |  6:app __x3__ = (ArraySpeciesCreate O 0i)
  |  6:let A = [? __x3__]
  |  7:app __x4__ = (FlattenIntoArray A O sourceLen 0i depthNum)
  |  7:[? __x4__]
  |  8:return A
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _sourceLen_ be ? LengthOfArrayLike(_O_).""",
    """          1. Let _depthNum_ be 1.""",
    """          1. If _depth_ is not *undefined*, then""",
    """            1. Set _depthNum_ to ? ToIntegerOrInfinity(_depth_).""",
    """            1. If _depthNum_ < 0, set _depthNum_ to 0.""",
    """          1. Let _A_ be ? ArraySpeciesCreate(_O_, 0).""",
    """          1. Perform ? FlattenIntoArray(_A_, _O_, _sourceLen_, 0, _depthNum_).""",
    """          1. Return _A_.""",
  )
}
