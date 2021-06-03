package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AbstractEqualityComparison` extends Algo {
  val head = NormalHead("AbstractEqualityComparison", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-abstract-equality-comparison",
    "sec-testing-and-comparison-operations",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (= (typeof x) (typeof y)) {
  |    1:app __x0__ = (StrictEqualityComparison x y)
  |    1:return __x0__
  |  } else 4:{}
  |  2:if (&& (= x null) (= y undefined)) return true else 4:{}
  |  3:if (&& (= x undefined) (= y null)) return true else 4:{}
  |  5:if (&& (= (typeof x) Number) (= (typeof y) String)) {
  |    app __x1__ = (ToNumber y)
  |    app __x2__ = (AbstractEqualityComparison x [! __x1__])
  |    return __x2__
  |  } else 4:{}
  |  6:if (&& (= (typeof x) String) (= (typeof y) Number)) {
  |    app __x3__ = (ToNumber x)
  |    app __x4__ = (AbstractEqualityComparison [! __x3__] y)
  |    return __x4__
  |  } else 4:{}
  |  7:if (&& (= (typeof x) BigInt) (= (typeof y) String)) {
  |    8:app __x5__ = (StringToBigInt y)
  |    8:let n = [! __x5__]
  |    9:if (= n NaN) return false else 4:{}
  |    10:app __x6__ = (AbstractEqualityComparison x n)
  |    10:return __x6__
  |  } else 4:{}
  |  11:if (&& (= (typeof x) String) (= (typeof y) BigInt)) {
  |    app __x7__ = (AbstractEqualityComparison y x)
  |    return __x7__
  |  } else 4:{}
  |  12:if (= (typeof x) Boolean) {
  |    app __x8__ = (ToNumber x)
  |    app __x9__ = (AbstractEqualityComparison [! __x8__] y)
  |    return __x9__
  |  } else 4:{}
  |  13:if (= (typeof y) Boolean) {
  |    app __x10__ = (ToNumber y)
  |    app __x11__ = (AbstractEqualityComparison x [! __x10__])
  |    return __x11__
  |  } else 4:{}
  |  14:if (&& (|| (|| (|| (= (typeof x) String) (= (typeof x) Number)) (= (typeof x) BigInt)) (= (typeof x) Symbol)) (= (typeof y) Object)) {
  |    app __x12__ = (ToPrimitive y)
  |    app __x13__ = (AbstractEqualityComparison x [? __x12__])
  |    return __x13__
  |  } else 4:{}
  |  15:if (&& (= (typeof x) Object) (|| (|| (|| (= (typeof y) String) (= (typeof y) Number)) (= (typeof y) BigInt)) (= (typeof y) Symbol))) {
  |    app __x14__ = (ToPrimitive x)
  |    app __x15__ = (AbstractEqualityComparison [? __x14__] y)
  |    return __x15__
  |  } else 4:{}
  |  16:if (|| (&& (= (typeof x) BigInt) (= (typeof y) Number)) (&& (= (typeof x) Number) (= (typeof y) BigInt))) {
  |    17:if (|| (|| (|| (= x NaN) (= x Infinity)) (= x -Infinity)) (|| (|| (= y NaN) (= y Infinity)) (= y -Infinity))) return false else 4:{}
  |    18:if (== x y) return true else return false
  |  } else 4:{}
  |  19:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_x_) is the same as Type(_y_), then""",
    """          1. Return the result of performing Strict Equality Comparison _x_ === _y_.""",
    """        1. If _x_ is *null* and _y_ is *undefined*, return *true*.""",
    """        1. If _x_ is *undefined* and _y_ is *null*, return *true*.""",
    """        1. [id="step-abstract-equality-comparison-web-compat-insertion-point"] NOTE: This step is replaced in section <emu-xref href="#sec-IsHTMLDDA-internal-slot-aec"></emu-xref>.""",
    """        1. If Type(_x_) is Number and Type(_y_) is String, return the result of the comparison _x_ == ! ToNumber(_y_).""",
    """        1. If Type(_x_) is String and Type(_y_) is Number, return the result of the comparison ! ToNumber(_x_) == _y_.""",
    """        1. If Type(_x_) is BigInt and Type(_y_) is String, then""",
    """          1. Let _n_ be ! StringToBigInt(_y_).""",
    """          1. If _n_ is *NaN*, return *false*.""",
    """          1. Return the result of the comparison _x_ == _n_.""",
    """        1. If Type(_x_) is String and Type(_y_) is BigInt, return the result of the comparison _y_ == _x_.""",
    """        1. If Type(_x_) is Boolean, return the result of the comparison ! ToNumber(_x_) == _y_.""",
    """        1. If Type(_y_) is Boolean, return the result of the comparison _x_ == ! ToNumber(_y_).""",
    """        1. If Type(_x_) is either String, Number, BigInt, or Symbol and Type(_y_) is Object, return the result of the comparison _x_ == ? ToPrimitive(_y_).""",
    """        1. If Type(_x_) is Object and Type(_y_) is either String, Number, BigInt, or Symbol, return the result of the comparison ? ToPrimitive(_x_) == _y_.""",
    """        1. If Type(_x_) is BigInt and Type(_y_) is Number, or if Type(_x_) is Number and Type(_y_) is BigInt, then""",
    """          1. If _x_ or _y_ are any of *NaN*, *+∞*<sub>𝔽</sub>, or *-∞*<sub>𝔽</sub>, return *false*.""",
    """          1. If ℝ(_x_) = ℝ(_y_), return *true*; otherwise return *false*.""",
    """        1. Return *false*.""",
  )
}
