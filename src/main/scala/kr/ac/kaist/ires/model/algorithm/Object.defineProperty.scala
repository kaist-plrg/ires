package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.defineProperty` extends Algo {
  val head = BuiltinHead(parseRef("""Object.defineProperty"""), List(Param("O", Normal), Param("P", Normal), Param("Attributes", Normal)))
  val ids = List(
    "sec-object.defineproperty",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof O) Object)) throw TypeError else 71:{}
  |  1:app __x0__ = (ToPropertyKey P)
  |  1:let key = [? __x0__]
  |  2:app __x1__ = (ToPropertyDescriptor Attributes)
  |  2:let desc = [? __x1__]
  |  3:app __x2__ = (DefinePropertyOrThrow O key desc)
  |  3:[? __x2__]
  |  4:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_O_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _key_ be ? ToPropertyKey(_P_).""",
    """          1. Let _desc_ be ? ToPropertyDescriptor(_Attributes_).""",
    """          1. Perform ? DefinePropertyOrThrow(_O_, _key_, _desc_).""",
    """          1. Return _O_.""",
  )
}
