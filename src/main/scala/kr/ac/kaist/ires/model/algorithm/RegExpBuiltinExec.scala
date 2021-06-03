package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RegExpBuiltinExec` extends Algo {
  val head = NormalHead("RegExpBuiltinExec", List(Param("R", Normal), Param("S", Normal)))
  val ids = List(
    "sec-regexpbuiltinexec",
    "sec-regexp.prototype.exec",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  1:assert (= (typeof S) String)
  |  2:let length = S.length
  |  3:app __x0__ = (Get R "lastIndex")
  |  3:app __x1__ = (ToLength [? __x0__])
  |  3:let lastIndex = [? __x1__]
  |  4:let flags = R.OriginalFlags
  |  5:if (contains flags "g") let global = true else let global = false
  |  6:if (contains flags "y") let sticky = true else let sticky = false
  |  7:if (&& (= global false) (= sticky false)) lastIndex = 0i else 52:{}
  |  8:let matcher = R.RegExpMatcher
  |  9:if (contains flags "u") let fullUnicode = true else let fullUnicode = false
  |  10:let matchSucceeded = false
  |  11:while (= matchSucceeded false) {
  |    12:if (< length lastIndex) {
  |      13:if (|| (= global true) (= sticky true)) {
  |        14:app __x2__ = (Set R "lastIndex" 0i true)
  |        14:[? __x2__]
  |      } else 52:{}
  |      15:return null
  |    } else 52:{}
  |    16:app __x3__ = (matcher S lastIndex)
  |    16:let r = __x3__
  |    22:if (= r CONST_failure) {
  |      18:if (= sticky true) {
  |        19:app __x4__ = (Set R "lastIndex" 0i true)
  |        19:[? __x4__]
  |        20:return null
  |      } else 52:{}
  |      21:app __x5__ = (AdvanceStringIndex S lastIndex fullUnicode)
  |      21:lastIndex = __x5__
  |    } else matchSucceeded = true
  |  }
  |  25:let e = r.endIndex
  |  26:if (= fullUnicode true) {
  |    27:??? "id:{e} is an index into the id:{Input} character list , derived from id:{S} , matched by id:{matcher} . Let id:{eUTF} be the smallest index into id:{S} that corresponds to the character at element id:{e} of id:{Input} . If id:{e} is greater than or equal to the number of elements in id:{Input} , then id:{eUTF} is the number of code units in id:{S} ."
  |    28:e = eUTF
  |  } else 52:{}
  |  29:if (|| (= global true) (= sticky true)) {
  |    30:app __x6__ = (Set R "lastIndex" e true)
  |    30:[? __x6__]
  |  } else 52:{}
  |  31:??? "Let id:{n} be the number of elements in id:{r} ' s id:{captures} List . ( This is the same value as link:{unhandled: sec-notation} ' s id:{NcapturingParens} . )"
  |  32:assert (< n (- (** 2.0 32i) 1i))
  |  33:app __x7__ = (ArrayCreate (+ n 1i))
  |  33:let A = [! __x7__]
  |  35:app __x8__ = (CreateDataPropertyOrThrow A "index" lastIndex)
  |  35:[! __x8__]
  |  36:app __x9__ = (CreateDataPropertyOrThrow A "input" S)
  |  36:[! __x9__]
  |  37:let __x10__ = ""
  |  37:let __x11__ = lastIndex
  |  37:while (< __x11__ (+ e 1i)) {
  |    access __x12__ = (S __x11__)
  |    __x10__ = (+ __x10__ __x12__)
  |    __x11__ = (+ __x11__ 1i)
  |  }
  |  37:let matchedSubstr = __x10__
  |  38:app __x13__ = (CreateDataPropertyOrThrow A "0" matchedSubstr)
  |  38:[! __x13__]
  |  39:??? "If id:{R} contains any nt:{GroupName} , then in:{} out:{}"
  |  41:??? "Else , in:{} out:{}"
  |  43:app __x14__ = (CreateDataPropertyOrThrow A "groups" groups)
  |  43:[! __x14__]
  |  44:??? "For each integer id:{i} such that id:{i} ≥ 1 and id:{i} ≤ id:{n} , do in:{} out:{}"
  |  58:return A
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _R_ is an initialized RegExp instance.""",
    """            1. Assert: Type(_S_) is String.""",
    """            1. Let _length_ be the number of code units in _S_.""",
    """            1. Let _lastIndex_ be ℝ(? ToLength(? Get(_R_, *"lastIndex"*))).""",
    """            1. Let _flags_ be _R_.[[OriginalFlags]].""",
    """            1. If _flags_ contains *"g"*, let _global_ be *true*; else let _global_ be *false*.""",
    """            1. If _flags_ contains *"y"*, let _sticky_ be *true*; else let _sticky_ be *false*.""",
    """            1. If _global_ is *false* and _sticky_ is *false*, set _lastIndex_ to 0.""",
    """            1. Let _matcher_ be _R_.[[RegExpMatcher]].""",
    """            1. If _flags_ contains *"u"*, let _fullUnicode_ be *true*; else let _fullUnicode_ be *false*.""",
    """            1. Let _matchSucceeded_ be *false*.""",
    """            1. Repeat, while _matchSucceeded_ is *false*,""",
    """              1. If _lastIndex_ > _length_, then""",
    """                1. If _global_ is *true* or _sticky_ is *true*, then""",
    """                  1. Perform ? Set(_R_, *"lastIndex"*, *+0*<sub>𝔽</sub>, *true*).""",
    """                1. Return *null*.""",
    """              1. Let _r_ be _matcher_(_S_, _lastIndex_).""",
    """              1. If _r_ is ~failure~, then""",
    """                1. If _sticky_ is *true*, then""",
    """                  1. Perform ? Set(_R_, *"lastIndex"*, *+0*<sub>𝔽</sub>, *true*).""",
    """                  1. Return *null*.""",
    """                1. Set _lastIndex_ to AdvanceStringIndex(_S_, _lastIndex_, _fullUnicode_).""",
    """              1. Else,""",
    """                1. Assert: _r_ is a State.""",
    """                1. Set _matchSucceeded_ to *true*.""",
    """            1. Let _e_ be _r_'s _endIndex_ value.""",
    """            1. If _fullUnicode_ is *true*, then""",
    """              1. _e_ is an index into the _Input_ character list, derived from _S_, matched by _matcher_. Let _eUTF_ be the smallest index into _S_ that corresponds to the character at element _e_ of _Input_. If _e_ is greater than or equal to the number of elements in _Input_, then _eUTF_ is the number of code units in _S_.""",
    """              1. Set _e_ to _eUTF_.""",
    """            1. If _global_ is *true* or _sticky_ is *true*, then""",
    """              1. Perform ? Set(_R_, *"lastIndex"*, 𝔽(_e_), *true*).""",
    """            1. Let _n_ be the number of elements in _r_'s _captures_ List. (This is the same value as <emu-xref href="#sec-notation"></emu-xref>'s _NcapturingParens_.)""",
    """            1. Assert: _n_ < 2<sup>32</sup> - 1.""",
    """            1. Let _A_ be ! ArrayCreate(_n_ + 1).""",
    """            1. Assert: The mathematical value of _A_'s *"length"* property is _n_ + 1.""",
    """            1. Perform ! CreateDataPropertyOrThrow(_A_, *"index"*, 𝔽(_lastIndex_)).""",
    """            1. Perform ! CreateDataPropertyOrThrow(_A_, *"input"*, _S_).""",
    """            1. Let _matchedSubstr_ be the substring of _S_ from _lastIndex_ to _e_.""",
    """            1. Perform ! CreateDataPropertyOrThrow(_A_, *"0"*, _matchedSubstr_).""",
    """            1. If _R_ contains any |GroupName|, then""",
    """              1. Let _groups_ be ! OrdinaryObjectCreate(*null*).""",
    """            1. Else,""",
    """              1. Let _groups_ be *undefined*.""",
    """            1. Perform ! CreateDataPropertyOrThrow(_A_, *"groups"*, _groups_).""",
    """            1. For each integer _i_ such that _i_ ≥ 1 and _i_ ≤ _n_, do""",
    """              1. Let _captureI_ be _i_<sup>th</sup> element of _r_'s _captures_ List.""",
    """              1. If _captureI_ is *undefined*, let _capturedValue_ be *undefined*.""",
    """              1. Else if _fullUnicode_ is *true*, then""",
    """                1. Assert: _captureI_ is a List of code points.""",
    """                1. Let _capturedValue_ be ! CodePointsToString(_captureI_).""",
    """              1. Else,""",
    """                1. Assert: _fullUnicode_ is *false*.""",
    """                1. Assert: _captureI_ is a List of code units.""",
    """                1. Let _capturedValue_ be the String value consisting of the code units of _captureI_.""",
    """              1. Perform ! CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_i_)), _capturedValue_).""",
    """              1. If the _i_<sup>th</sup> capture of _R_ was defined with a |GroupName|, then""",
    """                1. Let _s_ be the CapturingGroupName of the corresponding |RegExpIdentifierName|.""",
    """                1. Perform ! CreateDataPropertyOrThrow(_groups_, _s_, _capturedValue_).""",
    """            1. Return _A_.""",
  )
}
