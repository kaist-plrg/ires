package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FromPropertyDescriptor` extends Algo {
  val head = NormalHead("FromPropertyDescriptor", List(Param("Desc", Normal)))
  val ids = List(
    "sec-frompropertydescriptor",
    "sec-property-descriptor-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (= Desc undefined) return undefined else 2:{}
  |  1:app __x0__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |  1:let obj = [! __x0__]
  |  3:if (! (= Desc.Value absent)) {
  |    4:app __x1__ = (CreateDataPropertyOrThrow obj "value" Desc.Value)
  |    4:[! __x1__]
  |  } else 2:{}
  |  5:if (! (= Desc.Writable absent)) {
  |    6:app __x2__ = (CreateDataPropertyOrThrow obj "writable" Desc.Writable)
  |    6:[! __x2__]
  |  } else 2:{}
  |  7:if (! (= Desc.Get absent)) {
  |    8:app __x3__ = (CreateDataPropertyOrThrow obj "get" Desc.Get)
  |    8:[! __x3__]
  |  } else 2:{}
  |  9:if (! (= Desc.Set absent)) {
  |    10:app __x4__ = (CreateDataPropertyOrThrow obj "set" Desc.Set)
  |    10:[! __x4__]
  |  } else 2:{}
  |  11:if (! (= Desc.Enumerable absent)) {
  |    12:app __x5__ = (CreateDataPropertyOrThrow obj "enumerable" Desc.Enumerable)
  |    12:[! __x5__]
  |  } else 2:{}
  |  13:if (! (= Desc.Configurable absent)) {
  |    14:app __x6__ = (CreateDataPropertyOrThrow obj "configurable" Desc.Configurable)
  |    14:[! __x6__]
  |  } else 2:{}
  |  15:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _Desc_ is *undefined*, return *undefined*.""",
    """          1. Let _obj_ be ! OrdinaryObjectCreate(%Object.prototype%).""",
    """          1. Assert: _obj_ is an extensible ordinary object with no own properties.""",
    """          1. If _Desc_ has a [[Value]] field, then""",
    """            1. Perform ! CreateDataPropertyOrThrow(_obj_, *"value"*, _Desc_.[[Value]]).""",
    """          1. If _Desc_ has a [[Writable]] field, then""",
    """            1. Perform ! CreateDataPropertyOrThrow(_obj_, *"writable"*, _Desc_.[[Writable]]).""",
    """          1. If _Desc_ has a [[Get]] field, then""",
    """            1. Perform ! CreateDataPropertyOrThrow(_obj_, *"get"*, _Desc_.[[Get]]).""",
    """          1. If _Desc_ has a [[Set]] field, then""",
    """            1. Perform ! CreateDataPropertyOrThrow(_obj_, *"set"*, _Desc_.[[Set]]).""",
    """          1. If _Desc_ has an [[Enumerable]] field, then""",
    """            1. Perform ! CreateDataPropertyOrThrow(_obj_, *"enumerable"*, _Desc_.[[Enumerable]]).""",
    """          1. If _Desc_ has a [[Configurable]] field, then""",
    """            1. Perform ! CreateDataPropertyOrThrow(_obj_, *"configurable"*, _Desc_.[[Configurable]]).""",
    """          1. Return _obj_.""",
  )
}
