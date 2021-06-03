package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToPropertyDescriptor` extends Algo {
  val head = NormalHead("ToPropertyDescriptor", List(Param("Obj", Normal)))
  val ids = List(
    "sec-topropertydescriptor",
    "sec-property-descriptor-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof Obj) Object)) throw TypeError else 2:{}
  |  1:let desc = (new PropertyDescriptor("SubMap" -> (new SubMap())))
  |  2:app __x0__ = (HasProperty Obj "enumerable")
  |  2:let hasEnumerable = [? __x0__]
  |  3:if (= hasEnumerable true) {
  |    4:app __x1__ = (Get Obj "enumerable")
  |    4:app __x2__ = (ToBoolean [? __x1__])
  |    4:let enumerable = [! __x2__]
  |    5:desc.Enumerable = enumerable
  |  } else 2:{}
  |  6:app __x3__ = (HasProperty Obj "configurable")
  |  6:let hasConfigurable = [? __x3__]
  |  7:if (= hasConfigurable true) {
  |    8:app __x4__ = (Get Obj "configurable")
  |    8:app __x5__ = (ToBoolean [? __x4__])
  |    8:let configurable = [! __x5__]
  |    9:desc.Configurable = configurable
  |  } else 2:{}
  |  10:app __x6__ = (HasProperty Obj "value")
  |  10:let hasValue = [? __x6__]
  |  11:if (= hasValue true) {
  |    12:app __x7__ = (Get Obj "value")
  |    12:let value = [? __x7__]
  |    13:desc.Value = value
  |  } else 2:{}
  |  14:app __x8__ = (HasProperty Obj "writable")
  |  14:let hasWritable = [? __x8__]
  |  15:if (= hasWritable true) {
  |    16:app __x9__ = (Get Obj "writable")
  |    16:app __x10__ = (ToBoolean [? __x9__])
  |    16:let writable = [! __x10__]
  |    17:desc.Writable = writable
  |  } else 2:{}
  |  18:app __x11__ = (HasProperty Obj "get")
  |  18:let hasGet = [? __x11__]
  |  19:if (= hasGet true) {
  |    20:app __x12__ = (Get Obj "get")
  |    20:let getter = [? __x12__]
  |    21:app __x13__ = (IsCallable getter)
  |    21:if (&& (= __x13__ false) (! (= getter undefined))) throw TypeError else 2:{}
  |    22:desc.Get = getter
  |  } else 2:{}
  |  23:app __x14__ = (HasProperty Obj "set")
  |  23:let hasSet = [? __x14__]
  |  24:if (= hasSet true) {
  |    25:app __x15__ = (Get Obj "set")
  |    25:let setter = [? __x15__]
  |    26:app __x16__ = (IsCallable setter)
  |    26:if (&& (= __x16__ false) (! (= setter undefined))) throw TypeError else 2:{}
  |    27:desc.Set = setter
  |  } else 2:{}
  |  28:if (|| (! (= desc.Get absent)) (! (= desc.Set absent))) if (|| (! (= desc.Value absent)) (! (= desc.Writable absent))) throw TypeError else 2:{} else 2:{}
  |  30:return desc
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_Obj_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _desc_ be a new Property Descriptor that initially has no fields.""",
    """          1. Let _hasEnumerable_ be ? HasProperty(_Obj_, *"enumerable"*).""",
    """          1. If _hasEnumerable_ is *true*, then""",
    """            1. Let _enumerable_ be ! ToBoolean(? Get(_Obj_, *"enumerable"*)).""",
    """            1. Set _desc_.[[Enumerable]] to _enumerable_.""",
    """          1. Let _hasConfigurable_ be ? HasProperty(_Obj_, *"configurable"*).""",
    """          1. If _hasConfigurable_ is *true*, then""",
    """            1. Let _configurable_ be ! ToBoolean(? Get(_Obj_, *"configurable"*)).""",
    """            1. Set _desc_.[[Configurable]] to _configurable_.""",
    """          1. Let _hasValue_ be ? HasProperty(_Obj_, *"value"*).""",
    """          1. If _hasValue_ is *true*, then""",
    """            1. Let _value_ be ? Get(_Obj_, *"value"*).""",
    """            1. Set _desc_.[[Value]] to _value_.""",
    """          1. Let _hasWritable_ be ? HasProperty(_Obj_, *"writable"*).""",
    """          1. If _hasWritable_ is *true*, then""",
    """            1. Let _writable_ be ! ToBoolean(? Get(_Obj_, *"writable"*)).""",
    """            1. Set _desc_.[[Writable]] to _writable_.""",
    """          1. Let _hasGet_ be ? HasProperty(_Obj_, *"get"*).""",
    """          1. If _hasGet_ is *true*, then""",
    """            1. Let _getter_ be ? Get(_Obj_, *"get"*).""",
    """            1. If IsCallable(_getter_) is *false* and _getter_ is not *undefined*, throw a *TypeError* exception.""",
    """            1. Set _desc_.[[Get]] to _getter_.""",
    """          1. Let _hasSet_ be ? HasProperty(_Obj_, *"set"*).""",
    """          1. If _hasSet_ is *true*, then""",
    """            1. Let _setter_ be ? Get(_Obj_, *"set"*).""",
    """            1. If IsCallable(_setter_) is *false* and _setter_ is not *undefined*, throw a *TypeError* exception.""",
    """            1. Set _desc_.[[Set]] to _setter_.""",
    """          1. If _desc_.[[Get]] is present or _desc_.[[Set]] is present, then""",
    """            1. If _desc_.[[Value]] is present or _desc_.[[Writable]] is present, throw a *TypeError* exception.""",
    """          1. Return _desc_.""",
  )
}
