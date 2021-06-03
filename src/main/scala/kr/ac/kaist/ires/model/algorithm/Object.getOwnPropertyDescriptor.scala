package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.getOwnPropertyDescriptor` extends Algo {
  val head = BuiltinHead(parseRef("""Object.getOwnPropertyDescriptor"""), List(Param("O", Normal), Param("P", Normal)))
  val ids = List(
    "sec-object.getownpropertydescriptor",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject O)
  |  0:let obj = [? __x0__]
  |  1:app __x1__ = (ToPropertyKey P)
  |  1:let key = [? __x1__]
  |  2:app __x2__ = (obj.GetOwnProperty obj key)
  |  2:let desc = [? __x2__]
  |  3:app __x3__ = (FromPropertyDescriptor desc)
  |  3:return __x3__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _obj_ be ? ToObject(_O_).""",
    """          1. Let _key_ be ? ToPropertyKey(_P_).""",
    """          1. Let _desc_ be ? _obj_.[[GetOwnProperty]](_key_).""",
    """          1. Return FromPropertyDescriptor(_desc_).""",
  )
}
