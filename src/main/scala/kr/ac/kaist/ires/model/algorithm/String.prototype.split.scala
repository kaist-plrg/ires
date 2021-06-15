package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.split` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.split"""), List(Param("separator", Normal), Param("limit", Normal)))
  val ids = List(
    "sec-string.prototype.split",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:if (! (|| (= separator undefined) (= separator null))) {
  |    2:app __x1__ = (GetMethod separator SYMBOL_split)
  |    2:let splitter = [? __x1__]
  |    3:if (! (= splitter undefined)) {
  |      4:app __x2__ = (Call splitter separator (new [O, limit]))
  |      4:return [? __x2__]
  |    } else 25:{}
  |  } else 25:{}
  |  5:app __x3__ = (ToString O)
  |  5:let S = [? __x3__]
  |  6:app __x4__ = (ArrayCreate 0i)
  |  6:let A = [! __x4__]
  |  7:let lengthA = 0i
  |  8:if (= limit undefined) let lim = (- (** 2.0 32i) 1i) else {
  |    app __x5__ = (ToUint32 limit)
  |    let lim = [? __x5__]
  |  }
  |  9:app __x6__ = (ToString separator)
  |  9:let R = [? __x6__]
  |  10:if (== lim 0i) return A else 25:{}
  |  11:if (= separator undefined) {
  |    12:app __x7__ = (CreateDataPropertyOrThrow A "0" S)
  |    12:[! __x7__]
  |    13:return A
  |  } else 25:{}
  |  14:let s = S.length
  |  15:if (== s 0i) {
  |    16:if (! (= R "")) {
  |      17:app __x8__ = (CreateDataPropertyOrThrow A "0" S)
  |      17:[! __x8__]
  |    } else 25:{}
  |    18:return A
  |  } else 25:{}
  |  19:let p = 0i
  |  20:let q = p
  |  21:while (! (== q s)) {
  |    22:app __x9__ = (SplitMatch S q R)
  |    22:let e = __x9__
  |    24:if (= e CONST_notDASHmatched) q = (+ q 1i) else if (== e p) q = (+ q 1i) else {
  |      28:let __x10__ = ""
  |      28:let __x11__ = p
  |      28:while (< __x11__ (+ q 1i)) {
  |        access __x12__ = (S __x11__)
  |        __x10__ = (+ __x10__ __x12__)
  |        __x11__ = (+ __x11__ 1i)
  |      }
  |      28:let T = __x10__
  |      29:app __x13__ = (ToString lengthA)
  |      29:app __x14__ = (CreateDataPropertyOrThrow A [! __x13__] T)
  |      29:[! __x14__]
  |      30:lengthA = (+ lengthA 1i)
  |      31:if (== lengthA lim) return A else 25:{}
  |      32:p = e
  |      33:q = p
  |    }
  |  }
  |  34:let __x15__ = ""
  |  34:let __x16__ = p
  |  34:while (< __x16__ (+ s 1i)) {
  |    access __x17__ = (S __x16__)
  |    __x15__ = (+ __x15__ __x17__)
  |    __x16__ = (+ __x16__ 1i)
  |  }
  |  34:let T = __x15__
  |  35:app __x18__ = (ToString lengthA)
  |  35:app __x19__ = (CreateDataPropertyOrThrow A [! __x18__] T)
  |  35:[! __x19__]
  |  36:return A
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. If _separator_ is neither *undefined* nor *null*, then""",
    """            1. Let _splitter_ be ? GetMethod(_separator_, @@split).""",
    """            1. If _splitter_ is not *undefined*, then""",
    """              1. Return ? Call(_splitter_, _separator_, « _O_, _limit_ »).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _A_ be ! ArrayCreate(0).""",
    """          1. Let _lengthA_ be 0.""",
    """          1. If _limit_ is *undefined*, let _lim_ be 2<sup>32</sup> - 1; else let _lim_ be ℝ(? ToUint32(_limit_)).""",
    """          1. Let _R_ be ? ToString(_separator_).""",
    """          1. If _lim_ = 0, return _A_.""",
    """          1. If _separator_ is *undefined*, then""",
    """            1. Perform ! CreateDataPropertyOrThrow(_A_, *"0"*, _S_).""",
    """            1. Return _A_.""",
    """          1. Let _s_ be the length of _S_.""",
    """          1. If _s_ = 0, then""",
    """            1. If _R_ is not the empty String, then""",
    """              1. Perform ! CreateDataPropertyOrThrow(_A_, *"0"*, _S_).""",
    """            1. Return _A_.""",
    """          1. Let _p_ be 0.""",
    """          1. Let _q_ be _p_.""",
    """          1. Repeat, while _q_ ≠ _s_,""",
    """            1. Let _e_ be SplitMatch(_S_, _q_, _R_).""",
    """            1. If _e_ is ~not-matched~, set _q_ to _q_ + 1.""",
    """            1. Else,""",
    """              1. Assert: _e_ is a non-negative integer ≤ _s_.""",
    """              1. If _e_ = _p_, set _q_ to _q_ + 1.""",
    """              1. Else,""",
    """                1. Let _T_ be the substring of _S_ from _p_ to _q_.""",
    """                1. Perform ! CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_lengthA_)), _T_).""",
    """                1. Set _lengthA_ to _lengthA_ + 1.""",
    """                1. If _lengthA_ = _lim_, return _A_.""",
    """                1. Set _p_ to _e_.""",
    """                1. Set _q_ to _p_.""",
    """          1. Let _T_ be the substring of _S_ from _p_ to _s_.""",
    """          1. Perform ! CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_lengthA_)), _T_).""",
    """          1. Return _A_.""",
  )
}
