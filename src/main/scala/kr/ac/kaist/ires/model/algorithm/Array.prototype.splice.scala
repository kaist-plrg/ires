package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.splice` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.splice"""), List(Param("start", Normal), Param("deleteCount", Normal), Param("items", Variadic)))
  val ids = List(
    "sec-array.prototype.splice",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  2:app __x2__ = (ToIntegerOrInfinity start)
  |  2:let relativeStart = [? __x2__]
  |  5:if (= relativeStart -Infinity) let actualStart = 0i else if (< relativeStart 0i) {
  |    app __x3__ = (max (+ len relativeStart) 0i)
  |    let actualStart = __x3__
  |  } else {
  |    app __x4__ = (min relativeStart len)
  |    let actualStart = __x4__
  |  }
  |  12:if (= start absent) {
  |    7:let insertCount = 0i
  |    8:let actualDeleteCount = 0i
  |  } else if (= deleteCount absent) {
  |    10:let insertCount = 0i
  |    11:let actualDeleteCount = (- len actualStart)
  |  } else {
  |    13:let insertCount = items.length
  |    14:app __x5__ = (ToIntegerOrInfinity deleteCount)
  |    14:let dc = [? __x5__]
  |    15:??? "Let id:{actualDeleteCount} be the result of clamping id:{dc} between 0 and id:{len} - id:{actualStart} ."
  |  }
  |  16:if (< (- (** 2.0 53i) 1i) (- (+ len insertCount) actualDeleteCount)) throw TypeError else 25:{}
  |  17:app __x6__ = (ArraySpeciesCreate O actualDeleteCount)
  |  17:let A = [? __x6__]
  |  18:let k = 0i
  |  19:while (< k actualDeleteCount) {
  |    20:app __x7__ = (ToString (+ actualStart k))
  |    20:let from = [! __x7__]
  |    21:app __x8__ = (HasProperty O from)
  |    21:let fromPresent = [? __x8__]
  |    22:if (= fromPresent true) {
  |      23:app __x9__ = (Get O from)
  |      23:let fromValue = [? __x9__]
  |      24:app __x10__ = (ToString k)
  |      24:app __x11__ = (CreateDataPropertyOrThrow A [! __x10__] fromValue)
  |      24:[? __x11__]
  |    } else 25:{}
  |    25:k = (+ k 1i)
  |  }
  |  26:app __x12__ = (Set A "length" actualDeleteCount true)
  |  26:[? __x12__]
  |  27:let itemCount = items.length
  |  45:if (< itemCount actualDeleteCount) {
  |    29:k = actualStart
  |    30:while (< k (- len actualDeleteCount)) {
  |      31:app __x13__ = (ToString (+ k actualDeleteCount))
  |      31:let from = [! __x13__]
  |      32:app __x14__ = (ToString (+ k itemCount))
  |      32:let to = [! __x14__]
  |      33:app __x15__ = (HasProperty O from)
  |      33:let fromPresent = [? __x15__]
  |      37:if (= fromPresent true) {
  |        35:app __x16__ = (Get O from)
  |        35:let fromValue = [? __x16__]
  |        36:app __x17__ = (Set O to fromValue true)
  |        36:[? __x17__]
  |      } else {
  |        38:assert (= fromPresent false)
  |        39:app __x18__ = (DeletePropertyOrThrow O to)
  |        39:[? __x18__]
  |      }
  |      40:k = (+ k 1i)
  |    }
  |    41:k = len
  |    42:while (< (+ (- len actualDeleteCount) itemCount) k) {
  |      43:app __x19__ = (ToString (- k 1i))
  |      43:app __x20__ = (DeletePropertyOrThrow O [! __x19__])
  |      43:[? __x20__]
  |      44:k = (- k 1i)
  |    }
  |  } else if (< actualDeleteCount itemCount) {
  |    46:k = (- len actualDeleteCount)
  |    47:while (< actualStart k) {
  |      48:app __x21__ = (ToString (- (+ k actualDeleteCount) 1i))
  |      48:let from = [! __x21__]
  |      49:app __x22__ = (ToString (- (+ k itemCount) 1i))
  |      49:let to = [! __x22__]
  |      50:app __x23__ = (HasProperty O from)
  |      50:let fromPresent = [? __x23__]
  |      54:if (= fromPresent true) {
  |        52:app __x24__ = (Get O from)
  |        52:let fromValue = [? __x24__]
  |        53:app __x25__ = (Set O to fromValue true)
  |        53:[? __x25__]
  |      } else {
  |        55:assert (= fromPresent false)
  |        56:app __x26__ = (DeletePropertyOrThrow O to)
  |        56:[? __x26__]
  |      }
  |      57:k = (- k 1i)
  |    }
  |  } else 25:{}
  |  58:k = actualStart
  |  59:let __x27__ = items
  |  59:let __x28__ = 0i
  |  59:while (< __x28__ __x27__.length) {
  |    let E = __x27__[__x28__]
  |    60:app __x29__ = (ToString k)
  |    60:app __x30__ = (Set O [! __x29__] E true)
  |    60:[? __x30__]
  |    61:k = (+ k 1i)
  |    __x28__ = (+ __x28__ 1i)
  |  }
  |  62:app __x31__ = (Set O "length" (+ (- len actualDeleteCount) itemCount) true)
  |  62:[? __x31__]
  |  63:return A
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. Let _relativeStart_ be ? ToIntegerOrInfinity(_start_).""",
    """          1. If _relativeStart_ is -∞, let _actualStart_ be 0.""",
    """          1. Else if _relativeStart_ < 0, let _actualStart_ be max(_len_ + _relativeStart_, 0).""",
    """          1. Else, let _actualStart_ be min(_relativeStart_, _len_).""",
    """          1. If _start_ is not present, then""",
    """            1. Let _insertCount_ be 0.""",
    """            1. Let _actualDeleteCount_ be 0.""",
    """          1. Else if _deleteCount_ is not present, then""",
    """            1. Let _insertCount_ be 0.""",
    """            1. Let _actualDeleteCount_ be _len_ - _actualStart_.""",
    """          1. Else,""",
    """            1. Let _insertCount_ be the number of elements in _items_.""",
    """            1. Let _dc_ be ? ToIntegerOrInfinity(_deleteCount_).""",
    """            1. Let _actualDeleteCount_ be the result of clamping _dc_ between 0 and _len_ - _actualStart_.""",
    """          1. If _len_ + _insertCount_ - _actualDeleteCount_ > 2<sup>53</sup> - 1, throw a *TypeError* exception.""",
    """          1. Let _A_ be ? ArraySpeciesCreate(_O_, _actualDeleteCount_).""",
    """          1. Let _k_ be 0.""",
    """          1. Repeat, while _k_ < _actualDeleteCount_,""",
    """            1. Let _from_ be ! ToString(𝔽(_actualStart_ + _k_)).""",
    """            1. Let _fromPresent_ be ? HasProperty(_O_, _from_).""",
    """            1. If _fromPresent_ is *true*, then""",
    """              1. Let _fromValue_ be ? Get(_O_, _from_).""",
    """              1. Perform ? CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_k_)), _fromValue_).""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Perform ? Set(_A_, *"length"*, 𝔽(_actualDeleteCount_), *true*).""",
    """          1. Let _itemCount_ be the number of elements in _items_.""",
    """          1. If _itemCount_ < _actualDeleteCount_, then""",
    """            1. Set _k_ to _actualStart_.""",
    """            1. Repeat, while _k_ < (_len_ - _actualDeleteCount_),""",
    """              1. Let _from_ be ! ToString(𝔽(_k_ + _actualDeleteCount_)).""",
    """              1. Let _to_ be ! ToString(𝔽(_k_ + _itemCount_)).""",
    """              1. Let _fromPresent_ be ? HasProperty(_O_, _from_).""",
    """              1. If _fromPresent_ is *true*, then""",
    """                1. Let _fromValue_ be ? Get(_O_, _from_).""",
    """                1. Perform ? Set(_O_, _to_, _fromValue_, *true*).""",
    """              1. Else,""",
    """                1. Assert: _fromPresent_ is *false*.""",
    """                1. Perform ? DeletePropertyOrThrow(_O_, _to_).""",
    """              1. Set _k_ to _k_ + 1.""",
    """            1. Set _k_ to _len_.""",
    """            1. Repeat, while _k_ > (_len_ - _actualDeleteCount_ + _itemCount_),""",
    """              1. Perform ? DeletePropertyOrThrow(_O_, ! ToString(𝔽(_k_ - 1))).""",
    """              1. Set _k_ to _k_ - 1.""",
    """          1. Else if _itemCount_ > _actualDeleteCount_, then""",
    """            1. Set _k_ to (_len_ - _actualDeleteCount_).""",
    """            1. Repeat, while _k_ > _actualStart_,""",
    """              1. Let _from_ be ! ToString(𝔽(_k_ + _actualDeleteCount_ - 1)).""",
    """              1. Let _to_ be ! ToString(𝔽(_k_ + _itemCount_ - 1)).""",
    """              1. Let _fromPresent_ be ? HasProperty(_O_, _from_).""",
    """              1. If _fromPresent_ is *true*, then""",
    """                1. Let _fromValue_ be ? Get(_O_, _from_).""",
    """                1. Perform ? Set(_O_, _to_, _fromValue_, *true*).""",
    """              1. Else,""",
    """                1. Assert: _fromPresent_ is *false*.""",
    """                1. Perform ? DeletePropertyOrThrow(_O_, _to_).""",
    """              1. Set _k_ to _k_ - 1.""",
    """          1. Set _k_ to _actualStart_.""",
    """          1. For each element _E_ of _items_, do""",
    """            1. Perform ? Set(_O_, ! ToString(𝔽(_k_)), _E_, *true*).""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. [id="step-array-proto-splice-set-length"] Perform ? Set(_O_, *"length"*, 𝔽(_len_ - _actualDeleteCount_ + _itemCount_), *true*).""",
    """          1. Return _A_.""",
  )
}
