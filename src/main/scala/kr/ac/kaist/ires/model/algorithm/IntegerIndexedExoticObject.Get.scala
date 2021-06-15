package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IntegerIndexedExoticObject.Get` extends Algo {
  val head = MethodHead("IntegerIndexedExoticObject", "Get", Param("O", Normal), List(Param("P", Normal), Param("Receiver", Normal)))
  val ids = List(
    "sec-integer-indexed-exotic-objects-get-p-receiver",
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
  |      4:app __x2__ = (IntegerIndexedElementGet O numericIndex)
  |      4:return [! __x2__]
  |    } else 1:{}
  |  } else 1:{}
  |  5:app __x3__ = (OrdinaryGet O P Receiver)
  |  5:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. If Type(_P_) is String, then""",
    """            1. Let _numericIndex_ be ! CanonicalNumericIndexString(_P_).""",
    """            1. If _numericIndex_ is not *undefined*, then""",
    """              1. Return ! IntegerIndexedElementGet(_O_, _numericIndex_).""",
    """          1. Return ? OrdinaryGet(_O_, _P_, _Receiver_).""",
  )
}
