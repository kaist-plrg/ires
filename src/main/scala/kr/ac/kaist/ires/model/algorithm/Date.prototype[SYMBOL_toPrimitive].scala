package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype[SYMBOL_toPrimitive]` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype[SYMBOL_toPrimitive]"""), List(Param("hint", Normal)))
  val ids = List(
    "sec-date.prototype-@@toprimitive",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:let O = this
  |  1:if (! (= (typeof O) Object)) throw TypeError else 13:{}
  |  6:if (|| (= hint "string") (= hint "default")) let tryFirst = CONST_string else if (= hint "number") let tryFirst = CONST_number else throw TypeError
  |  7:app __x0__ = (OrdinaryToPrimitive O tryFirst)
  |  7:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be the *this* value.""",
    """          1. If Type(_O_) is not Object, throw a *TypeError* exception.""",
    """          1. If _hint_ is *"string"* or *"default"*, then""",
    """            1. Let _tryFirst_ be ~string~.""",
    """          1. Else if _hint_ is *"number"*, then""",
    """            1. Let _tryFirst_ be ~number~.""",
    """          1. Else, throw a *TypeError* exception.""",
    """          1. Return ? OrdinaryToPrimitive(_O_, _tryFirst_).""",
  )
}
