package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.RegExp.prototype[SYMBOL_match]` extends Algo {
  val head = BuiltinHead(parseRef("""RegExp.prototype[SYMBOL_match]"""), List(Param("string", Normal)))
  val ids = List(
    "sec-regexp.prototype-@@match",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let rx = this
  |  1:if (! (= (typeof rx) Object)) throw TypeError else 52:{}
  |  2:app __x0__ = (ToString string)
  |  2:let S = [? __x0__]
  |  3:app __x1__ = (Get rx "global")
  |  3:app __x2__ = (ToBoolean [? __x1__])
  |  3:let global = [! __x2__]
  |  6:if (= global false) {
  |    5:app __x3__ = (RegExpExec rx S)
  |    5:return [? __x3__]
  |  } else {
  |    7:assert (= global true)
  |    8:app __x4__ = (Get rx "unicode")
  |    8:app __x5__ = (ToBoolean [? __x4__])
  |    8:let fullUnicode = [! __x5__]
  |    9:app __x6__ = (Set rx "lastIndex" 0i true)
  |    9:[? __x6__]
  |    10:app __x7__ = (ArrayCreate 0i)
  |    10:let A = [! __x7__]
  |    11:let n = 0i
  |    12:while true {
  |      13:app __x8__ = (RegExpExec rx S)
  |      13:let result = [? __x8__]
  |      17:if (= result null) {
  |        15:if (== n 0i) return null else 52:{}
  |        16:return A
  |      } else {
  |        18:app __x9__ = (Get result "0")
  |        18:app __x10__ = (ToString [? __x9__])
  |        18:let matchStr = [? __x10__]
  |        19:app __x11__ = (ToString n)
  |        19:app __x12__ = (CreateDataPropertyOrThrow A [! __x11__] matchStr)
  |        19:[! __x12__]
  |        20:if (= matchStr "") {
  |          21:app __x13__ = (Get rx "lastIndex")
  |          21:app __x14__ = (ToLength [? __x13__])
  |          21:let thisIndex = [? __x14__]
  |          22:app __x15__ = (AdvanceStringIndex S thisIndex fullUnicode)
  |          22:let nextIndex = __x15__
  |          23:app __x16__ = (Set rx "lastIndex" nextIndex true)
  |          23:[? __x16__]
  |        } else 52:{}
  |        24:n = (+ n 1i)
  |      }
  |    }
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _rx_ be the *this* value.""",
    """          1. If Type(_rx_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _S_ be ? ToString(_string_).""",
    """          1. Let _global_ be ! ToBoolean(? Get(_rx_, *"global"*)).""",
    """          1. If _global_ is *false*, then""",
    """            1. Return ? RegExpExec(_rx_, _S_).""",
    """          1. Else,""",
    """            1. Assert: _global_ is *true*.""",
    """            1. Let _fullUnicode_ be ! ToBoolean(? Get(_rx_, *"unicode"*)).""",
    """            1. Perform ? Set(_rx_, *"lastIndex"*, *+0*<sub>𝔽</sub>, *true*).""",
    """            1. Let _A_ be ! ArrayCreate(0).""",
    """            1. Let _n_ be 0.""",
    """            1. Repeat,""",
    """              1. Let _result_ be ? RegExpExec(_rx_, _S_).""",
    """              1. If _result_ is *null*, then""",
    """                1. If _n_ = 0, return *null*.""",
    """                1. Return _A_.""",
    """              1. Else,""",
    """                1. Let _matchStr_ be ? ToString(? Get(_result_, *"0"*)).""",
    """                1. Perform ! CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_n_)), _matchStr_).""",
    """                1. If _matchStr_ is the empty String, then""",
    """                  1. Let _thisIndex_ be ℝ(? ToLength(? Get(_rx_, *"lastIndex"*))).""",
    """                  1. Let _nextIndex_ be AdvanceStringIndex(_S_, _thisIndex_, _fullUnicode_).""",
    """                  1. Perform ? Set(_rx_, *"lastIndex"*, 𝔽(_nextIndex_), *true*).""",
    """                1. Set _n_ to _n_ + 1.""",
  )
}
