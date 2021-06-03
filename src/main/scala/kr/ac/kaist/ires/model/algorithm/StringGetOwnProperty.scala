package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StringGetOwnProperty` extends Algo {
  val head = NormalHead("StringGetOwnProperty", List(Param("S", Normal), Param("P", Normal)))
  val ids = List(
    "sec-stringgetownproperty",
    "sec-string-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (IsPropertyKey P)
  |  1:assert (= __x0__ true)
  |  2:if (! (= (typeof P) String)) return undefined else 0:{}
  |  3:app __x1__ = (CanonicalNumericIndexString P)
  |  3:let index = [! __x1__]
  |  4:if (= index undefined) return undefined else 0:{}
  |  5:app __x2__ = (IsIntegralNumber index)
  |  5:if (= __x2__ false) return undefined else 0:{}
  |  6:if (= index -0.0) return undefined else 0:{}
  |  7:let str = S.StringData
  |  8:assert (= (typeof str) String)
  |  9:let len = str.length
  |  10:if (|| (< index 0i) (! (< index len))) return undefined else 0:{}
  |  11:??? "Let id:{resultStr} be the String value of length 1 , containing one code unit from id:{str} , specifically the code unit at index ℝ ( id:{index} ) ."
  |  12:return (new PropertyDescriptor("Value" -> resultStr, "Writable" -> false, "Enumerable" -> true, "Configurable" -> false))
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _S_ is an Object that has a [[StringData]] internal slot.""",
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. If Type(_P_) is not String, return *undefined*.""",
    """          1. Let _index_ be ! CanonicalNumericIndexString(_P_).""",
    """          1. If _index_ is *undefined*, return *undefined*.""",
    """          1. If IsIntegralNumber(_index_) is *false*, return *undefined*.""",
    """          1. If _index_ is *-0*<sub>𝔽</sub>, return *undefined*.""",
    """          1. Let _str_ be _S_.[[StringData]].""",
    """          1. Assert: Type(_str_) is String.""",
    """          1. Let _len_ be the length of _str_.""",
    """          1. If ℝ(_index_) < 0 or _len_ ≤ ℝ(_index_), return *undefined*.""",
    """          1. Let _resultStr_ be the String value of length 1, containing one code unit from _str_, specifically the code unit at index ℝ(_index_).""",
    """          1. Return the PropertyDescriptor { [[Value]]: _resultStr_, [[Writable]]: *false*, [[Enumerable]]: *true*, [[Configurable]]: *false* }.""",
  )
}
