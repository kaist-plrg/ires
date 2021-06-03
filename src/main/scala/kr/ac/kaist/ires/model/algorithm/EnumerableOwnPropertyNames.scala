package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EnumerableOwnPropertyNames` extends Algo {
  val head = NormalHead("EnumerableOwnPropertyNames", List(Param("O", Normal), Param("kind", Normal)))
  val ids = List(
    "sec-enumerableownpropertynames",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:app __x0__ = (O.OwnPropertyKeys O)
  |  1:let ownKeys = [? __x0__]
  |  2:let properties = (new [])
  |  3:let __x1__ = ownKeys
  |  3:let __x2__ = 0i
  |  3:while (< __x2__ __x1__.length) {
  |    let key = __x1__[__x2__]
  |    4:if (= (typeof key) String) {
  |      5:app __x3__ = (O.GetOwnProperty O key)
  |      5:let desc = [? __x3__]
  |      6:if (&& (! (= desc undefined)) (= desc.Enumerable true)) if (= kind CONST_key) append key -> properties else {
  |        9:app __x4__ = (Get O key)
  |        9:let value = [? __x4__]
  |        11:if (= kind CONST_value) append value -> properties else {
  |          12:assert (= kind CONST_keyPLUSvalue)
  |          13:app __x5__ = (CreateArrayFromList (new [key, value]))
  |          13:let entry = [! __x5__]
  |          14:append entry -> properties
  |        }
  |      } else 0:{}
  |    } else 0:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  15:return properties
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_O_) is Object.""",
    """        1. Let _ownKeys_ be ? _O_.[[OwnPropertyKeys]]().""",
    """        1. Let _properties_ be a new empty List.""",
    """        1. For each element _key_ of _ownKeys_, do""",
    """          1. If Type(_key_) is String, then""",
    """            1. Let _desc_ be ? _O_.[[GetOwnProperty]](_key_).""",
    """            1. If _desc_ is not *undefined* and _desc_.[[Enumerable]] is *true*, then""",
    """              1. If _kind_ is ~key~, append _key_ to _properties_.""",
    """              1. Else,""",
    """                1. Let _value_ be ? Get(_O_, _key_).""",
    """                1. If _kind_ is ~value~, append _value_ to _properties_.""",
    """                1. Else,""",
    """                  1. Assert: _kind_ is ~key+value~.""",
    """                  1. Let _entry_ be ! CreateArrayFromList(« _key_, _value_ »).""",
    """                  1. Append _entry_ to _properties_.""",
    """        1. Return _properties_.""",
  )
}
