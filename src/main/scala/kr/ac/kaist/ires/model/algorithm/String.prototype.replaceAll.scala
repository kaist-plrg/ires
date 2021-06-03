package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.replaceAll` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.replaceAll"""), List(Param("searchValue", Normal), Param("replaceValue", Normal)))
  val ids = List(
    "sec-string.prototype.replaceall",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:if (! (|| (= searchValue undefined) (= searchValue null))) {
  |    2:app __x1__ = (IsRegExp searchValue)
  |    2:let isRegExp = [? __x1__]
  |    3:if (= isRegExp true) {
  |      4:app __x2__ = (Get searchValue "flags")
  |      4:let flags = [? __x2__]
  |      5:app __x3__ = (RequireObjectCoercible flags)
  |      5:[? __x3__]
  |      6:app __x4__ = (ToString flags)
  |      6:if (! (contains [? __x4__] "g")) throw TypeError else 5:{}
  |    } else 5:{}
  |    7:app __x5__ = (GetMethod searchValue SYMBOL_replace)
  |    7:let replacer = [? __x5__]
  |    8:if (! (= replacer undefined)) {
  |      9:app __x6__ = (Call replacer searchValue (new [O, replaceValue]))
  |      9:return [? __x6__]
  |    } else 5:{}
  |  } else 5:{}
  |  10:app __x7__ = (ToString O)
  |  10:let string = [? __x7__]
  |  11:app __x8__ = (ToString searchValue)
  |  11:let searchString = [? __x8__]
  |  12:app __x9__ = (IsCallable replaceValue)
  |  12:let functionalReplace = __x9__
  |  13:if (= functionalReplace false) {
  |    14:app __x10__ = (ToString replaceValue)
  |    14:replaceValue = [? __x10__]
  |  } else 5:{}
  |  15:let searchLength = searchString.length
  |  16:app __x11__ = (max 1i searchLength)
  |  16:let advanceBy = __x11__
  |  17:let matchPositions = (new [])
  |  18:app __x12__ = (StringIndexOf string searchString 0i)
  |  18:let position = [! __x12__]
  |  19:while (! (= position -1i)) {
  |    20:append position -> matchPositions
  |    21:app __x13__ = (StringIndexOf string searchString (+ position advanceBy))
  |    21:position = [! __x13__]
  |  }
  |  22:let endOfLastMatch = 0i
  |  23:let result = ""
  |  24:let __x14__ = matchPositions
  |  24:let __x15__ = 0i
  |  24:while (< __x15__ __x14__.length) {
  |    let p = __x14__[__x15__]
  |    25:let __x16__ = ""
  |    25:let __x17__ = endOfLastMatch
  |    25:while (< __x17__ (+ p 1i)) {
  |      access __x18__ = (string __x17__)
  |      __x16__ = (+ __x16__ __x18__)
  |      __x17__ = (+ __x17__ 1i)
  |    }
  |    25:let preserved = __x16__
  |    28:if (= functionalReplace true) {
  |      27:app __x19__ = (Call replaceValue undefined (new [searchString, p, string]))
  |      27:app __x20__ = (ToString [? __x19__])
  |      27:let replacement = [? __x20__]
  |    } else {
  |      29:assert (= (typeof replaceValue) String)
  |      30:let captures = (new [])
  |      31:app __x21__ = (GetSubstitution searchString string p captures undefined replaceValue)
  |      31:let replacement = [! __x21__]
  |    }
  |    32:result = (+ (+ result preserved) replacement)
  |    33:endOfLastMatch = (+ p searchLength)
  |    __x15__ = (+ __x15__ 1i)
  |  }
  |  34:if (< endOfLastMatch string.length) ??? "Set id:{result} to the string - concatenation of id:{result} and the substring of id:{string} from id:{endOfLastMatch} ." else 5:{}
  |  36:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. If _searchValue_ is neither *undefined* nor *null*, then""",
    """            1. Let _isRegExp_ be ? IsRegExp(_searchValue_).""",
    """            1. If _isRegExp_ is *true*, then""",
    """              1. Let _flags_ be ? Get(_searchValue_, *"flags"*).""",
    """              1. Perform ? RequireObjectCoercible(_flags_).""",
    """              1. If ? ToString(_flags_) does not contain *"g"*, throw a *TypeError* exception.""",
    """            1. Let _replacer_ be ? GetMethod(_searchValue_, @@replace).""",
    """            1. If _replacer_ is not *undefined*, then""",
    """              1. Return ? Call(_replacer_, _searchValue_, « _O_, _replaceValue_ »).""",
    """          1. Let _string_ be ? ToString(_O_).""",
    """          1. Let _searchString_ be ? ToString(_searchValue_).""",
    """          1. Let _functionalReplace_ be IsCallable(_replaceValue_).""",
    """          1. If _functionalReplace_ is *false*, then""",
    """            1. Set _replaceValue_ to ? ToString(_replaceValue_).""",
    """          1. Let _searchLength_ be the length of _searchString_.""",
    """          1. Let _advanceBy_ be max(1, _searchLength_).""",
    """          1. Let _matchPositions_ be a new empty List.""",
    """          1. Let _position_ be ! StringIndexOf(_string_, _searchString_, 0).""",
    """          1. Repeat, while _position_ is not -1,""",
    """            1. Append _position_ to the end of _matchPositions_.""",
    """            1. Set _position_ to ! StringIndexOf(_string_, _searchString_, _position_ + _advanceBy_).""",
    """          1. Let _endOfLastMatch_ be 0.""",
    """          1. Let _result_ be the empty String.""",
    """          1. For each element _p_ of _matchPositions_, do""",
    """            1. Let _preserved_ be the substring of _string_ from _endOfLastMatch_ to _p_.""",
    """            1. If _functionalReplace_ is *true*, then""",
    """              1. Let _replacement_ be ? ToString(? Call(_replaceValue_, *undefined*, « _searchString_, 𝔽(_p_), _string_ »)).""",
    """            1. Else,""",
    """              1. Assert: Type(_replaceValue_) is String.""",
    """              1. Let _captures_ be a new empty List.""",
    """              1. Let _replacement_ be ! GetSubstitution(_searchString_, _string_, _p_, _captures_, *undefined*, _replaceValue_).""",
    """            1. Set _result_ to the string-concatenation of _result_, _preserved_, and _replacement_.""",
    """            1. Set _endOfLastMatch_ to _p_ + _searchLength_.""",
    """          1. If _endOfLastMatch_ < the length of _string_, then""",
    """            1. Set _result_ to the string-concatenation of _result_ and the substring of _string_ from _endOfLastMatch_.""",
    """          1. Return _result_.""",
  )
}
