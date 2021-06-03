package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Symbol.keyFor` extends Algo {
  val head = BuiltinHead(parseRef("""Symbol.keyFor"""), List(Param("sym", Normal)))
  val ids = List(
    "sec-symbol.keyfor",
    "sec-properties-of-the-symbol-constructor",
    "sec-symbol-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof sym) Symbol)) throw TypeError else 3:{}
  |  1:let __x0__ = SYMBOL_REGISTRY
  |  1:let __x1__ = 0i
  |  1:while (< __x1__ __x0__.length) {
  |    let e = __x0__[__x1__]
  |    2:app __x2__ = (SameValue e.Symbol sym)
  |    2:if (= __x2__ true) return e.Key else 3:{}
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  4:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_sym_) is not Symbol, throw a *TypeError* exception.""",
    """          1. For each element _e_ of the GlobalSymbolRegistry List (see <emu-xref href="#sec-symbol.for"></emu-xref>), do""",
    """            1. If SameValue(_e_.[[Symbol]], _sym_) is *true*, return _e_.[[Key]].""",
    """          1. Assert: GlobalSymbolRegistry does not currently contain an entry for _sym_.""",
    """          1. Return *undefined*.""",
  )
}
