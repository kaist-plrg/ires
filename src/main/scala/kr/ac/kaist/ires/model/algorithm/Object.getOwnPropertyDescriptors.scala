package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.getOwnPropertyDescriptors` extends Algo {
  val head = BuiltinHead(parseRef("""Object.getOwnPropertyDescriptors"""), List(Param("O", Normal)))
  val ids = List(
    "sec-object.getownpropertydescriptors",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject O)
  |  0:let obj = [? __x0__]
  |  1:app __x1__ = (obj.OwnPropertyKeys obj)
  |  1:let ownKeys = [? __x1__]
  |  2:app __x2__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |  2:let descriptors = [! __x2__]
  |  3:let __x3__ = ownKeys
  |  3:let __x4__ = 0i
  |  3:while (< __x4__ __x3__.length) {
  |    let key = __x3__[__x4__]
  |    4:app __x5__ = (obj.GetOwnProperty obj key)
  |    4:let desc = [? __x5__]
  |    5:app __x6__ = (FromPropertyDescriptor desc)
  |    5:let descriptor = [! __x6__]
  |    6:if (! (= descriptor undefined)) {
  |      app __x7__ = (CreateDataPropertyOrThrow descriptors key descriptor)
  |      [! __x7__]
  |    } else 2:{}
  |    __x4__ = (+ __x4__ 1i)
  |  }
  |  7:return descriptors
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _obj_ be ? ToObject(_O_).""",
    """          1. Let _ownKeys_ be ? _obj_.[[OwnPropertyKeys]]().""",
    """          1. Let _descriptors_ be ! OrdinaryObjectCreate(%Object.prototype%).""",
    """          1. For each element _key_ of _ownKeys_, do""",
    """            1. Let _desc_ be ? _obj_.[[GetOwnProperty]](_key_).""",
    """            1. Let _descriptor_ be ! FromPropertyDescriptor(_desc_).""",
    """            1. If _descriptor_ is not *undefined*, perform ! CreateDataPropertyOrThrow(_descriptors_, _key_, _descriptor_).""",
    """          1. Return _descriptors_.""",
  )
}
