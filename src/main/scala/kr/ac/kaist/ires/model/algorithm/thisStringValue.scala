package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::thisStringValue` extends Algo {
  val head = NormalHead("thisStringValue", List(Param("value", Normal)))
  val ids = List(
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:if (= (typeof value) String) return value else 6:{}
  |  1:if (&& (= (typeof value) Object) (! (= value.StringData absent))) {
  |    2:let s = value.StringData
  |    3:assert (= (typeof s) String)
  |    4:return s
  |  } else 6:{}
  |  5:throw TypeError
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_value_) is String, return _value_.""",
    """        1. If Type(_value_) is Object and _value_ has a [[StringData]] internal slot, then""",
    """          1. Let _s_ be _value_.[[StringData]].""",
    """          1. Assert: Type(_s_) is String.""",
    """          1. Return _s_.""",
    """        1. Throw a *TypeError* exception.""",
  )
}
