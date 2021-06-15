package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SortCompare` extends Algo {
  val head = NormalHead("SortCompare", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-sortcompare",
    "sec-array.prototype.sort",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:if (&& (= x undefined) (= y undefined)) return 0i else 25:{}
  |  1:if (= x undefined) return 1i else 25:{}
  |  2:if (= y undefined) return -1i else 25:{}
  |  3:if (! (= comparefn undefined)) {
  |    4:app __x0__ = (Call comparefn undefined (new [x, y]))
  |    4:app __x1__ = (ToNumber [? __x0__])
  |    4:let v = [? __x1__]
  |    5:if (= v NaN) return 0i else 25:{}
  |    6:return v
  |  } else 25:{}
  |  7:app __x2__ = (ToString x)
  |  7:let xString = [? __x2__]
  |  8:app __x3__ = (ToString y)
  |  8:let yString = [? __x3__]
  |  9:app __x4__ = (AbstractRelationalComparison xString yString)
  |  9:let xSmaller = __x4__
  |  10:if (= xSmaller true) return -1i else 25:{}
  |  11:app __x5__ = (AbstractRelationalComparison yString xString)
  |  11:let ySmaller = __x5__
  |  12:if (= ySmaller true) return 1i else 25:{}
  |  13:return 0i
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _x_ and _y_ are both *undefined*, return *+0*<sub>𝔽</sub>.""",
    """            1. If _x_ is *undefined*, return *1*<sub>𝔽</sub>.""",
    """            1. If _y_ is *undefined*, return *-1*<sub>𝔽</sub>.""",
    """            1. If _comparefn_ is not *undefined*, then""",
    """              1. Let _v_ be ? ToNumber(? Call(_comparefn_, *undefined*, « _x_, _y_ »)).""",
    """              1. If _v_ is *NaN*, return *+0*<sub>𝔽</sub>.""",
    """              1. Return _v_.""",
    """            1. [id="step-sortcompare-tostring-x"] Let _xString_ be ? ToString(_x_).""",
    """            1. [id="step-sortcompare-tostring-y"] Let _yString_ be ? ToString(_y_).""",
    """            1. Let _xSmaller_ be the result of performing Abstract Relational Comparison _xString_ < _yString_.""",
    """            1. If _xSmaller_ is *true*, return *-1*<sub>𝔽</sub>.""",
    """            1. Let _ySmaller_ be the result of performing Abstract Relational Comparison _yString_ < _xString_.""",
    """            1. If _ySmaller_ is *true*, return *1*<sub>𝔽</sub>.""",
    """            1. Return *+0*<sub>𝔽</sub>.""",
  )
}
