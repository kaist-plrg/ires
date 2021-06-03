package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Symbol.prototype.toString` extends Algo {
  val head = BuiltinHead(parseRef("""Symbol.prototype.toString"""), List())
  val ids = List(
    "sec-symbol.prototype.tostring",
    "sec-properties-of-the-symbol-prototype-object",
    "sec-symbol-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisSymbolValue this)
  |  0:let sym = [? __x0__]
  |  1:app __x1__ = (SymbolDescriptiveString sym)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _sym_ be ? thisSymbolValue(*this* value).""",
    """          1. Return SymbolDescriptiveString(_sym_).""",
  )
}
