package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number::equal` extends Algo {
  val head = NormalHead("Number::equal", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-numeric-types-number-equal",
    "sec-ecmascript-language-types-number-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (= x NaN) return false else 9:{}
  |  1:if (= y NaN) return false else 9:{}
  |  2:if (= x y) return true else 9:{}
  |  3:if (&& (= x 0i) (= y -0.0)) return true else 9:{}
  |  4:if (&& (= x -0.0) (= y 0i)) return true else 9:{}
  |  5:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _x_ is *NaN*, return *false*.""",
    """            1. If _y_ is *NaN*, return *false*.""",
    """            1. If _x_ is the same Number value as _y_, return *true*.""",
    """            1. If _x_ is *+0*<sub>𝔽</sub> and _y_ is *-0*<sub>𝔽</sub>, return *true*.""",
    """            1. If _x_ is *-0*<sub>𝔽</sub> and _y_ is *+0*<sub>𝔽</sub>, return *true*.""",
    """            1. Return *false*.""",
  )
}
