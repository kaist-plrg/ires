package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number.isFinite` extends Algo {
  val head = BuiltinHead(parseRef("""Number.isFinite"""), List(Param("number", Normal)))
  val ids = List(
    "sec-number.isfinite",
    "sec-properties-of-the-number-constructor",
    "sec-number-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof number) Number)) return false else 3:{}
  |  2:if (|| (|| (= number NaN) (= number Infinity)) (= number -Infinity)) return false else return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_number_) is not Number, return *false*.""",
    """          1. If _number_ is *NaN*, *+∞*<sub>𝔽</sub>, or *-∞*<sub>𝔽</sub>, return *false*.""",
    """          1. Otherwise, return *true*.""",
  )
}
