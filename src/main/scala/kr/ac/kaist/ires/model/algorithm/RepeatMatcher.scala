package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RepeatMatcher` extends Algo {
  val head = NormalHead("RepeatMatcher", List(Param("m", Normal), Param("min", Normal), Param("max", Normal), Param("greedy", Normal), Param("x", Normal), Param("c", Normal), Param("parenIndex", Normal), Param("parenCount", Normal)))
  val ids = List(
    "sec-runtime-semantics-repeatmatcher-abstract-operation",
    "sec-term",
    "sec-pattern-semantics",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:if (== max 0i) {
  |    app __x0__ = (c x)
  |    return __x0__
  |  } else 2:{}
  |  1:??? "Let id:{d} be a new Continuation with parameters ( id:{y} ) that captures id:{m} , id:{min} , id:{max} , id:{greedy} , id:{x} , id:{c} , id:{parenIndex} , and id:{parenCount} and performs the following steps when called : in:{} out:{}"
  |  7:let cap = (copy-obj x.captures)
  |  8:??? "For each integer id:{k} such that id:{parenIndex} < id:{k} and id:{k} ≤ id:{parenIndex} + id:{parenCount} , set id:{cap} [ id:{k} ] to value:{undefined} ."
  |  9:let e = x.endIndex
  |  10:let xr = (new [e, cap])
  |  11:if (! (== min 0i)) {
  |    app __x1__ = (m xr d)
  |    return __x1__
  |  } else 2:{}
  |  12:if (= greedy false) {
  |    13:app __x2__ = (c x)
  |    13:let z = __x2__
  |    14:if (! (= z CONST_failure)) return z else 2:{}
  |    15:app __x3__ = (m xr d)
  |    15:return __x3__
  |  } else 2:{}
  |  16:app __x4__ = (m xr d)
  |  16:let z = __x4__
  |  17:if (! (= z CONST_failure)) return z else 2:{}
  |  18:app __x5__ = (c x)
  |  18:return __x5__
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _max_ = 0, return _c_(_x_).""",
    """            1. Let _d_ be a new Continuation with parameters (_y_) that captures _m_, _min_, _max_, _greedy_, _x_, _c_, _parenIndex_, and _parenCount_ and performs the following steps when called:""",
    """              1. Assert: _y_ is a State.""",
    """              1. [id="step-repeatmatcher-done"] If _min_ = 0 and _y_'s _endIndex_ = _x_'s _endIndex_, return ~failure~.""",
    """              1. If _min_ = 0, let _min2_ be 0; otherwise let _min2_ be _min_ - 1.""",
    """              1. If _max_ is +∞, let _max2_ be +∞; otherwise let _max2_ be _max_ - 1.""",
    """              1. Return ! RepeatMatcher(_m_, _min2_, _max2_, _greedy_, _y_, _c_, _parenIndex_, _parenCount_).""",
    """            1. Let _cap_ be a copy of _x_'s _captures_ List.""",
    """            1. [id="step-repeatmatcher-clear-captures"] For each integer _k_ such that _parenIndex_ < _k_ and _k_ ≤ _parenIndex_ + _parenCount_, set _cap_[_k_] to *undefined*.""",
    """            1. Let _e_ be _x_'s _endIndex_.""",
    """            1. Let _xr_ be the State (_e_, _cap_).""",
    """            1. If _min_ ≠ 0, return _m_(_xr_, _d_).""",
    """            1. If _greedy_ is *false*, then""",
    """              1. Let _z_ be _c_(_x_).""",
    """              1. If _z_ is not ~failure~, return _z_.""",
    """              1. Return _m_(_xr_, _d_).""",
    """            1. Let _z_ be _m_(_xr_, _d_).""",
    """            1. If _z_ is not ~failure~, return _z_.""",
    """            1. Return _c_(_x_).""",
  )
}
