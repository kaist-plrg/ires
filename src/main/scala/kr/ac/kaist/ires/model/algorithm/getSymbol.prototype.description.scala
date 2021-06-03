package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::getSymbol.prototype.description` extends Algo {
  val head = BuiltinHead(parseRef("""getSymbol.prototype.description"""), List())
  val ids = List(
    "sec-symbol.prototype.description",
    "sec-properties-of-the-symbol-prototype-object",
    "sec-symbol-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:let s = this
  |  1:app __x0__ = (thisSymbolValue s)
  |  1:let sym = [? __x0__]
  |  2:return sym.Description
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _s_ be the *this* value.""",
    """          1. Let _sym_ be ? thisSymbolValue(_s_).""",
    """          1. Return _sym_.[[Description]].""",
  )
}
