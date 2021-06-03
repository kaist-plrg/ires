package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::isFinite` extends Algo {
  val head = BuiltinHead(parseRef("""isFinite"""), List(Param("number", Normal)))
  val ids = List(
    "sec-isfinite-number",
    "sec-function-properties-of-the-global-object",
    "sec-global-object",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber number)
  |  0:let num = [? __x0__]
  |  2:if (|| (|| (= num NaN) (= num Infinity)) (= num -Infinity)) return false else return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _num_ be ? ToNumber(_number_).""",
    """        1. If _num_ is *NaN*, *+∞*<sub>𝔽</sub>, or *-∞*<sub>𝔽</sub>, return *false*.""",
    """        1. Otherwise, return *true*.""",
  )
}
