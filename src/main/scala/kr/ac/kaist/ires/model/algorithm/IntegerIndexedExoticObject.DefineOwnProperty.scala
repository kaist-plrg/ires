package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IntegerIndexedExoticObject.DefineOwnProperty` extends Algo {
  val head = MethodHead("IntegerIndexedExoticObject", "DefineOwnProperty", Param("O", Normal), List(Param("P", Normal), Param("Desc", Normal)))
  val ids = List(
    "sec-integer-indexed-exotic-objects-defineownproperty-p-desc",
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
  |      5:if (= [! __x2__] false) return false else 1:{}
  |      6:if (&& (! (= Desc.Configurable absent)) (= Desc.Configurable false)) return false else 1:{}
  |      7:if (&& (! (= Desc.Enumerable absent)) (= Desc.Enumerable false)) return false else 1:{}
  |      8:app __x3__ = (IsAccessorDescriptor Desc)
  |      8:if (= [! __x3__] true) return false else 1:{}
  |      9:if (&& (! (= Desc.Writable absent)) (= Desc.Writable false)) return false else 1:{}
  |      10:if (! (= Desc.Value absent)) {
  |        app __x4__ = (IntegerIndexedElementSet O numericIndex Desc.Value)
  |        [? __x4__]
  |      } else 1:{}
  |      11:return true
  |    } else 1:{}
  |  } else 1:{}
  |  12:app __x5__ = (OrdinaryDefineOwnProperty O P Desc)
  |  12:return [! __x5__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. Assert: _O_ is an Integer-Indexed exotic object.""",
    """          1. If Type(_P_) is String, then""",
    """            1. Let _numericIndex_ be ! CanonicalNumericIndexString(_P_).""",
    """            1. If _numericIndex_ is not *undefined*, then""",
    """              1. If ! IsValidIntegerIndex(_O_, _numericIndex_) is *false*, return *false*.""",
    """              1. If _Desc_ has a [[Configurable]] field and if _Desc_.[[Configurable]] is *false*, return *false*.""",
    """              1. If _Desc_ has an [[Enumerable]] field and if _Desc_.[[Enumerable]] is *false*, return *false*.""",
    """              1. If ! IsAccessorDescriptor(_Desc_) is *true*, return *false*.""",
    """              1. If _Desc_ has a [[Writable]] field and if _Desc_.[[Writable]] is *false*, return *false*.""",
    """              1. If _Desc_ has a [[Value]] field, perform ? IntegerIndexedElementSet(_O_, _numericIndex_, _Desc_.[[Value]]).""",
    """              1. Return *true*.""",
    """          1. Return ! OrdinaryDefineOwnProperty(_O_, _P_, _Desc_).""",
  )
}
