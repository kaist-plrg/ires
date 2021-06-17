package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Boolean` extends Algo {
  val head = BuiltinHead(parseRef("""Boolean"""), List(Param("value", Normal)))
  val ids = List(
    "sec-boolean-constructor-boolean-value",
    "sec-boolean-constructor",
    "sec-boolean-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToBoolean value)
  |  0:let b = [! __x0__]
  |  1:if (= NewTarget undefined) return b else 73:{}
  |  2:app __x1__ = (OrdinaryCreateFromConstructor NewTarget "%Boolean.prototype%" (new ["BooleanData"]))
  |  2:let O = [? __x1__]
  |  3:O.BooleanData = b
  |  4:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _b_ be ! ToBoolean(_value_).""",
    """          1. If NewTarget is *undefined*, return _b_.""",
    """          1. Let _O_ be ? OrdinaryCreateFromConstructor(NewTarget, *"%Boolean.prototype%"*, « [[BooleanData]] »).""",
    """          1. Set _O_.[[BooleanData]] to _b_.""",
    """          1. Return _O_.""",
  )
}
