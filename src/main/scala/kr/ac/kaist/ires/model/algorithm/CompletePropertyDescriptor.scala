package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CompletePropertyDescriptor` extends Algo {
  val head = NormalHead("CompletePropertyDescriptor", List(Param("Desc", Normal)))
  val ids = List(
    "sec-completepropertydescriptor",
    "sec-property-descriptor-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:assert (is-instance-of Desc PropertyDescriptor)
  |  1:let like = (new Record("Value" -> undefined, "Writable" -> false, "Get" -> undefined, "Set" -> undefined, "Enumerable" -> false, "Configurable" -> false))
  |  5:let __x0__ = true
  |  5:app __x1__ = (IsGenericDescriptor Desc)
  |  5:__x0__ = (= __x1__ true)
  |  5:if __x0__ 2:{} else {
  |    app __x2__ = (IsDataDescriptor Desc)
  |    __x0__ = (= __x2__ true)
  |  }
  |  5:if __x0__ {
  |    3:if (= Desc.Value absent) Desc.Value = like.Value else 2:{}
  |    4:if (= Desc.Writable absent) Desc.Writable = like.Writable else 2:{}
  |  } else {
  |    6:if (= Desc.Get absent) Desc.Get = like.Get else 2:{}
  |    7:if (= Desc.Set absent) Desc.Set = like.Set else 2:{}
  |  }
  |  8:if (= Desc.Enumerable absent) Desc.Enumerable = like.Enumerable else 2:{}
  |  9:if (= Desc.Configurable absent) Desc.Configurable = like.Configurable else 2:{}
  |  10:return Desc
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _Desc_ is a Property Descriptor.""",
    """          1. Let _like_ be the Record { [[Value]]: *undefined*, [[Writable]]: *false*, [[Get]]: *undefined*, [[Set]]: *undefined*, [[Enumerable]]: *false*, [[Configurable]]: *false* }.""",
    """          1. If IsGenericDescriptor(_Desc_) is *true* or IsDataDescriptor(_Desc_) is *true*, then""",
    """            1. If _Desc_ does not have a [[Value]] field, set _Desc_.[[Value]] to _like_.[[Value]].""",
    """            1. If _Desc_ does not have a [[Writable]] field, set _Desc_.[[Writable]] to _like_.[[Writable]].""",
    """          1. Else,""",
    """            1. If _Desc_ does not have a [[Get]] field, set _Desc_.[[Get]] to _like_.[[Get]].""",
    """            1. If _Desc_ does not have a [[Set]] field, set _Desc_.[[Set]] to _like_.[[Set]].""",
    """          1. If _Desc_ does not have an [[Enumerable]] field, set _Desc_.[[Enumerable]] to _like_.[[Enumerable]].""",
    """          1. If _Desc_ does not have a [[Configurable]] field, set _Desc_.[[Configurable]] to _like_.[[Configurable]].""",
    """          1. Return _Desc_.""",
  )
}
