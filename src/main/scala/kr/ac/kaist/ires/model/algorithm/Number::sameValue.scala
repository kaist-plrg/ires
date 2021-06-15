package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number::sameValue` extends Algo {
  val head = NormalHead("Number::sameValue", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-numeric-types-number-sameValue",
    "sec-ecmascript-language-types-number-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (&& (= x NaN) (= y NaN)) return true else 9:{}
  |  1:if (&& (= x 0i) (= y -0.0)) return false else 9:{}
  |  2:if (&& (= x -0.0) (= y 0i)) return false else 9:{}
  |  3:if (= x y) return true else 9:{}
  |  4:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _x_ is *NaN* and _y_ is *NaN*, return *true*.""",
    """            1. If _x_ is *+0*<sub>𝔽</sub> and _y_ is *-0*<sub>𝔽</sub>, return *false*.""",
    """            1. If _x_ is *-0*<sub>𝔽</sub> and _y_ is *+0*<sub>𝔽</sub>, return *false*.""",
    """            1. If _x_ is the same Number value as _y_, return *true*.""",
    """            1. Return *false*.""",
  )
}
