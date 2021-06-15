package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.from` extends Algo {
  val head = BuiltinHead(parseRef("""Array.from"""), List(Param("items", Normal), Param("mapfn", Optional), Param("thisArg", Optional)))
  val ids = List(
    "sec-array.from",
    "sec-properties-of-the-array-constructor",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let C = this
  |  2:if (= mapfn undefined) let mapping = false else {
  |    3:app __x0__ = (IsCallable mapfn)
  |    3:if (= __x0__ false) throw TypeError else 31:{}
  |    4:let mapping = true
  |  }
  |  5:app __x1__ = (GetMethod items SYMBOL_iterator)
  |  5:let usingIterator = [? __x1__]
  |  6:if (! (= usingIterator undefined)) {
  |    9:app __x2__ = (IsConstructor C)
  |    9:if (= __x2__ true) {
  |      8:app __x3__ = (Construct C)
  |      8:let A = [? __x3__]
  |    } else {
  |      10:app __x4__ = (ArrayCreate 0i)
  |      10:let A = [! __x4__]
  |    }
  |    11:app __x5__ = (GetIterator items CONST_sync usingIterator)
  |    11:let iteratorRecord = [? __x5__]
  |    12:let k = 0i
  |    13:while true {
  |      14:if (! (< k (- (** 2.0 53i) 1i))) {
  |        15:app __x6__ = (ThrowCompletion (new OrdinaryObject()))
  |        15:let error = __x6__
  |        16:app __x7__ = (IteratorClose iteratorRecord error)
  |        16:return [? __x7__]
  |      } else 31:{}
  |      17:app __x8__ = (ToString k)
  |      17:let Pk = [! __x8__]
  |      18:app __x9__ = (IteratorStep iteratorRecord)
  |      18:let next = [? __x9__]
  |      19:if (= next false) {
  |        20:app __x10__ = (Set A "length" k true)
  |        20:[? __x10__]
  |        21:return A
  |      } else 31:{}
  |      22:app __x11__ = (IteratorValue next)
  |      22:let nextValue = [? __x11__]
  |      27:if (= mapping true) {
  |        24:app __x12__ = (Call mapfn thisArg (new [nextValue, k]))
  |        24:let mappedValue = __x12__
  |        25:app __x13__ = (IsAbruptCompletion mappedValue)
  |        25:if __x13__ {
  |          app __x14__ = (IteratorClose iteratorRecord mappedValue)
  |          return [? __x14__]
  |        } else 31:{}
  |        26:mappedValue = mappedValue.Value
  |      } else let mappedValue = nextValue
  |      28:app __x15__ = (CreateDataPropertyOrThrow A Pk mappedValue)
  |      28:let defineStatus = __x15__
  |      29:app __x16__ = (IsAbruptCompletion defineStatus)
  |      29:if __x16__ {
  |        app __x17__ = (IteratorClose iteratorRecord defineStatus)
  |        return [? __x17__]
  |      } else 31:{}
  |      30:k = (+ k 1i)
  |    }
  |  } else 31:{}
  |  32:app __x18__ = (ToObject items)
  |  32:let arrayLike = [! __x18__]
  |  33:app __x19__ = (LengthOfArrayLike arrayLike)
  |  33:let len = [? __x19__]
  |  36:app __x20__ = (IsConstructor C)
  |  36:if (= __x20__ true) {
  |    35:app __x21__ = (Construct C (new [len]))
  |    35:let A = [? __x21__]
  |  } else {
  |    37:app __x22__ = (ArrayCreate len)
  |    37:let A = [? __x22__]
  |  }
  |  38:let k = 0i
  |  39:while (< k len) {
  |    40:app __x23__ = (ToString k)
  |    40:let Pk = [! __x23__]
  |    41:app __x24__ = (Get arrayLike Pk)
  |    41:let kValue = [? __x24__]
  |    44:if (= mapping true) {
  |      43:app __x25__ = (Call mapfn thisArg (new [kValue, k]))
  |      43:let mappedValue = [? __x25__]
  |    } else let mappedValue = kValue
  |    45:app __x26__ = (CreateDataPropertyOrThrow A Pk mappedValue)
  |    45:[? __x26__]
  |    46:k = (+ k 1i)
  |  }
  |  47:app __x27__ = (Set A "length" len true)
  |  47:[? __x27__]
  |  48:return A
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _C_ be the *this* value.""",
    """          1. If _mapfn_ is *undefined*, let _mapping_ be *false*.""",
    """          1. Else,""",
    """            1. If IsCallable(_mapfn_) is *false*, throw a *TypeError* exception.""",
    """            1. Let _mapping_ be *true*.""",
    """          1. Let _usingIterator_ be ? GetMethod(_items_, @@iterator).""",
    """          1. If _usingIterator_ is not *undefined*, then""",
    """            1. If IsConstructor(_C_) is *true*, then""",
    """              1. Let _A_ be ? Construct(_C_).""",
    """            1. Else,""",
    """              1. Let _A_ be ! ArrayCreate(0).""",
    """            1. Let _iteratorRecord_ be ? GetIterator(_items_, ~sync~, _usingIterator_).""",
    """            1. Let _k_ be 0.""",
    """            1. Repeat,""",
    """              1. If _k_ ≥ 2<sup>53</sup> - 1, then""",
    """                1. Let _error_ be ThrowCompletion(a newly created *TypeError* object).""",
    """                1. Return ? IteratorClose(_iteratorRecord_, _error_).""",
    """              1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """              1. Let _next_ be ? IteratorStep(_iteratorRecord_).""",
    """              1. If _next_ is *false*, then""",
    """                1. Perform ? Set(_A_, *"length"*, 𝔽(_k_), *true*).""",
    """                1. Return _A_.""",
    """              1. Let _nextValue_ be ? IteratorValue(_next_).""",
    """              1. If _mapping_ is *true*, then""",
    """                1. Let _mappedValue_ be Call(_mapfn_, _thisArg_, « _nextValue_, 𝔽(_k_) »).""",
    """                1. If _mappedValue_ is an abrupt completion, return ? IteratorClose(_iteratorRecord_, _mappedValue_).""",
    """                1. Set _mappedValue_ to _mappedValue_.[[Value]].""",
    """              1. Else, let _mappedValue_ be _nextValue_.""",
    """              1. Let _defineStatus_ be CreateDataPropertyOrThrow(_A_, _Pk_, _mappedValue_).""",
    """              1. If _defineStatus_ is an abrupt completion, return ? IteratorClose(_iteratorRecord_, _defineStatus_).""",
    """              1. Set _k_ to _k_ + 1.""",
    """          1. NOTE: _items_ is not an Iterable so assume it is an array-like object.""",
    """          1. Let _arrayLike_ be ! ToObject(_items_).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_arrayLike_).""",
    """          1. If IsConstructor(_C_) is *true*, then""",
    """            1. Let _A_ be ? Construct(_C_, « 𝔽(_len_) »).""",
    """          1. Else,""",
    """            1. Let _A_ be ? ArrayCreate(_len_).""",
    """          1. Let _k_ be 0.""",
    """          1. Repeat, while _k_ < _len_,""",
    """            1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """            1. Let _kValue_ be ? Get(_arrayLike_, _Pk_).""",
    """            1. If _mapping_ is *true*, then""",
    """              1. Let _mappedValue_ be ? Call(_mapfn_, _thisArg_, « _kValue_, 𝔽(_k_) »).""",
    """            1. Else, let _mappedValue_ be _kValue_.""",
    """            1. Perform ? CreateDataPropertyOrThrow(_A_, _Pk_, _mappedValue_).""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Perform ? Set(_A_, *"length"*, 𝔽(_len_), *true*).""",
    """          1. Return _A_.""",
  )
}
