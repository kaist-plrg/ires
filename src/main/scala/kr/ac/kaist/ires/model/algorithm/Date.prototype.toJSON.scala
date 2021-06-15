package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.toJSON` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.toJSON"""), List(Param("key", Normal)))
  val ids = List(
    "sec-date.prototype.tojson",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ToPrimitive O CONST_number)
  |  1:let tv = [? __x1__]
  |  2:if (&& (= (typeof tv) Number) (|| (= tv Infinity) (= tv -Infinity))) return null else 13:{}
  |  3:app __x2__ = (Invoke O "toISOString")
  |  3:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _tv_ be ? ToPrimitive(_O_, ~number~).""",
    """          1. If Type(_tv_) is Number and _tv_ is not finite, return *null*.""",
    """          1. Return ? Invoke(_O_, *"toISOString"*).""",
  )
}
