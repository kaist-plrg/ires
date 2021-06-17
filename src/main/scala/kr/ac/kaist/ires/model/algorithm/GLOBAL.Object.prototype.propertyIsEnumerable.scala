package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Object.prototype.propertyIsEnumerable` extends Algo {
  val head = BuiltinHead(parseRef("""Object.prototype.propertyIsEnumerable"""), List(Param("V", Normal)))
  val ids = List(
    "sec-object.prototype.propertyisenumerable",
    "sec-properties-of-the-object-prototype-object",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToPropertyKey V)
  |  0:let P = [? __x0__]
  |  1:app __x1__ = (ToObject this)
  |  1:let O = [? __x1__]
  |  2:app __x2__ = (O.GetOwnProperty O P)
  |  2:let desc = [? __x2__]
  |  3:if (= desc undefined) return false else 2:{}
  |  4:return desc.Enumerable
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. [id="step-propertyisenumerable-topropertykey"] Let _P_ be ? ToPropertyKey(_V_).""",
    """          1. [id="step-propertyisenumerable-toobject"] Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _desc_ be ? _O_.[[GetOwnProperty]](_P_).""",
    """          1. If _desc_ is *undefined*, return *false*.""",
    """          1. Return _desc_.[[Enumerable]].""",
  )
}
