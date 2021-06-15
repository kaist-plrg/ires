package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryToPrimitive` extends Algo {
  val head = NormalHead("OrdinaryToPrimitive", List(Param("O", Normal), Param("hint", Normal)))
  val ids = List(
    "sec-ordinarytoprimitive",
    "sec-toprimitive",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:assert (|| (= hint CONST_string) (= hint CONST_number))
  |  4:if (= hint CONST_string) let methodNames = (new ["toString", "valueOf"]) else let methodNames = (new ["valueOf", "toString"])
  |  6:let __x0__ = methodNames
  |  6:let __x1__ = 0i
  |  6:while (< __x1__ __x0__.length) {
  |    let name = __x0__[__x1__]
  |    7:app __x2__ = (Get O name)
  |    7:let method = [? __x2__]
  |    8:app __x3__ = (IsCallable method)
  |    8:if (= __x3__ true) {
  |      9:app __x4__ = (Call method O)
  |      9:let result = [? __x4__]
  |      10:if (! (= (typeof result) Object)) return result else 0:{}
  |    } else 0:{}
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  11:throw TypeError
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Type(_O_) is Object.""",
    """          1. Assert: _hint_ is either ~string~ or ~number~.""",
    """          1. If _hint_ is ~string~, then""",
    """            1. Let _methodNames_ be « *"toString"*, *"valueOf"* ».""",
    """          1. Else,""",
    """            1. Let _methodNames_ be « *"valueOf"*, *"toString"* ».""",
    """          1. For each element _name_ of _methodNames_, do""",
    """            1. Let _method_ be ? Get(_O_, _name_).""",
    """            1. If IsCallable(_method_) is *true*, then""",
    """              1. Let _result_ be ? Call(_method_, _O_).""",
    """              1. If Type(_result_) is not Object, return _result_.""",
    """          1. Throw a *TypeError* exception.""",
  )
}
