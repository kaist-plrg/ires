package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.is` extends Algo {
  val head = BuiltinHead(parseRef("""Object.is"""), List(Param("value1", Normal), Param("value2", Normal)))
  val ids = List(
    "sec-object.is",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (SameValue value1 value2)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return SameValue(_value1_, _value2_).""",
  )
}
