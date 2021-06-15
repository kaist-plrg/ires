package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IntegerIndexedExoticObject.GetOwnProperty` extends Algo {
  val head = MethodHead("IntegerIndexedExoticObject", "GetOwnProperty", Param("O", Normal), List(Param("P", Normal)))
  val ids = List(
    "sec-integer-indexed-exotic-objects-getownproperty-p",
    "sec-integer-indexed-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  2:if (= (typeof P) String) {
  |    3:app __x1__ = (CanonicalNumericIndexString P)
  |    3:let numericIndex = [! __x1__]
  |    4:if (! (= numericIndex undefined)) {
  |      5:app __x2__ = (IntegerIndexedElementGet O numericIndex)
  |      5:let value = [! __x2__]
  |      6:if (= value undefined) return undefined else 1:{}
  |      7:return (new PropertyDescriptor("Value" -> value, "Writable" -> true, "Enumerable" -> true, "Configurable" -> true))
  |    } else 1:{}
  |  } else 1:{}
  |  8:app __x3__ = (OrdinaryGetOwnProperty O P)
  |  8:return __x3__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. Assert: _O_ is an Integer-Indexed exotic object.""",
    """          1. If Type(_P_) is String, then""",
    """            1. Let _numericIndex_ be ! CanonicalNumericIndexString(_P_).""",
    """            1. If _numericIndex_ is not *undefined*, then""",
    """              1. Let _value_ be ! IntegerIndexedElementGet(_O_, _numericIndex_).""",
    """              1. If _value_ is *undefined*, return *undefined*.""",
    """              1. Return the PropertyDescriptor { [[Value]]: _value_, [[Writable]]: *true*, [[Enumerable]]: *true*, [[Configurable]]: *true* }.""",
    """          1. Return OrdinaryGetOwnProperty(_O_, _P_).""",
  )
}
