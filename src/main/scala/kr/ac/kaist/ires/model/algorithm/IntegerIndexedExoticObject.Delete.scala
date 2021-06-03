package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IntegerIndexedExoticObject.Delete` extends Algo {
  val head = MethodHead("IntegerIndexedExoticObject", "Delete", Param("O", Normal), List(Param("P", Normal)))
  val ids = List(
    "sec-integer-indexed-exotic-objects-delete-p",
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
  |      5:app __x2__ = (IsValidIntegerIndex O numericIndex)
  |      5:if (= [! __x2__] false) return true else return false
  |    } else 1:{}
  |  } else 1:{}
  |  6:app __x3__ = (OrdinaryDelete O P)
  |  6:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. Assert: _O_ is an Integer-Indexed exotic object.""",
    """          1. If Type(_P_) is String, then""",
    """            1. Let _numericIndex_ be ! CanonicalNumericIndexString(_P_).""",
    """            1. If _numericIndex_ is not *undefined*, then""",
    """              1. If ! IsValidIntegerIndex(_O_, _numericIndex_) is *false*, return *true*; else return *false*.""",
    """          1. Return ? OrdinaryDelete(_O_, _P_).""",
  )
}
