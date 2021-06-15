package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.padEnd` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.padEnd"""), List(Param("maxLength", Normal), Param("fillString", Optional)))
  val ids = List(
    "sec-string.prototype.padend",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (StringPad O maxLength fillString CONST_end)
  |  1:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Return ? StringPad(_O_, _maxLength_, _fillString_, ~end~).""",
  )
}
