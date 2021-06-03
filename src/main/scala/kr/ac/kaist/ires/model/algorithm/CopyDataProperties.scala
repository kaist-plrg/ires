package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CopyDataProperties` extends Algo {
  val head = NormalHead("CopyDataProperties", List(Param("target", Normal), Param("source", Normal), Param("excludedItems", Normal)))
  val ids = List(
    "sec-copydataproperties",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof target) Object)
  |  2:if (|| (= source undefined) (= source null)) return target else 1:{}
  |  3:app __x0__ = (ToObject source)
  |  3:let from = [! __x0__]
  |  4:app __x1__ = (from.OwnPropertyKeys from)
  |  4:let keys = [? __x1__]
  |  5:let __x2__ = keys
  |  5:let __x3__ = 0i
  |  5:while (< __x3__ __x2__.length) {
  |    let nextKey = __x2__[__x3__]
  |    6:let excluded = false
  |    7:let __x4__ = excludedItems
  |    7:let __x5__ = 0i
  |    7:while (< __x5__ __x4__.length) {
  |      let e = __x4__[__x5__]
  |      8:app __x6__ = (SameValue e nextKey)
  |      8:if (= __x6__ true) excluded = true else 1:{}
  |      __x5__ = (+ __x5__ 1i)
  |    }
  |    10:if (= excluded false) {
  |      11:app __x7__ = (from.GetOwnProperty from nextKey)
  |      11:let desc = [? __x7__]
  |      12:if (&& (! (= desc undefined)) (= desc.Enumerable true)) {
  |        13:app __x8__ = (Get from nextKey)
  |        13:let propValue = [? __x8__]
  |        14:app __x9__ = (CreateDataPropertyOrThrow target nextKey propValue)
  |        14:[! __x9__]
  |      } else 1:{}
  |    } else 1:{}
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  15:return target
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_target_) is Object.""",
    """        1. Assert: _excludedItems_ is a List of property keys.""",
    """        1. If _source_ is *undefined* or *null*, return _target_.""",
    """        1. Let _from_ be ! ToObject(_source_).""",
    """        1. Let _keys_ be ? _from_.[[OwnPropertyKeys]]().""",
    """        1. For each element _nextKey_ of _keys_, do""",
    """          1. Let _excluded_ be *false*.""",
    """          1. For each element _e_ of _excludedItems_, do""",
    """            1. If SameValue(_e_, _nextKey_) is *true*, then""",
    """              1. Set _excluded_ to *true*.""",
    """          1. If _excluded_ is *false*, then""",
    """            1. Let _desc_ be ? _from_.[[GetOwnProperty]](_nextKey_).""",
    """            1. If _desc_ is not *undefined* and _desc_.[[Enumerable]] is *true*, then""",
    """              1. Let _propValue_ be ? Get(_from_, _nextKey_).""",
    """              1. Perform ! CreateDataPropertyOrThrow(_target_, _nextKey_, _propValue_).""",
    """        1. Return _target_.""",
  )
}
