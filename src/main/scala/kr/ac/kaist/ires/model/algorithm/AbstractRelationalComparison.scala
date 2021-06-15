package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AbstractRelationalComparison` extends Algo {
  val head = NormalHead("AbstractRelationalComparison", List(Param("x", Normal), Param("y", Normal), Param("LeftFirst", Optional)))
  val ids = List(
    "sec-abstract-relational-comparison",
    "sec-testing-and-comparison-operations",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  3:if (= LeftFirst true) {
  |    1:app __x0__ = (ToPrimitive x CONST_number)
  |    1:let px = [? __x0__]
  |    2:app __x1__ = (ToPrimitive y CONST_number)
  |    2:let py = [? __x1__]
  |  } else {
  |    5:app __x2__ = (ToPrimitive y CONST_number)
  |    5:let py = [? __x2__]
  |    6:app __x3__ = (ToPrimitive x CONST_number)
  |    6:let px = [? __x3__]
  |  }
  |  14:if (&& (= (typeof px) String) (= (typeof py) String)) {
  |    8:app __x4__ = (IsStringPrefix py px)
  |    8:if (= __x4__ true) return false else 23:{}
  |    9:app __x5__ = (IsStringPrefix px py)
  |    9:if (= __x5__ true) return true else 23:{}
  |    10:??? "Let id:{k} be the smallest non - negative integer such that the code unit at index id:{k} within id:{px} is different from the code unit at index id:{k} within id:{py} . ( There must be such a id:{k} , for neither String is a prefix of the other . )"
  |    11:??? "Let id:{m} be the integer that is the numeric value of the code unit at index id:{k} within id:{px} ."
  |    12:??? "Let id:{n} be the integer that is the numeric value of the code unit at index id:{k} within id:{py} ."
  |    13:if (< m n) return true else return false
  |  } else {
  |    15:if (&& (= (typeof px) BigInt) (= (typeof py) String)) {
  |      16:app __x6__ = (StringToBigInt py)
  |      16:let ny = [! __x6__]
  |      17:if (= ny NaN) return undefined else 23:{}
  |      18:app __x7__ = (PRIMITIVE[BigInt].lessThan px ny)
  |      18:return __x7__
  |    } else 23:{}
  |    19:if (&& (= (typeof px) String) (= (typeof py) BigInt)) {
  |      20:app __x8__ = (StringToBigInt px)
  |      20:let nx = [! __x8__]
  |      21:if (= nx NaN) return undefined else 23:{}
  |      22:app __x9__ = (PRIMITIVE[BigInt].lessThan nx py)
  |      22:return __x9__
  |    } else 23:{}
  |    24:app __x10__ = (ToNumeric px)
  |    24:let nx = [! __x10__]
  |    25:app __x11__ = (ToNumeric py)
  |    25:let ny = [! __x11__]
  |    26:if (= (typeof nx) (typeof ny)) {
  |      app __x12__ = (PRIMITIVE[(typeof nx)].lessThan nx ny)
  |      return __x12__
  |    } else 23:{}
  |    27:assert (|| (&& (= (typeof nx) BigInt) (= (typeof ny) Number)) (&& (= (typeof nx) Number) (= (typeof ny) BigInt)))
  |    28:if (|| (= nx NaN) (= ny NaN)) return undefined else 23:{}
  |    29:if (|| (= nx -Infinity) (= ny Infinity)) return true else 23:{}
  |    30:if (|| (= nx Infinity) (= ny -Infinity)) return false else 23:{}
  |    31:if (< nx ny) return true else return false
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the _LeftFirst_ flag is *true*, then""",
    """          1. Let _px_ be ? ToPrimitive(_x_, ~number~).""",
    """          1. Let _py_ be ? ToPrimitive(_y_, ~number~).""",
    """        1. Else,""",
    """          1. NOTE: The order of evaluation needs to be reversed to preserve left to right evaluation.""",
    """          1. Let _py_ be ? ToPrimitive(_y_, ~number~).""",
    """          1. Let _px_ be ? ToPrimitive(_x_, ~number~).""",
    """        1. [id="step-arc-string-check"] If Type(_px_) is String and Type(_py_) is String, then""",
    """          1. If IsStringPrefix(_py_, _px_) is *true*, return *false*.""",
    """          1. If IsStringPrefix(_px_, _py_) is *true*, return *true*.""",
    """          1. Let _k_ be the smallest non-negative integer such that the code unit at index _k_ within _px_ is different from the code unit at index _k_ within _py_. (There must be such a _k_, for neither String is a prefix of the other.)""",
    """          1. Let _m_ be the integer that is the numeric value of the code unit at index _k_ within _px_.""",
    """          1. Let _n_ be the integer that is the numeric value of the code unit at index _k_ within _py_.""",
    """          1. If _m_ < _n_, return *true*. Otherwise, return *false*.""",
    """        1. Else,""",
    """          1. If Type(_px_) is BigInt and Type(_py_) is String, then""",
    """            1. Let _ny_ be ! StringToBigInt(_py_).""",
    """            1. If _ny_ is *NaN*, return *undefined*.""",
    """            1. Return BigInt::lessThan(_px_, _ny_).""",
    """          1. If Type(_px_) is String and Type(_py_) is BigInt, then""",
    """            1. Let _nx_ be ! StringToBigInt(_px_).""",
    """            1. If _nx_ is *NaN*, return *undefined*.""",
    """            1. Return BigInt::lessThan(_nx_, _py_).""",
    """          1. NOTE: Because _px_ and _py_ are primitive values, evaluation order is not important.""",
    """          1. Let _nx_ be ! ToNumeric(_px_).""",
    """          1. Let _ny_ be ! ToNumeric(_py_).""",
    """          1. If Type(_nx_) is the same as Type(_ny_), return Type(_nx_)::lessThan(_nx_, _ny_).""",
    """          1. Assert: Type(_nx_) is BigInt and Type(_ny_) is Number, or Type(_nx_) is Number and Type(_ny_) is BigInt.""",
    """          1. If _nx_ or _ny_ is *NaN*, return *undefined*.""",
    """          1. If _nx_ is *-∞*<sub>𝔽</sub> or _ny_ is *+∞*<sub>𝔽</sub>, return *true*.""",
    """          1. If _nx_ is *+∞*<sub>𝔽</sub> or _ny_ is *-∞*<sub>𝔽</sub>, return *false*.""",
    """          1. If ℝ(_nx_) < ℝ(_ny_), return *true*; otherwise return *false*.""",
  )
}
