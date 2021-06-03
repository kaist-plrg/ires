package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String` extends Algo {
  val head = BuiltinHead(parseRef("""String"""), List(Param("value", Normal)))
  val ids = List(
    "sec-string-constructor-string-value",
    "sec-string-constructor",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  1:if (= value absent) let s = "" else {
  |    2:if (&& (= NewTarget undefined) (= (typeof value) Symbol)) {
  |      app __x0__ = (SymbolDescriptiveString value)
  |      return __x0__
  |    } else 13:{}
  |    3:app __x1__ = (ToString value)
  |    3:let s = [? __x1__]
  |  }
  |  4:if (= NewTarget undefined) return s else 13:{}
  |  5:app __x2__ = (GetPrototypeFromConstructor NewTarget "%String.prototype%")
  |  5:app __x3__ = (StringCreate s [? __x2__])
  |  5:return [! __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _value_ is not present, let _s_ be the empty String.""",
    """          1. Else,""",
    """            1. If NewTarget is *undefined* and Type(_value_) is Symbol, return SymbolDescriptiveString(_value_).""",
    """            1. Let _s_ be ? ToString(_value_).""",
    """          1. If NewTarget is *undefined*, return _s_.""",
    """          1. Return ! StringCreate(_s_, ? GetPrototypeFromConstructor(NewTarget, *"%String.prototype%"*)).""",
  )
}
