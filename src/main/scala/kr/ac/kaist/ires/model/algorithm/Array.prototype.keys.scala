package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.keys` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.keys"""), List())
  val ids = List(
    "sec-array.prototype.keys",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (CreateArrayIterator O CONST_key)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Return CreateArrayIterator(_O_, ~key~).""",
  )
}
