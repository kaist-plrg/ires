package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.prototype.isPrototypeOf` extends Algo {
  val head = BuiltinHead(parseRef("""Object.prototype.isPrototypeOf"""), List(Param("V", Normal)))
  val ids = List(
    "sec-object.prototype.isprototypeof",
    "sec-properties-of-the-object-prototype-object",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof V) Object)) return false else 2:{}
  |  1:app __x0__ = (ToObject this)
  |  1:let O = [? __x0__]
  |  2:while true {
  |    3:app __x1__ = (V.GetPrototypeOf V)
  |    3:V = [? __x1__]
  |    4:if (= V null) return false else 2:{}
  |    5:app __x2__ = (SameValue O V)
  |    5:if (= __x2__ true) return true else 2:{}
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. [id="step-isprototypeof-check-object"] If Type(_V_) is not Object, return *false*.""",
    """          1. [id="step-isprototypeof-toobject"] Let _O_ be ? ToObject(*this* value).""",
    """          1. Repeat,""",
    """            1. Set _V_ to ? _V_.[[GetPrototypeOf]]().""",
    """            1. If _V_ is *null*, return *false*.""",
    """            1. If SameValue(_O_, _V_) is *true*, return *true*.""",
  )
}
