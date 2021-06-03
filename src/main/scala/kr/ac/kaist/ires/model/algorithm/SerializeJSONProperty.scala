package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SerializeJSONProperty` extends Algo {
  val head = NormalHead("SerializeJSONProperty", List(Param("state", Normal), Param("key", Normal), Param("holder", Normal)))
  val ids = List(
    "sec-serializejsonproperty",
    "sec-json.stringify",
    "sec-json-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (Get holder key)
  |  0:let value = [? __x0__]
  |  1:if (|| (= (typeof value) Object) (= (typeof value) BigInt)) {
  |    2:app __x1__ = (GetV value "toJSON")
  |    2:let toJSON = [? __x1__]
  |    3:app __x2__ = (IsCallable toJSON)
  |    3:if (= __x2__ true) {
  |      4:app __x3__ = (Call toJSON value (new [key]))
  |      4:value = [? __x3__]
  |    } else 7:{}
  |  } else 7:{}
  |  5:if (! (= state.ReplacerFunction undefined)) {
  |    6:app __x4__ = (Call state.ReplacerFunction holder (new [key, value]))
  |    6:value = [? __x4__]
  |  } else 7:{}
  |  7:if (= (typeof value) Object) if (! (= value.NumberData absent)) {
  |    9:app __x5__ = (ToNumber value)
  |    9:value = [? __x5__]
  |  } else if (! (= value.StringData absent)) {
  |    11:app __x6__ = (ToString value)
  |    11:value = [? __x6__]
  |  } else if (! (= value.BooleanData absent)) value = value.BooleanData else if (! (= value.BigIntData absent)) value = value.BigIntData else 7:{} else 7:{}
  |  16:if (= value null) return "null" else 7:{}
  |  17:if (= value true) return "true" else 7:{}
  |  18:if (= value false) return "false" else 7:{}
  |  19:if (= (typeof value) String) {
  |    app __x7__ = (QuoteJSONString value)
  |    return __x7__
  |  } else 7:{}
  |  20:if (= (typeof value) Number) {
  |    21:if (! (|| (= value Infinity) (= value -Infinity))) {
  |      app __x8__ = (ToString value)
  |      return [! __x8__]
  |    } else 7:{}
  |    22:return "null"
  |  } else 7:{}
  |  23:if (= (typeof value) BigInt) throw TypeError else 7:{}
  |  24:let __x9__ = true
  |  24:__x9__ = (= (typeof value) Object)
  |  24:if __x9__ {
  |    app __x10__ = (IsCallable value)
  |    __x9__ = (= __x10__ false)
  |  } else 7:{}
  |  24:if __x9__ {
  |    25:app __x11__ = (IsArray value)
  |    25:let isArray = [? __x11__]
  |    26:if (= isArray true) {
  |      app __x12__ = (SerializeJSONArray state value)
  |      return [? __x12__]
  |    } else 7:{}
  |    27:app __x13__ = (SerializeJSONObject state value)
  |    27:return [? __x13__]
  |  } else 7:{}
  |  28:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _value_ be ? Get(_holder_, _key_).""",
    """          1. If Type(_value_) is Object or BigInt, then""",
    """            1. Let _toJSON_ be ? GetV(_value_, *"toJSON"*).""",
    """            1. If IsCallable(_toJSON_) is *true*, then""",
    """              1. Set _value_ to ? Call(_toJSON_, _value_, « _key_ »).""",
    """          1. If _state_.[[ReplacerFunction]] is not *undefined*, then""",
    """            1. Set _value_ to ? Call(_state_.[[ReplacerFunction]], _holder_, « _key_, _value_ »).""",
    """          1. If Type(_value_) is Object, then""",
    """            1. If _value_ has a [[NumberData]] internal slot, then""",
    """              1. Set _value_ to ? ToNumber(_value_).""",
    """            1. Else if _value_ has a [[StringData]] internal slot, then""",
    """              1. Set _value_ to ? ToString(_value_).""",
    """            1. Else if _value_ has a [[BooleanData]] internal slot, then""",
    """              1. Set _value_ to _value_.[[BooleanData]].""",
    """            1. Else if _value_ has a [[BigIntData]] internal slot, then""",
    """              1. Set _value_ to _value_.[[BigIntData]].""",
    """          1. If _value_ is *null*, return *"null"*.""",
    """          1. If _value_ is *true*, return *"true"*.""",
    """          1. If _value_ is *false*, return *"false"*.""",
    """          1. If Type(_value_) is String, return QuoteJSONString(_value_).""",
    """          1. If Type(_value_) is Number, then""",
    """            1. If _value_ is finite, return ! ToString(_value_).""",
    """            1. Return *"null"*.""",
    """          1. If Type(_value_) is BigInt, throw a *TypeError* exception.""",
    """          1. If Type(_value_) is Object and IsCallable(_value_) is *false*, then""",
    """            1. Let _isArray_ be ? IsArray(_value_).""",
    """            1. If _isArray_ is *true*, return ? SerializeJSONArray(_state_, _value_).""",
    """            1. Return ? SerializeJSONObject(_state_, _value_).""",
    """          1. Return *undefined*.""",
  )
}
