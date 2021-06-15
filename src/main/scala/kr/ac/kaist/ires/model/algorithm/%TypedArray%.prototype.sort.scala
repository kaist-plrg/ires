package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::%TypedArray%.prototype.sort` extends Algo {
  val head = NormalHead("%TypedArray%.prototype.sort", List(Param("comparefn", Normal)))
  val ids = List(
    "sec-%typedarray%.prototype.sort",
    "sec-properties-of-the-%typedarrayprototype%-object",
    "sec-typedarray-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  1:if (! (= comparefn undefined)) {
  |    2:app __x0__ = (Call comparefn undefined (new [x, y]))
  |    2:app __x1__ = (ToNumber [? __x0__])
  |    2:let v = [? __x1__]
  |    3:app __x2__ = (IsDetachedBuffer buffer)
  |    3:if (= __x2__ true) throw TypeError else 0:{}
  |    4:if (= v NaN) return 0i else 0:{}
  |    5:return v
  |  } else 0:{}
  |  6:if (&& (= x NaN) (= y NaN)) return 0i else 0:{}
  |  7:if (= x NaN) return 1i else 0:{}
  |  8:if (= y NaN) return -1i else 0:{}
  |  9:if (< x y) return -1i else 0:{}
  |  10:if (< y x) return 1i else 0:{}
  |  11:if (&& (= x -0.0) (= y 0i)) return -1i else 0:{}
  |  12:if (&& (= x 0i) (= y -0.0)) return 1i else 0:{}
  |  13:return 0i
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Both Type(_x_) and Type(_y_) are Number or both are BigInt.""",
    """          1. If _comparefn_ is not *undefined*, then""",
    """            1. Let _v_ be ? ToNumber(? Call(_comparefn_, *undefined*, « _x_, _y_ »)).""",
    """            1. If IsDetachedBuffer(_buffer_) is *true*, throw a *TypeError* exception.""",
    """            1. If _v_ is *NaN*, return *+0*<sub>𝔽</sub>.""",
    """            1. Return _v_.""",
    """          1. If _x_ and _y_ are both *NaN*, return *+0*<sub>𝔽</sub>.""",
    """          1. If _x_ is *NaN*, return *1*<sub>𝔽</sub>.""",
    """          1. If _y_ is *NaN*, return *-1*<sub>𝔽</sub>.""",
    """          1. If _x_ < _y_, return *-1*<sub>𝔽</sub>.""",
    """          1. If _x_ > _y_, return *1*<sub>𝔽</sub>.""",
    """          1. If _x_ is *-0*<sub>𝔽</sub> and _y_ is *+0*<sub>𝔽</sub>, return *-1*<sub>𝔽</sub>.""",
    """          1. If _x_ is *+0*<sub>𝔽</sub> and _y_ is *-0*<sub>𝔽</sub>, return *1*<sub>𝔽</sub>.""",
    """          1. Return *+0*<sub>𝔽</sub>.""",
  )
}
