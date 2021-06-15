package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number::lessThan` extends Algo {
  val head = NormalHead("Number::lessThan", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-numeric-types-number-lessThan",
    "sec-ecmascript-language-types-number-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (= x NaN) return undefined else 9:{}
  |  1:if (= y NaN) return undefined else 9:{}
  |  2:if (= x y) return false else 9:{}
  |  3:if (&& (= x 0i) (= y -0.0)) return false else 9:{}
  |  4:if (&& (= x -0.0) (= y 0i)) return false else 9:{}
  |  5:if (= x Infinity) return false else 9:{}
  |  6:if (= y Infinity) return true else 9:{}
  |  7:if (= y -Infinity) return false else 9:{}
  |  8:if (= x -Infinity) return true else 9:{}
  |  10:if (< x y) return true else return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _x_ is *NaN*, return *undefined*.""",
    """            1. If _y_ is *NaN*, return *undefined*.""",
    """            1. If _x_ and _y_ are the same Number value, return *false*.""",
    """            1. If _x_ is *+0*<sub>𝔽</sub> and _y_ is *-0*<sub>𝔽</sub>, return *false*.""",
    """            1. If _x_ is *-0*<sub>𝔽</sub> and _y_ is *+0*<sub>𝔽</sub>, return *false*.""",
    """            1. If _x_ is *+∞*<sub>𝔽</sub>, return *false*.""",
    """            1. If _y_ is *+∞*<sub>𝔽</sub>, return *true*.""",
    """            1. If _y_ is *-∞*<sub>𝔽</sub>, return *false*.""",
    """            1. If _x_ is *-∞*<sub>𝔽</sub>, return *true*.""",
    """            1. Assert: _x_ and _y_ are finite and non-zero.""",
    """            1. If ℝ(_x_) < ℝ(_y_), return *true*. Otherwise, return *false*.""",
  )
}
