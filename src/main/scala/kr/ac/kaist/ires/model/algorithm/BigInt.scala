package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigInt` extends Algo {
  val head = BuiltinHead(parseRef("""BigInt"""), List(Param("value", Normal)))
  val ids = List(
    "sec-bigint-constructor-number-value",
    "sec-bigint-constructor",
    "sec-bigint-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= NewTarget undefined)) throw TypeError else 2:{}
  |  1:app __x0__ = (ToPrimitive value CONST_number)
  |  1:let prim = [? __x0__]
  |  3:if (= (typeof prim) Number) {
  |    app __x1__ = (NumberToBigInt prim)
  |    return [? __x1__]
  |  } else {
  |    app __x2__ = (ToBigInt value)
  |    return [? __x2__]
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is not *undefined*, throw a *TypeError* exception.""",
    """          1. Let _prim_ be ? ToPrimitive(_value_, ~number~).""",
    """          1. If Type(_prim_) is Number, return ? NumberToBigInt(_prim_).""",
    """          1. Otherwise, return ? ToBigInt(_value_).""",
  )
}
