package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.RegExp.prototype[SYMBOL_replace]` extends Algo {
  val head = BuiltinHead(parseRef("""RegExp.prototype[SYMBOL_replace]"""), List(Param("string", Normal), Param("replaceValue", Normal)))
  val ids = List(
    "sec-regexp.prototype-@@replace",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let rx = this
  |  1:if (! (= (typeof rx) Object)) throw TypeError else 56:{}
  |  2:app __x0__ = (ToString string)
  |  2:let S = [? __x0__]
  |  3:let lengthS = S.length
  |  4:app __x1__ = (IsCallable replaceValue)
  |  4:let functionalReplace = __x1__
  |  5:if (= functionalReplace false) {
  |    6:app __x2__ = (ToString replaceValue)
  |    6:replaceValue = [? __x2__]
  |  } else 56:{}
  |  7:app __x3__ = (Get rx "global")
  |  7:app __x4__ = (ToBoolean [? __x3__])
  |  7:let global = [! __x4__]
  |  8:if (= global true) {
  |    9:app __x5__ = (Get rx "unicode")
  |    9:app __x6__ = (ToBoolean [? __x5__])
  |    9:let fullUnicode = [! __x6__]
  |    10:app __x7__ = (Set rx "lastIndex" 0i true)
  |    10:[? __x7__]
  |  } else 56:{}
  |  11:let results = (new [])
  |  12:let done = false
  |  13:while (= done false) {
  |    14:app __x8__ = (RegExpExec rx S)
  |    14:let result = [? __x8__]
  |    16:if (= result null) done = true else {
  |      17:append result -> results
  |      19:if (= global false) done = true else {
  |        20:app __x9__ = (Get result "0")
  |        20:app __x10__ = (ToString [? __x9__])
  |        20:let matchStr = [? __x10__]
  |        21:if (= matchStr "") {
  |          22:app __x11__ = (Get rx "lastIndex")
  |          22:app __x12__ = (ToLength [? __x11__])
  |          22:let thisIndex = [? __x12__]
  |          23:app __x13__ = (AdvanceStringIndex S thisIndex fullUnicode)
  |          23:let nextIndex = __x13__
  |          24:app __x14__ = (Set rx "lastIndex" nextIndex true)
  |          24:[? __x14__]
  |        } else 56:{}
  |      }
  |    }
  |  }
  |  25:let accumulatedResult = ""
  |  26:let nextSourcePosition = 0i
  |  27:let __x15__ = results
  |  27:let __x16__ = 0i
  |  27:while (< __x16__ __x15__.length) {
  |    let result = __x15__[__x16__]
  |    28:app __x17__ = (LengthOfArrayLike result)
  |    28:let resultLength = [? __x17__]
  |    29:app __x18__ = (max (- resultLength 1i) 0i)
  |    29:let nCaptures = __x18__
  |    30:app __x19__ = (Get result "0")
  |    30:app __x20__ = (ToString [? __x19__])
  |    30:let matched = [? __x20__]
  |    31:let matchLength = matched.length
  |    32:app __x21__ = (Get result "index")
  |    32:app __x22__ = (ToIntegerOrInfinity [? __x21__])
  |    32:let position = [? __x22__]
  |    33:??? "Set id:{position} to the result of clamping id:{position} between 0 and id:{lengthS} ."
  |    34:let n = 1i
  |    35:let captures = (new [])
  |    36:while (! (< nCaptures n)) {
  |      37:app __x23__ = (ToString n)
  |      37:app __x24__ = (Get result [! __x23__])
  |      37:let capN = [? __x24__]
  |      38:if (! (= capN undefined)) {
  |        39:app __x25__ = (ToString capN)
  |        39:capN = [? __x25__]
  |      } else 56:{}
  |      40:append capN -> captures
  |      41:n = (+ n 1i)
  |    }
  |    42:app __x26__ = (Get result "groups")
  |    42:let namedCaptures = [? __x26__]
  |    51:if (= functionalReplace true) {
  |      44:let replacerArgs = (new [matched])
  |      45:let __x27__ = captures
  |      45:let __x28__ = 0i
  |      45:while (< __x28__ __x27__.length) {
  |        let __x29__ = __x27__[__x28__]
  |        append __x29__ -> replacerArgs
  |        __x28__ = (+ __x28__ 1i)
  |      }
  |      46:append position -> replacerArgs
  |      46:append S -> replacerArgs
  |      47:if (! (= namedCaptures undefined)) append namedCaptures -> replacerArgs else 56:{}
  |      49:app __x30__ = (Call replaceValue undefined replacerArgs)
  |      49:let replValue = [? __x30__]
  |      50:app __x31__ = (ToString replValue)
  |      50:let replacement = [? __x31__]
  |    } else {
  |      52:if (! (= namedCaptures undefined)) {
  |        53:app __x32__ = (ToObject namedCaptures)
  |        53:namedCaptures = [? __x32__]
  |      } else 56:{}
  |      54:app __x33__ = (GetSubstitution matched S position captures namedCaptures replaceValue)
  |      54:let replacement = [? __x33__]
  |    }
  |    55:if (! (< position nextSourcePosition)) {
  |      57:let __x34__ = ""
  |      57:let __x35__ = nextSourcePosition
  |      57:while (< __x35__ (+ position 1i)) {
  |        access __x36__ = (S __x35__)
  |        __x34__ = (+ __x34__ __x36__)
  |        __x35__ = (+ __x35__ 1i)
  |      }
  |      57:accumulatedResult = (+ (+ accumulatedResult __x34__) replacement)
  |      58:nextSourcePosition = (+ position matchLength)
  |    } else 56:{}
  |    __x16__ = (+ __x16__ 1i)
  |  }
  |  59:if (! (< nextSourcePosition lengthS)) return accumulatedResult else 56:{}
  |  60:??? "Return the string - concatenation of id:{accumulatedResult} and the substring of id:{S} from id:{nextSourcePosition} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _rx_ be the *this* value.""",
    """          1. If Type(_rx_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _S_ be ? ToString(_string_).""",
    """          1. Let _lengthS_ be the number of code unit elements in _S_.""",
    """          1. Let _functionalReplace_ be IsCallable(_replaceValue_).""",
    """          1. If _functionalReplace_ is *false*, then""",
    """            1. Set _replaceValue_ to ? ToString(_replaceValue_).""",
    """          1. Let _global_ be ! ToBoolean(? Get(_rx_, *"global"*)).""",
    """          1. If _global_ is *true*, then""",
    """            1. Let _fullUnicode_ be ! ToBoolean(? Get(_rx_, *"unicode"*)).""",
    """            1. Perform ? Set(_rx_, *"lastIndex"*, *+0*<sub>𝔽</sub>, *true*).""",
    """          1. Let _results_ be a new empty List.""",
    """          1. Let _done_ be *false*.""",
    """          1. Repeat, while _done_ is *false*,""",
    """            1. Let _result_ be ? RegExpExec(_rx_, _S_).""",
    """            1. If _result_ is *null*, set _done_ to *true*.""",
    """            1. Else,""",
    """              1. Append _result_ to the end of _results_.""",
    """              1. If _global_ is *false*, set _done_ to *true*.""",
    """              1. Else,""",
    """                1. Let _matchStr_ be ? ToString(? Get(_result_, *"0"*)).""",
    """                1. If _matchStr_ is the empty String, then""",
    """                  1. Let _thisIndex_ be ℝ(? ToLength(? Get(_rx_, *"lastIndex"*))).""",
    """                  1. Let _nextIndex_ be AdvanceStringIndex(_S_, _thisIndex_, _fullUnicode_).""",
    """                  1. Perform ? Set(_rx_, *"lastIndex"*, 𝔽(_nextIndex_), *true*).""",
    """          1. Let _accumulatedResult_ be the empty String.""",
    """          1. Let _nextSourcePosition_ be 0.""",
    """          1. For each element _result_ of _results_, do""",
    """            1. Let _resultLength_ be ? LengthOfArrayLike(_result_).""",
    """            1. Let _nCaptures_ be max(_resultLength_ - 1, 0).""",
    """            1. Let _matched_ be ? ToString(? Get(_result_, *"0"*)).""",
    """            1. Let _matchLength_ be the number of code units in _matched_.""",
    """            1. Let _position_ be ? ToIntegerOrInfinity(? Get(_result_, *"index"*)).""",
    """            1. Set _position_ to the result of clamping _position_ between 0 and _lengthS_.""",
    """            1. Let _n_ be 1.""",
    """            1. Let _captures_ be a new empty List.""",
    """            1. Repeat, while _n_ ≤ _nCaptures_,""",
    """              1. Let _capN_ be ? Get(_result_, ! ToString(𝔽(_n_))).""",
    """              1. If _capN_ is not *undefined*, then""",
    """                1. Set _capN_ to ? ToString(_capN_).""",
    """              1. Append _capN_ as the last element of _captures_.""",
    """              1. Set _n_ to _n_ + 1.""",
    """            1. Let _namedCaptures_ be ? Get(_result_, *"groups"*).""",
    """            1. If _functionalReplace_ is *true*, then""",
    """              1. Let _replacerArgs_ be « _matched_ ».""",
    """              1. Append in List order the elements of _captures_ to the end of the List _replacerArgs_.""",
    """              1. Append 𝔽(_position_) and _S_ to _replacerArgs_.""",
    """              1. If _namedCaptures_ is not *undefined*, then""",
    """                1. Append _namedCaptures_ as the last element of _replacerArgs_.""",
    """              1. Let _replValue_ be ? Call(_replaceValue_, *undefined*, _replacerArgs_).""",
    """              1. Let _replacement_ be ? ToString(_replValue_).""",
    """            1. Else,""",
    """              1. If _namedCaptures_ is not *undefined*, then""",
    """                1. Set _namedCaptures_ to ? ToObject(_namedCaptures_).""",
    """              1. Let _replacement_ be ? GetSubstitution(_matched_, _S_, _position_, _captures_, _namedCaptures_, _replaceValue_).""",
    """            1. If _position_ ≥ _nextSourcePosition_, then""",
    """              1. NOTE: _position_ should not normally move backwards. If it does, it is an indication of an ill-behaving RegExp subclass or use of an access triggered side-effect to change the global flag or other characteristics of _rx_. In such cases, the corresponding substitution is ignored.""",
    """              1. Set _accumulatedResult_ to the string-concatenation of _accumulatedResult_, the substring of _S_ from _nextSourcePosition_ to _position_, and _replacement_.""",
    """              1. Set _nextSourcePosition_ to _position_ + _matchLength_.""",
    """          1. If _nextSourcePosition_ ≥ _lengthS_, return _accumulatedResult_.""",
    """          1. Return the string-concatenation of _accumulatedResult_ and the substring of _S_ from _nextSourcePosition_.""",
  )
}
