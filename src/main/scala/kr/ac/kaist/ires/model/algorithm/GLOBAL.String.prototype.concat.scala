package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.String.prototype.concat` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.concat"""), List(Param("args", Variadic)))
  val ids = List(
    "sec-string.prototype.concat",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ToString O)
  |  1:let S = [? __x1__]
  |  2:let R = S
  |  3:let __x2__ = args
  |  3:let __x3__ = 0i
  |  3:while (< __x3__ __x2__.length) {
  |    let next = __x2__[__x3__]
  |    4:app __x4__ = (ToString next)
  |    4:let nextString = [? __x4__]
  |    5:R = (+ R nextString)
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  6:return R
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _R_ be _S_.""",
    """          1. For each element _next_ of _args_, do""",
    """            1. Let _nextString_ be ? ToString(_next_).""",
    """            1. Set _R_ to the string-concatenation of _R_ and _nextString_.""",
    """          1. Return _R_.""",
  )
}
