package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.RegExp.prototype[SYMBOL_search]` extends Algo {
  val head = BuiltinHead(parseRef("""RegExp.prototype[SYMBOL_search]"""), List(Param("string", Normal)))
  val ids = List(
    "sec-regexp.prototype-@@search",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let rx = this
  |  1:if (! (= (typeof rx) Object)) throw TypeError else 56:{}
  |  2:app __x0__ = (ToString string)
  |  2:let S = [? __x0__]
  |  3:app __x1__ = (Get rx "lastIndex")
  |  3:let previousLastIndex = [? __x1__]
  |  4:app __x2__ = (SameValue previousLastIndex 0i)
  |  4:if (= __x2__ false) {
  |    5:app __x3__ = (Set rx "lastIndex" 0i true)
  |    5:[? __x3__]
  |  } else 56:{}
  |  6:app __x4__ = (RegExpExec rx S)
  |  6:let result = [? __x4__]
  |  7:app __x5__ = (Get rx "lastIndex")
  |  7:let currentLastIndex = [? __x5__]
  |  8:app __x6__ = (SameValue currentLastIndex previousLastIndex)
  |  8:if (= __x6__ false) {
  |    9:app __x7__ = (Set rx "lastIndex" previousLastIndex true)
  |    9:[? __x7__]
  |  } else 56:{}
  |  10:if (= result null) return -1i else 56:{}
  |  11:app __x8__ = (Get result "index")
  |  11:return [? __x8__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _rx_ be the *this* value.""",
    """          1. If Type(_rx_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _S_ be ? ToString(_string_).""",
    """          1. Let _previousLastIndex_ be ? Get(_rx_, *"lastIndex"*).""",
    """          1. If SameValue(_previousLastIndex_, *+0*<sub>𝔽</sub>) is *false*, then""",
    """            1. Perform ? Set(_rx_, *"lastIndex"*, *+0*<sub>𝔽</sub>, *true*).""",
    """          1. Let _result_ be ? RegExpExec(_rx_, _S_).""",
    """          1. Let _currentLastIndex_ be ? Get(_rx_, *"lastIndex"*).""",
    """          1. If SameValue(_currentLastIndex_, _previousLastIndex_) is *false*, then""",
    """            1. Perform ? Set(_rx_, *"lastIndex"*, _previousLastIndex_, *true*).""",
    """          1. If _result_ is *null*, return *-1*<sub>𝔽</sub>.""",
    """          1. Return ? Get(_result_, *"index"*).""",
  )
}
