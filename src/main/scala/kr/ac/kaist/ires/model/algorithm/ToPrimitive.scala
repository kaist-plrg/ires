package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToPrimitive` extends Algo {
  val head = NormalHead("ToPrimitive", List(Param("input", Normal), Param("preferredType", Optional)))
  val ids = List(
    "sec-toprimitive",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  1:if (= (typeof input) Object) {
  |    2:app __x0__ = (GetMethod input SYMBOL_toPrimitive)
  |    2:let exoticToPrim = [? __x0__]
  |    3:if (! (= exoticToPrim undefined)) {
  |      6:if (= preferredType absent) let hint = "default" else if (= preferredType CONST_string) let hint = "string" else {
  |        7:assert (= preferredType CONST_number)
  |        8:let hint = "number"
  |      }
  |      9:app __x1__ = (Call exoticToPrim input (new [hint]))
  |      9:let result = [? __x1__]
  |      10:if (! (= (typeof result) Object)) return result else 0:{}
  |      11:throw TypeError
  |    } else 0:{}
  |    12:if (= preferredType absent) let preferredType = CONST_number else 0:{}
  |    13:app __x2__ = (OrdinaryToPrimitive input preferredType)
  |    13:return [? __x2__]
  |  } else 0:{}
  |  14:return input
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _input_ is an ECMAScript language value.""",
    """        1. If Type(_input_) is Object, then""",
    """          1. Let _exoticToPrim_ be ? GetMethod(_input_, @@toPrimitive).""",
    """          1. If _exoticToPrim_ is not *undefined*, then""",
    """            1. If _preferredType_ is not present, let _hint_ be *"default"*.""",
    """            1. Else if _preferredType_ is ~string~, let _hint_ be *"string"*.""",
    """            1. Else,""",
    """              1. Assert: _preferredType_ is ~number~.""",
    """              1. Let _hint_ be *"number"*.""",
    """            1. Let _result_ be ? Call(_exoticToPrim_, _input_, « _hint_ »).""",
    """            1. If Type(_result_) is not Object, return _result_.""",
    """            1. Throw a *TypeError* exception.""",
    """          1. If _preferredType_ is not present, let _preferredType_ be ~number~.""",
    """          1. Return ? OrdinaryToPrimitive(_input_, _preferredType_).""",
    """        1. Return _input_.""",
  )
}
