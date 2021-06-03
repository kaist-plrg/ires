package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AdvanceStringIndex` extends Algo {
  val head = NormalHead("AdvanceStringIndex", List(Param("S", Normal), Param("index", Normal), Param("unicode", Normal)))
  val ids = List(
    "sec-advancestringindex",
    "sec-regexp.prototype.exec",
    "sec-properties-of-the-regexp-prototype-object",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:assert (! (< (- (** 2.0 53i) 1i) index))
  |  1:if (= unicode false) return (+ index 1i) else 52:{}
  |  2:let length = S.length
  |  3:if (! (< (+ index 1i) length)) return (+ index 1i) else 52:{}
  |  4:app __x0__ = (CodePointAt S index)
  |  4:let cp = [! __x0__]
  |  5:return (+ index cp.CodeUnitCount)
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _index_ ≤ 2<sup>53</sup> - 1.""",
    """            1. If _unicode_ is *false*, return _index_ + 1.""",
    """            1. Let _length_ be the number of code units in _S_.""",
    """            1. If _index_ + 1 ≥ _length_, return _index_ + 1.""",
    """            1. Let _cp_ be ! CodePointAt(_S_, _index_).""",
    """            1. Return _index_ + _cp_.[[CodeUnitCount]].""",
  )
}
