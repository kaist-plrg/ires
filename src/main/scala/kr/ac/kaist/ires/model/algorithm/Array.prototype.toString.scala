package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.toString` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.toString"""), List())
  val ids = List(
    "sec-array.prototype.tostring",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let array = [? __x0__]
  |  1:app __x1__ = (Get array "join")
  |  1:let func = [? __x1__]
  |  2:app __x2__ = (IsCallable func)
  |  2:if (= __x2__ false) func = INTRINSIC_Object_prototype_toString else 25:{}
  |  3:app __x3__ = (Call func array)
  |  3:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _array_ be ? ToObject(*this* value).""",
    """          1. Let _func_ be ? Get(_array_, *"join"*).""",
    """          1. If IsCallable(_func_) is *false*, set _func_ to the intrinsic function %Object.prototype.toString%.""",
    """          1. Return ? Call(_func_, _array_).""",
  )
}
