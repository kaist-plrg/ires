package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigInt::divide` extends Algo {
  val head = NormalHead("BigInt::divide", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-numeric-types-bigint-divide",
    "sec-ecmascript-language-types-bigint-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (= y 0i) throw RangeError else 9:{}
  |  1:let quotient = (/ x y)
  |  2:??? "Return the BigInt value that represents id:{quotient} rounded towards 0 to the next integer value ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _y_ is *0*<sub>ℤ</sub>, throw a *RangeError* exception.""",
    """            1. Let _quotient_ be ℝ(_x_) / ℝ(_y_).""",
    """            1. Return the BigInt value that represents _quotient_ rounded towards 0 to the next integer value.""",
  )
}
