package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ProxyObject.OwnPropertyKeys` extends Algo {
  val head = MethodHead("ProxyObject", "OwnPropertyKeys", Param("O", Normal), List())
  val ids = List(
    "sec-proxy-object-internal-methods-and-internal-slots-ownpropertykeys",
    "sec-proxy-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let handler = O.ProxyHandler
  |  1:if (= handler null) throw TypeError else 13:{}
  |  2:assert (= (typeof handler) Object)
  |  3:let target = O.ProxyTarget
  |  4:app __x0__ = (GetMethod handler "ownKeys")
  |  4:let trap = [? __x0__]
  |  5:if (= trap undefined) {
  |    6:app __x1__ = (target.OwnPropertyKeys target)
  |    6:return [? __x1__]
  |  } else 13:{}
  |  7:app __x2__ = (Call trap handler (new [target]))
  |  7:let trapResultArray = [? __x2__]
  |  8:app __x3__ = (CreateListFromArrayLike trapResultArray (new [String, Symbol]))
  |  8:let trapResult = [? __x3__]
  |  9:app __x4__ = (IsDuplicate trapResult)
  |  9:if __x4__ throw TypeError else 13:{}
  |  10:app __x5__ = (IsExtensible target)
  |  10:let extensibleTarget = [? __x5__]
  |  11:app __x6__ = (target.OwnPropertyKeys target)
  |  11:let targetKeys = [? __x6__]
  |  14:let targetConfigurableKeys = (new [])
  |  15:let targetNonconfigurableKeys = (new [])
  |  16:let __x7__ = targetKeys
  |  16:let __x8__ = 0i
  |  16:while (< __x8__ __x7__.length) {
  |    let key = __x7__[__x8__]
  |    17:app __x9__ = (target.GetOwnProperty target key)
  |    17:let desc = [? __x9__]
  |    20:if (&& (! (= desc undefined)) (= desc.Configurable false)) append key -> targetNonconfigurableKeys else append key -> targetConfigurableKeys
  |    __x8__ = (+ __x8__ 1i)
  |  }
  |  22:if (&& (= extensibleTarget true) (= targetNonconfigurableKeys.length 0i)) return trapResult else 13:{}
  |  24:let uncheckedResultKeys = (copy-obj trapResult)
  |  25:let __x10__ = targetNonconfigurableKeys
  |  25:let __x11__ = 0i
  |  25:while (< __x11__ __x10__.length) {
  |    let key = __x10__[__x11__]
  |    26:if (! (contains uncheckedResultKeys key)) throw TypeError else 13:{}
  |    27:let __x12__ = 0i
  |    27:while (< __x12__ uncheckedResultKeys.length) if (= uncheckedResultKeys[__x12__] key) (pop uncheckedResultKeys __x12__) else __x12__ = (+ __x12__ 1i)
  |    __x11__ = (+ __x11__ 1i)
  |  }
  |  28:if (= extensibleTarget true) return trapResult else 13:{}
  |  29:let __x13__ = targetConfigurableKeys
  |  29:let __x14__ = 0i
  |  29:while (< __x14__ __x13__.length) {
  |    let key = __x13__[__x14__]
  |    30:if (! (contains uncheckedResultKeys key)) throw TypeError else 13:{}
  |    31:let __x15__ = 0i
  |    31:while (< __x15__ uncheckedResultKeys.length) if (= uncheckedResultKeys[__x15__] key) (pop uncheckedResultKeys __x15__) else __x15__ = (+ __x15__ 1i)
  |    __x14__ = (+ __x14__ 1i)
  |  }
  |  32:if (< 0i uncheckedResultKeys.length) throw TypeError else 13:{}
  |  33:return trapResult
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _handler_ be _O_.[[ProxyHandler]].""",
    """        1. If _handler_ is *null*, throw a *TypeError* exception.""",
    """        1. Assert: Type(_handler_) is Object.""",
    """        1. Let _target_ be _O_.[[ProxyTarget]].""",
    """        1. Let _trap_ be ? GetMethod(_handler_, *"ownKeys"*).""",
    """        1. If _trap_ is *undefined*, then""",
    """          1. Return ? _target_.[[OwnPropertyKeys]]().""",
    """        1. Let _trapResultArray_ be ? Call(_trap_, _handler_, « _target_ »).""",
    """        1. Let _trapResult_ be ? CreateListFromArrayLike(_trapResultArray_, « String, Symbol »).""",
    """        1. If _trapResult_ contains any duplicate entries, throw a *TypeError* exception.""",
    """        1. Let _extensibleTarget_ be ? IsExtensible(_target_).""",
    """        1. Let _targetKeys_ be ? _target_.[[OwnPropertyKeys]]().""",
    """        1. Assert: _targetKeys_ is a List whose elements are only String and Symbol values.""",
    """        1. Assert: _targetKeys_ contains no duplicate entries.""",
    """        1. Let _targetConfigurableKeys_ be a new empty List.""",
    """        1. Let _targetNonconfigurableKeys_ be a new empty List.""",
    """        1. For each element _key_ of _targetKeys_, do""",
    """          1. Let _desc_ be ? _target_.[[GetOwnProperty]](_key_).""",
    """          1. If _desc_ is not *undefined* and _desc_.[[Configurable]] is *false*, then""",
    """            1. Append _key_ as an element of _targetNonconfigurableKeys_.""",
    """          1. Else,""",
    """            1. Append _key_ as an element of _targetConfigurableKeys_.""",
    """        1. If _extensibleTarget_ is *true* and _targetNonconfigurableKeys_ is empty, then""",
    """          1. Return _trapResult_.""",
    """        1. Let _uncheckedResultKeys_ be a List whose elements are the elements of _trapResult_.""",
    """        1. For each element _key_ of _targetNonconfigurableKeys_, do""",
    """          1. If _key_ is not an element of _uncheckedResultKeys_, throw a *TypeError* exception.""",
    """          1. Remove _key_ from _uncheckedResultKeys_.""",
    """        1. If _extensibleTarget_ is *true*, return _trapResult_.""",
    """        1. For each element _key_ of _targetConfigurableKeys_, do""",
    """          1. If _key_ is not an element of _uncheckedResultKeys_, throw a *TypeError* exception.""",
    """          1. Remove _key_ from _uncheckedResultKeys_.""",
    """        1. If _uncheckedResultKeys_ is not empty, throw a *TypeError* exception.""",
    """        1. Return _trapResult_.""",
  )
}
