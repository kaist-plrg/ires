package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToLength` extends Algo {
  val head = NormalHead("ToLength", List(Param("argument", Normal)))
  val ids = List(
    "sec-tolength",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToIntegerOrInfinity argument)
  |  0:let len = [? __x0__]
  |  1:if (! (< 0i len)) return 0i else 0:{}
  |  2:app __x1__ = (min len (- (** 2.0 53i) 1i))
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _len_ be ? ToIntegerOrInfinity(_argument_).""",
    """        1. If _len_ ≤ 0, return *+0*<sub>𝔽</sub>.""",
    """        1. Return 𝔽(min(_len_, 2<sup>53</sup> - 1)).""",
  )
}
