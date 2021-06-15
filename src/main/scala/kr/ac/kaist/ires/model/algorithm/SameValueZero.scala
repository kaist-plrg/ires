package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SameValueZero` extends Algo {
  val head = NormalHead("SameValueZero", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-samevaluezero",
    "sec-testing-and-comparison-operations",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof x) (typeof y))) return false else 0:{}
  |  1:if (|| (= (typeof x) Number) (= (typeof x) BigInt)) {
  |    2:app __x0__ = (PRIMITIVE[(typeof x)].sameValueZero x y)
  |    2:return [! __x0__]
  |  } else 0:{}
  |  3:app __x1__ = (SameValueNonNumeric x y)
  |  3:return [! __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_x_) is different from Type(_y_), return *false*.""",
    """        1. If Type(_x_) is Number or BigInt, then""",
    """          1. Return ! Type(_x_)::sameValueZero(_x_, _y_).""",
    """        1. Return ! SameValueNonNumeric(_x_, _y_).""",
  )
}
