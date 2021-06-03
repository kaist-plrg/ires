package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.replace` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.replace"""), List(Param("searchValue", Normal), Param("replaceValue", Normal)))
  val ids = List(
    "sec-string.prototype.replace",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:if (! (|| (= searchValue undefined) (= searchValue null))) {
  |    2:app __x1__ = (GetMethod searchValue SYMBOL_replace)
  |    2:let replacer = [? __x1__]
  |    3:if (! (= replacer undefined)) {
  |      4:app __x2__ = (Call replacer searchValue (new [O, replaceValue]))
  |      4:return [? __x2__]
  |    } else 4:{}
  |  } else 4:{}
  |  5:app __x3__ = (ToString O)
  |  5:let string = [? __x3__]
  |  6:app __x4__ = (ToString searchValue)
  |  6:let searchString = [? __x4__]
  |  7:app __x5__ = (IsCallable replaceValue)
  |  7:let functionalReplace = __x5__
  |  8:if (= functionalReplace false) {
  |    9:app __x6__ = (ToString replaceValue)
  |    9:replaceValue = [? __x6__]
  |  } else 4:{}
  |  10:let searchLength = searchString.length
  |  11:app __x7__ = (StringIndexOf string searchString 0i)
  |  11:let position = [! __x7__]
  |  12:if (= position -1i) return string else 4:{}
  |  13:let __x8__ = ""
  |  13:let __x9__ = 0i
  |  13:while (< __x9__ (+ position 1i)) {
  |    access __x10__ = (string __x9__)
  |    __x8__ = (+ __x8__ __x10__)
  |    __x9__ = (+ __x9__ 1i)
  |  }
  |  13:let preserved = __x8__
  |  16:if (= functionalReplace true) {
  |    15:app __x11__ = (Call replaceValue undefined (new [searchString, position, string]))
  |    15:app __x12__ = (ToString [? __x11__])
  |    15:let replacement = [? __x12__]
  |  } else {
  |    17:assert (= (typeof replaceValue) String)
  |    18:let captures = (new [])
  |    19:app __x13__ = (GetSubstitution searchString string position captures undefined replaceValue)
  |    19:let replacement = [! __x13__]
  |  }
  |  20:??? "Return the string - concatenation of id:{preserved} , id:{replacement} , and the substring of id:{string} from id:{position} + id:{searchLength} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. If _searchValue_ is neither *undefined* nor *null*, then""",
    """            1. Let _replacer_ be ? GetMethod(_searchValue_, @@replace).""",
    """            1. If _replacer_ is not *undefined*, then""",
    """              1. Return ? Call(_replacer_, _searchValue_, « _O_, _replaceValue_ »).""",
    """          1. Let _string_ be ? ToString(_O_).""",
    """          1. Let _searchString_ be ? ToString(_searchValue_).""",
    """          1. Let _functionalReplace_ be IsCallable(_replaceValue_).""",
    """          1. If _functionalReplace_ is *false*, then""",
    """            1. Set _replaceValue_ to ? ToString(_replaceValue_).""",
    """          1. Let _searchLength_ be the length of _searchString_.""",
    """          1. Let _position_ be ! StringIndexOf(_string_, _searchString_, 0).""",
    """          1. If _position_ is -1, return _string_.""",
    """          1. Let _preserved_ be the substring of _string_ from 0 to _position_.""",
    """          1. If _functionalReplace_ is *true*, then""",
    """            1. Let _replacement_ be ? ToString(? Call(_replaceValue_, *undefined*, « _searchString_, 𝔽(_position_), _string_ »)).""",
    """          1. Else,""",
    """            1. Assert: Type(_replaceValue_) is String.""",
    """            1. Let _captures_ be a new empty List.""",
    """            1. Let _replacement_ be ! GetSubstitution(_searchString_, _string_, _position_, _captures_, *undefined*, _replaceValue_).""",
    """          1. Return the string-concatenation of _preserved_, _replacement_, and the substring of _string_ from _position_ + _searchLength_.""",
  )
}
