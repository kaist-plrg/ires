package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number.isSafeInteger` extends Algo {
  val head = BuiltinHead(parseRef("""Number.isSafeInteger"""), List(Param("number", Normal)))
  val ids = List(
    "sec-number.issafeinteger",
    "sec-properties-of-the-number-constructor",
    "sec-number-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsIntegralNumber number)
  |  0:if (= [! __x0__] true) {
  |    1:app __x1__ = (abs number)
  |    1:if (! (< (- (** 2.0 53i) 1i) __x1__)) return true else 3:{}
  |  } else 3:{}
  |  2:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If ! IsIntegralNumber(_number_) is *true*, then""",
    """            1. If abs(ℝ(_number_)) ≤ 2<sup>53</sup> - 1, return *true*.""",
    """          1. Return *false*.""",
  )
}
