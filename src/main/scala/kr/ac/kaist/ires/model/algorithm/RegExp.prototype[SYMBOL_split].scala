package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RegExp.prototype[SYMBOL_split]` extends Algo {
  val head = BuiltinHead(parseRef("""RegExp.prototype[SYMBOL_split]"""), List(Param("string", Normal), Param("limit", Normal)))
  val ids = List(
    "sec-regexp.prototype-@@split",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let rx = this
  |  1:if (! (= (typeof rx) Object)) throw TypeError else 56:{}
  |  2:app __x0__ = (ToString string)
  |  2:let S = [? __x0__]
  |  3:app __x1__ = (SpeciesConstructor rx INTRINSIC_RegExp)
  |  3:let C = [? __x1__]
  |  4:app __x2__ = (Get rx "flags")
  |  4:app __x3__ = (ToString [? __x2__])
  |  4:let flags = [? __x3__]
  |  6:if (contains flags "u") let unicodeMatching = true else let unicodeMatching = false
  |  8:if (contains flags "y") let newFlags = flags else let newFlags = (+ flags "y")
  |  9:app __x4__ = (Construct C (new [rx, newFlags]))
  |  9:let splitter = [? __x4__]
  |  10:app __x5__ = (ArrayCreate 0i)
  |  10:let A = [! __x5__]
  |  11:let lengthA = 0i
  |  12:if (= limit undefined) let lim = (- (** 2.0 32i) 1i) else {
  |    app __x6__ = (ToUint32 limit)
  |    let lim = [? __x6__]
  |  }
  |  13:if (= lim 0i) return A else 56:{}
  |  14:let size = S.length
  |  15:if (= size 0i) {
  |    16:app __x7__ = (RegExpExec splitter S)
  |    16:let z = [? __x7__]
  |    17:if (! (= z null)) return A else 56:{}
  |    18:app __x8__ = (CreateDataPropertyOrThrow A "0" S)
  |    18:[! __x8__]
  |    19:return A
  |  } else 56:{}
  |  20:let p = 0i
  |  21:let q = p
  |  22:while (< q size) {
  |    23:app __x9__ = (Set splitter "lastIndex" q true)
  |    23:[? __x9__]
  |    24:app __x10__ = (RegExpExec splitter S)
  |    24:let z = [? __x10__]
  |    26:if (= z null) {
  |      app __x11__ = (AdvanceStringIndex S q unicodeMatching)
  |      q = __x11__
  |    } else {
  |      27:app __x12__ = (Get splitter "lastIndex")
  |      27:app __x13__ = (ToLength [? __x12__])
  |      27:let e = [? __x13__]
  |      28:app __x14__ = (min e size)
  |      28:e = __x14__
  |      30:if (== e p) {
  |        app __x15__ = (AdvanceStringIndex S q unicodeMatching)
  |        q = __x15__
  |      } else {
  |        31:let __x16__ = ""
  |        31:let __x17__ = p
  |        31:while (< __x17__ (+ q 1i)) {
  |          access __x18__ = (S __x17__)
  |          __x16__ = (+ __x16__ __x18__)
  |          __x17__ = (+ __x17__ 1i)
  |        }
  |        31:let T = __x16__
  |        32:app __x19__ = (ToString lengthA)
  |        32:app __x20__ = (CreateDataPropertyOrThrow A [! __x19__] T)
  |        32:[! __x20__]
  |        33:lengthA = (+ lengthA 1i)
  |        34:if (== lengthA lim) return A else 56:{}
  |        35:p = e
  |        36:app __x21__ = (LengthOfArrayLike z)
  |        36:let numberOfCaptures = [? __x21__]
  |        37:app __x22__ = (max (- numberOfCaptures 1i) 0i)
  |        37:numberOfCaptures = __x22__
  |        38:let i = 1i
  |        39:while (! (< numberOfCaptures i)) {
  |          40:app __x23__ = (ToString i)
  |          40:app __x24__ = (Get z [! __x23__])
  |          40:let nextCapture = [? __x24__]
  |          41:app __x25__ = (ToString lengthA)
  |          41:app __x26__ = (CreateDataPropertyOrThrow A [! __x25__] nextCapture)
  |          41:[! __x26__]
  |          42:i = (+ i 1i)
  |          43:lengthA = (+ lengthA 1i)
  |          44:if (== lengthA lim) return A else 56:{}
  |        }
  |        45:q = p
  |      }
  |    }
  |  }
  |  46:let __x27__ = ""
  |  46:let __x28__ = p
  |  46:while (< __x28__ (+ size 1i)) {
  |    access __x29__ = (S __x28__)
  |    __x27__ = (+ __x27__ __x29__)
  |    __x28__ = (+ __x28__ 1i)
  |  }
  |  46:let T = __x27__
  |  47:app __x30__ = (ToString lengthA)
  |  47:app __x31__ = (CreateDataPropertyOrThrow A [! __x30__] T)
  |  47:[! __x31__]
  |  48:return A
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _rx_ be the *this* value.""",
    """          1. If Type(_rx_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _S_ be ? ToString(_string_).""",
    """          1. Let _C_ be ? SpeciesConstructor(_rx_, %RegExp%).""",
    """          1. Let _flags_ be ? ToString(? Get(_rx_, *"flags"*)).""",
    """          1. If _flags_ contains *"u"*, let _unicodeMatching_ be *true*.""",
    """          1. Else, let _unicodeMatching_ be *false*.""",
    """          1. If _flags_ contains *"y"*, let _newFlags_ be _flags_.""",
    """          1. Else, let _newFlags_ be the string-concatenation of _flags_ and *"y"*.""",
    """          1. Let _splitter_ be ? Construct(_C_, « _rx_, _newFlags_ »).""",
    """          1. Let _A_ be ! ArrayCreate(0).""",
    """          1. Let _lengthA_ be 0.""",
    """          1. If _limit_ is *undefined*, let _lim_ be 2<sup>32</sup> - 1; else let _lim_ be ℝ(? ToUint32(_limit_)).""",
    """          1. If _lim_ is 0, return _A_.""",
    """          1. Let _size_ be the length of _S_.""",
    """          1. If _size_ is 0, then""",
    """            1. Let _z_ be ? RegExpExec(_splitter_, _S_).""",
    """            1. If _z_ is not *null*, return _A_.""",
    """            1. Perform ! CreateDataPropertyOrThrow(_A_, *"0"*, _S_).""",
    """            1. Return _A_.""",
    """          1. Let _p_ be 0.""",
    """          1. Let _q_ be _p_.""",
    """          1. Repeat, while _q_ < _size_,""",
    """            1. Perform ? Set(_splitter_, *"lastIndex"*, 𝔽(_q_), *true*).""",
    """            1. Let _z_ be ? RegExpExec(_splitter_, _S_).""",
    """            1. If _z_ is *null*, set _q_ to AdvanceStringIndex(_S_, _q_, _unicodeMatching_).""",
    """            1. Else,""",
    """              1. Let _e_ be ℝ(? ToLength(? Get(_splitter_, *"lastIndex"*))).""",
    """              1. Set _e_ to min(_e_, _size_).""",
    """              1. If _e_ = _p_, set _q_ to AdvanceStringIndex(_S_, _q_, _unicodeMatching_).""",
    """              1. Else,""",
    """                1. Let _T_ be the substring of _S_ from _p_ to _q_.""",
    """                1. Perform ! CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_lengthA_)), _T_).""",
    """                1. Set _lengthA_ to _lengthA_ + 1.""",
    """                1. If _lengthA_ = _lim_, return _A_.""",
    """                1. Set _p_ to _e_.""",
    """                1. Let _numberOfCaptures_ be ? LengthOfArrayLike(_z_).""",
    """                1. Set _numberOfCaptures_ to max(_numberOfCaptures_ - 1, 0).""",
    """                1. Let _i_ be 1.""",
    """                1. Repeat, while _i_ ≤ _numberOfCaptures_,""",
    """                  1. Let _nextCapture_ be ? Get(_z_, ! ToString(𝔽(_i_))).""",
    """                  1. Perform ! CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_lengthA_)), _nextCapture_).""",
    """                  1. Set _i_ to _i_ + 1.""",
    """                  1. Set _lengthA_ to _lengthA_ + 1.""",
    """                  1. If _lengthA_ = _lim_, return _A_.""",
    """                1. Set _q_ to _p_.""",
    """          1. Let _T_ be the substring of _S_ from _p_ to _size_.""",
    """          1. Perform ! CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_lengthA_)), _T_).""",
    """          1. Return _A_.""",
  )
}
