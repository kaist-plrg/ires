package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Object.prototype.hasOwnProperty` extends Algo {
  val head = BuiltinHead(parseRef("""Object.prototype.hasOwnProperty"""), List(Param("V", Normal)))
  val ids = List(
    "sec-object.prototype.hasownproperty",
    "sec-properties-of-the-object-prototype-object",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToPropertyKey V)
  |  0:let P = [? __x0__]
  |  1:app __x1__ = (ToObject this)
  |  1:let O = [? __x1__]
  |  2:app __x2__ = (HasOwnProperty O P)
  |  2:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. [id="step-hasownproperty-topropertykey"] Let _P_ be ? ToPropertyKey(_V_).""",
    """          1. [id="step-hasownproperty-toobject"] Let _O_ be ? ToObject(*this* value).""",
    """          1. Return ? HasOwnProperty(_O_, _P_).""",
  )
}
