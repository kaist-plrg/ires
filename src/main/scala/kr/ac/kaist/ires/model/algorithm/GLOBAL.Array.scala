package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Array` extends Algo {
  val head = BuiltinHead(parseRef("""Array"""), List(Param("values", Variadic)))
  val ids = List(
    "sec-array",
    "sec-array-constructor",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) let newTarget = CONTEXT.Function else let newTarget = NewTarget
  |  1:app __x0__ = (GetPrototypeFromConstructor newTarget "%Array.prototype%")
  |  1:let proto = [? __x0__]
  |  2:let numberOfArgs = values.length
  |  16:if (== numberOfArgs 0i) {
  |    4:app __x1__ = (ArrayCreate 0i proto)
  |    4:return [! __x1__]
  |  } else if (== numberOfArgs 1i) {
  |    6:let len = values[0i]
  |    7:app __x2__ = (ArrayCreate 0i proto)
  |    7:let array = [! __x2__]
  |    11:if (! (= (typeof len) Number)) {
  |      9:app __x3__ = (CreateDataPropertyOrThrow array "0" len)
  |      9:[! __x3__]
  |      10:let intLen = 1i
  |    } else {
  |      12:app __x4__ = (ToUint32 len)
  |      12:let intLen = [! __x4__]
  |      13:if (! (= intLen len)) throw RangeError else 25:{}
  |    }
  |    14:app __x5__ = (Set array "length" intLen true)
  |    14:[! __x5__]
  |    15:return array
  |  } else {
  |    17:assert (! (< numberOfArgs 2i))
  |    18:app __x6__ = (ArrayCreate numberOfArgs proto)
  |    18:let array = [? __x6__]
  |    19:let k = 0i
  |    20:while (< k numberOfArgs) {
  |      21:app __x7__ = (ToString k)
  |      21:let Pk = [! __x7__]
  |      22:let itemK = values[k]
  |      23:app __x8__ = (CreateDataPropertyOrThrow array Pk itemK)
  |      23:[! __x8__]
  |      24:k = (+ k 1i)
  |    }
  |    26:return array
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, let _newTarget_ be the active function object; else let _newTarget_ be NewTarget.""",
    """          1. Let _proto_ be ? GetPrototypeFromConstructor(_newTarget_, *"%Array.prototype%"*).""",
    """          1. Let _numberOfArgs_ be the number of elements in _values_.""",
    """          1. If _numberOfArgs_ = 0, then""",
    """            1. Return ! ArrayCreate(0, _proto_).""",
    """          1. Else if _numberOfArgs_ = 1, then""",
    """            1. Let _len_ be _values_[0].""",
    """            1. Let _array_ be ! ArrayCreate(0, _proto_).""",
    """            1. If Type(_len_) is not Number, then""",
    """              1. Perform ! CreateDataPropertyOrThrow(_array_, *"0"*, _len_).""",
    """              1. Let _intLen_ be *1*<sub>𝔽</sub>.""",
    """            1. Else,""",
    """              1. Let _intLen_ be ! ToUint32(_len_).""",
    """              1. If _intLen_ is not the same value as _len_, throw a *RangeError* exception.""",
    """            1. Perform ! Set(_array_, *"length"*, _intLen_, *true*).""",
    """            1. Return _array_.""",
    """          1. Else,""",
    """            1. Assert: _numberOfArgs_ ≥ 2.""",
    """            1. Let _array_ be ? ArrayCreate(_numberOfArgs_, _proto_).""",
    """            1. Let _k_ be 0.""",
    """            1. Repeat, while _k_ < _numberOfArgs_,""",
    """              1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """              1. Let _itemK_ be _values_[_k_].""",
    """              1. Perform ! CreateDataPropertyOrThrow(_array_, _Pk_, _itemK_).""",
    """              1. Set _k_ to _k_ + 1.""",
    """            1. Assert: The mathematical value of _array_'s *"length"* property is _numberOfArgs_.""",
    """            1. Return _array_.""",
  )
}
