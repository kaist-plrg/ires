package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Symbol.for` extends Algo {
  val head = BuiltinHead(parseRef("""Symbol.for"""), List(Param("key", Normal)))
  val ids = List(
    "sec-symbol.for",
    "sec-properties-of-the-symbol-constructor",
    "sec-symbol-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToString key)
  |  0:let stringKey = [? __x0__]
  |  1:let __x1__ = SYMBOL_REGISTRY
  |  1:let __x2__ = 0i
  |  1:while (< __x2__ __x1__.length) {
  |    let e = __x1__[__x2__]
  |    2:app __x3__ = (SameValue e.Key stringKey)
  |    2:if (= __x3__ true) return e.Symbol else 3:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  4:??? "Let id:{newSymbol} be a new unique Symbol value whose [ [ Description ] ] value is id:{stringKey} ."
  |  5:append (new Record("Key" -> stringKey, "Symbol" -> newSymbol)) -> SYMBOL_REGISTRY
  |  6:return newSymbol
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _stringKey_ be ? ToString(_key_).""",
    """          1. For each element _e_ of the GlobalSymbolRegistry List, do""",
    """            1. If SameValue(_e_.[[Key]], _stringKey_) is *true*, return _e_.[[Symbol]].""",
    """          1. Assert: GlobalSymbolRegistry does not currently contain an entry for _stringKey_.""",
    """          1. Let _newSymbol_ be a new unique Symbol value whose [[Description]] value is _stringKey_.""",
    """          1. Append the Record { [[Key]]: _stringKey_, [[Symbol]]: _newSymbol_ } to the GlobalSymbolRegistry List.""",
    """          1. Return _newSymbol_.""",
  )
}
