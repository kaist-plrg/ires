package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ValidateAndApplyPropertyDescriptor` extends Algo {
  val head = NormalHead("ValidateAndApplyPropertyDescriptor", List(Param("O", Normal), Param("P", Normal), Param("extensible", Normal), Param("Desc", Normal), Param("current", Normal)))
  val ids = List(
    "sec-validateandapplypropertydescriptor",
    "sec-ordinary-object-internal-methods-and-internal-slots-defineownproperty-p-desc",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:if (= current undefined) {
  |    2:if (= extensible false) return false else 15:{}
  |    3:assert (= extensible true)
  |    6:let __x0__ = true
  |    6:app __x1__ = (IsGenericDescriptor Desc)
  |    6:__x0__ = (= __x1__ true)
  |    6:if __x0__ 15:{} else {
  |      app __x2__ = (IsDataDescriptor Desc)
  |      __x0__ = (= __x2__ true)
  |    }
  |    6:if __x0__ if (= O undefined) {} else {} else {
  |      7:app __x3__ = (IsAccessorDescriptor Desc)
  |      7:assert (= [! __x3__] true)
  |      8:if (= O undefined) {} else {}
  |    }
  |    9:return true
  |  } else 15:{}
  |  10:??? "If every field in id:{Desc} is absent , return value:{true} ."
  |  11:if (= current.Configurable false) {
  |    12:if (= Desc.Configurable true) return false else 15:{}
  |    13:let __x4__ = true
  |    13:__x4__ = (! (= Desc.Enumerable absent))
  |    13:if __x4__ {
  |      app __x5__ = (SameValue Desc.Enumerable current.Enumerable)
  |      __x4__ = (= [! __x5__] false)
  |    } else 15:{}
  |    13:if __x4__ return false else 15:{}
  |  } else 15:{}
  |  27:app __x6__ = (IsGenericDescriptor Desc)
  |  27:if (= [! __x6__] true) {} else {
  |    app __x7__ = (IsDataDescriptor current)
  |    app __x8__ = (IsDataDescriptor Desc)
  |    app __x9__ = (SameValue [! __x7__] [! __x8__])
  |    if (= [! __x9__] false) {
  |      17:if (= current.Configurable false) return false else 15:{}
  |      20:app __x10__ = (IsDataDescriptor current)
  |      20:if (= __x10__ true) if (= O undefined) {} else {} else if (= O undefined) {} else {}
  |    } else {
  |      app __x11__ = (IsDataDescriptor current)
  |      app __x12__ = (IsDataDescriptor Desc)
  |      if (&& (= __x11__ true) (= __x12__ true)) if (&& (= current.Configurable false) (= current.Writable false)) {
  |        24:if (&& (! (= Desc.Writable absent)) (= Desc.Writable true)) return false else 15:{}
  |        25:let __x13__ = true
  |        25:__x13__ = (! (= Desc.Value absent))
  |        25:if __x13__ {
  |          app __x14__ = (SameValue Desc.Value current.Value)
  |          __x13__ = (= __x14__ false)
  |        } else 15:{}
  |        25:if __x13__ return false else 15:{}
  |        26:return true
  |      } else 15:{} else {
  |        28:app __x15__ = (IsAccessorDescriptor current)
  |        28:app __x16__ = (IsAccessorDescriptor Desc)
  |        28:assert (&& (= [! __x15__] true) (= [! __x16__] true))
  |        29:if (= current.Configurable false) {
  |          30:let __x17__ = true
  |          30:__x17__ = (! (= Desc.Set absent))
  |          30:if __x17__ {
  |            app __x18__ = (SameValue Desc.Set current.Set)
  |            __x17__ = (= __x18__ false)
  |          } else 15:{}
  |          30:if __x17__ return false else 15:{}
  |          31:let __x19__ = true
  |          31:__x19__ = (! (= Desc.Get absent))
  |          31:if __x19__ {
  |            app __x20__ = (SameValue Desc.Get current.Get)
  |            __x19__ = (= __x20__ false)
  |          } else 15:{}
  |          31:if __x19__ return false else 15:{}
  |          32:return true
  |        } else 15:{}
  |      }
  |    }
  |  }
  |  33:if (! (= O undefined)) ??? "For each field of id:{Desc} that is present , set the corresponding attribute of the property named id:{P} of object id:{O} to the value of the field ." else 15:{}
  |  35:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: If _O_ is not *undefined*, then IsPropertyKey(_P_) is *true*.""",
    """          1. If _current_ is *undefined*, then""",
    """            1. If _extensible_ is *false*, return *false*.""",
    """            1. Assert: _extensible_ is *true*.""",
    """            1. If IsGenericDescriptor(_Desc_) is *true* or IsDataDescriptor(_Desc_) is *true*, then""",
    """              1. If _O_ is not *undefined*, create an own data property named _P_ of object _O_ whose [[Value]], [[Writable]], [[Enumerable]], and [[Configurable]] attribute values are described by _Desc_. If the value of an attribute field of _Desc_ is absent, the attribute of the newly created property is set to its <emu-xref href="#table-default-attribute-values">default value</emu-xref>.""",
    """            1. Else,""",
    """              1. Assert: ! IsAccessorDescriptor(_Desc_) is *true*.""",
    """              1. If _O_ is not *undefined*, create an own accessor property named _P_ of object _O_ whose [[Get]], [[Set]], [[Enumerable]], and [[Configurable]] attribute values are described by _Desc_. If the value of an attribute field of _Desc_ is absent, the attribute of the newly created property is set to its <emu-xref href="#table-default-attribute-values">default value</emu-xref>.""",
    """            1. Return *true*.""",
    """          1. If every field in _Desc_ is absent, return *true*.""",
    """          1. If _current_.[[Configurable]] is *false*, then""",
    """            1. If _Desc_.[[Configurable]] is present and its value is *true*, return *false*.""",
    """            1. If _Desc_.[[Enumerable]] is present and ! SameValue(_Desc_.[[Enumerable]], _current_.[[Enumerable]]) is *false*, return *false*.""",
    """          1. If ! IsGenericDescriptor(_Desc_) is *true*, then""",
    """            1. NOTE: No further validation is required.""",
    """          1. Else if ! SameValue(! IsDataDescriptor(_current_), ! IsDataDescriptor(_Desc_)) is *false*, then""",
    """            1. If _current_.[[Configurable]] is *false*, return *false*.""",
    """            1. If IsDataDescriptor(_current_) is *true*, then""",
    """              1. If _O_ is not *undefined*, convert the property named _P_ of object _O_ from a data property to an accessor property. Preserve the existing values of the converted property's [[Configurable]] and [[Enumerable]] attributes and set the rest of the property's attributes to their <emu-xref href="#table-default-attribute-values">default values</emu-xref>.""",
    """            1. Else,""",
    """              1. If _O_ is not *undefined*, convert the property named _P_ of object _O_ from an accessor property to a data property. Preserve the existing values of the converted property's [[Configurable]] and [[Enumerable]] attributes and set the rest of the property's attributes to their <emu-xref href="#table-default-attribute-values">default values</emu-xref>.""",
    """          1. Else if IsDataDescriptor(_current_) and IsDataDescriptor(_Desc_) are both *true*, then""",
    """            1. If _current_.[[Configurable]] is *false* and _current_.[[Writable]] is *false*, then""",
    """              1. If _Desc_.[[Writable]] is present and _Desc_.[[Writable]] is *true*, return *false*.""",
    """              1. If _Desc_.[[Value]] is present and SameValue(_Desc_.[[Value]], _current_.[[Value]]) is *false*, return *false*.""",
    """              1. Return *true*.""",
    """          1. Else,""",
    """            1. Assert: ! IsAccessorDescriptor(_current_) and ! IsAccessorDescriptor(_Desc_) are both *true*.""",
    """            1. If _current_.[[Configurable]] is *false*, then""",
    """              1. If _Desc_.[[Set]] is present and SameValue(_Desc_.[[Set]], _current_.[[Set]]) is *false*, return *false*.""",
    """              1. If _Desc_.[[Get]] is present and SameValue(_Desc_.[[Get]], _current_.[[Get]]) is *false*, return *false*.""",
    """              1. Return *true*.""",
    """          1. If _O_ is not *undefined*, then""",
    """            1. For each field of _Desc_ that is present, set the corresponding attribute of the property named _P_ of object _O_ to the value of the field.""",
    """          1. Return *true*.""",
  )
}
