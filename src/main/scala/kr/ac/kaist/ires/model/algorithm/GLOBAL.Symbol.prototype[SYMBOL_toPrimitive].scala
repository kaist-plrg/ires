package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Symbol.prototype[SYMBOL_toPrimitive]` extends Algo {
  val head = BuiltinHead(parseRef("""Symbol.prototype[SYMBOL_toPrimitive]"""), List(Param("hint", Normal)))
  val ids = List(
    "sec-symbol.prototype-@@toprimitive",
    "sec-properties-of-the-symbol-prototype-object",
    "sec-symbol-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisSymbolValue this)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? thisSymbolValue(*this* value).""",
  )
}
