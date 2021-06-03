package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IntegerIndexedExoticObject.Set` extends Algo {
  val head = MethodHead("IntegerIndexedExoticObject", "Set", Param("O", Normal), List(Param("P", Normal), Param("V", Normal), Param("Receiver", Normal)))
  val ids = List(
    "sec-integer-indexed-exotic-objects-set-p-v-receiver",
    "sec-integer-indexed-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:if (= (typeof P) String) {
  |    2:app __x1__ = (CanonicalNumericIndexString P)
  |    2:let numericIndex = [! __x1__]
  |    3:if (! (= numericIndex undefined)) {
  |      4:app __x2__ = (IntegerIndexedElementSet O numericIndex V)
  |      4:[? __x2__]
  |      5:return true
  |    } else 1:{}
  |  } else 1:{}
  |  6:app __x3__ = (OrdinarySet O P V Receiver)
  |  6:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. If Type(_P_) is String, then""",
    """            1. Let _numericIndex_ be ! CanonicalNumericIndexString(_P_).""",
    """            1. If _numericIndex_ is not *undefined*, then""",
    """              1. Perform ? IntegerIndexedElementSet(_O_, _numericIndex_, _V_).""",
    """              1. Return *true*.""",
    """          1. Return ? OrdinarySet(_O_, _P_, _V_, _Receiver_).""",
  )
}
