package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.localeCompare` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.localeCompare"""), List(Param("that", Normal), Param("reserved1", Optional), Param("reserved2", Optional)))
  val ids = List(
    "sec-string.prototype.localecompare",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ToString O)
  |  1:let S = [? __x1__]
  |  2:app __x2__ = (ToString that)
  |  2:let That = [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _That_ be ? ToString(_that_).""",
  )
}
