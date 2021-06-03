package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Math.fround` extends Algo {
  val head = BuiltinHead(parseRef("""Math.fround"""), List(Param("x", Normal)))
  val ids = List(
    "sec-math.fround",
    "sec-function-properties-of-the-math-object",
    "sec-math-object",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber x)
  |  0:let n = [? __x0__]
  |  1:if (= n NaN) return NaN else 2:{}
  |  2:??? "If id:{n} is one of value:{+0} , value:{-0} , value:{+∞} , or value:{-∞} , return id:{n} ."
  |  3:??? "Let id:{n32} be the result of converting id:{n} to a value in IEEE 754 - 2019 binary32 format using roundTiesToEven mode ."
  |  4:??? "Let id:{n64} be the result of converting id:{n32} to a value in IEEE 754 - 2019 binary64 format ."
  |  5:return n64
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _n_ be ? ToNumber(_x_).""",
    """          1. If _n_ is *NaN*, return *NaN*.""",
    """          1. If _n_ is one of *+0*<sub>𝔽</sub>, *-0*<sub>𝔽</sub>, *+∞*<sub>𝔽</sub>, or *-∞*<sub>𝔽</sub>, return _n_.""",
    """          1. Let _n32_ be the result of converting _n_ to a value in IEEE 754-2019 binary32 format using roundTiesToEven mode.""",
    """          1. Let _n64_ be the result of converting _n32_ to a value in IEEE 754-2019 binary64 format.""",
    """          1. Return the ECMAScript Number value corresponding to _n64_.""",
  )
}
