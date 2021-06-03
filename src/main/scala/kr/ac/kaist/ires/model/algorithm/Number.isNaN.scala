package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number.isNaN` extends Algo {
  val head = BuiltinHead(parseRef("""Number.isNaN"""), List(Param("number", Normal)))
  val ids = List(
    "sec-number.isnan",
    "sec-properties-of-the-number-constructor",
    "sec-number-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof number) Number)) return false else 3:{}
  |  2:if (= number NaN) return true else return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_number_) is not Number, return *false*.""",
    """          1. If _number_ is *NaN*, return *true*.""",
    """          1. Otherwise, return *false*.""",
  )
}
