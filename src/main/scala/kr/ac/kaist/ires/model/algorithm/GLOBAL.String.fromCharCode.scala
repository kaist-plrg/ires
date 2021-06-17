package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.String.fromCharCode` extends Algo {
  val head = BuiltinHead(parseRef("""String.fromCharCode"""), List(Param("codeUnits", Variadic)))
  val ids = List(
    "sec-string.fromcharcode",
    "sec-properties-of-the-string-constructor",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let length = codeUnits.length
  |  1:let elements = (new [])
  |  2:let __x0__ = codeUnits
  |  2:let __x1__ = 0i
  |  2:while (< __x1__ __x0__.length) {
  |    let next = __x0__[__x1__]
  |    3:app __x2__ = (ToUint16 next)
  |    3:let nextCU = [? __x2__]
  |    4:append nextCU -> elements
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  5:??? "Return the String value whose code units are the elements in the List id:{elements} . If id:{codeUnits} is empty , the empty String is returned ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _length_ be the number of elements in _codeUnits_.""",
    """          1. Let _elements_ be a new empty List.""",
    """          1. For each element _next_ of _codeUnits_, do""",
    """            1. Let _nextCU_ be ℝ(? ToUint16(_next_)).""",
    """            1. Append _nextCU_ to the end of _elements_.""",
    """          1. Return the String value whose code units are the elements in the List _elements_. If _codeUnits_ is empty, the empty String is returned.""",
  )
}
