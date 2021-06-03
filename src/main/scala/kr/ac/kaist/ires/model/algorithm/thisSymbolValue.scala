package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::thisSymbolValue` extends Algo {
  val head = NormalHead("thisSymbolValue", List(Param("value", Normal)))
  val ids = List(
    "sec-properties-of-the-symbol-prototype-object",
    "sec-symbol-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (= (typeof value) Symbol) return value else 3:{}
  |  1:if (&& (= (typeof value) Object) (! (= value.SymbolData absent))) {
  |    2:let s = value.SymbolData
  |    3:assert (= (typeof s) Symbol)
  |    4:return s
  |  } else 3:{}
  |  5:throw TypeError
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_value_) is Symbol, return _value_.""",
    """        1. If Type(_value_) is Object and _value_ has a [[SymbolData]] internal slot, then""",
    """          1. Let _s_ be _value_.[[SymbolData]].""",
    """          1. Assert: Type(_s_) is Symbol.""",
    """          1. Return _s_.""",
    """        1. Throw a *TypeError* exception.""",
  )
}
