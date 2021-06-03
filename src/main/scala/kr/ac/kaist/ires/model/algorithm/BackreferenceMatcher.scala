package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BackreferenceMatcher` extends Algo {
  val head = NormalHead("BackreferenceMatcher", List(Param("n", Normal), Param("direction", Normal)))
  val ids = List(
    "sec-backreference-matcher",
    "sec-atomescape",
    "sec-pattern-semantics",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:assert (! (< n 1i))
  |  1:??? "Return a new Matcher with parameters ( id:{x} , id:{c} ) that captures id:{n} and id:{direction} and performs the following steps when called : in:{} out:{}"
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _n_ ≥ 1.""",
    """            1. Return a new Matcher with parameters (_x_, _c_) that captures _n_ and _direction_ and performs the following steps when called:""",
    """              1. Assert: _x_ is a State.""",
    """              1. Assert: _c_ is a Continuation.""",
    """              1. Let _cap_ be _x_'s _captures_ List.""",
    """              1. Let _s_ be _cap_[_n_].""",
    """              1. If _s_ is *undefined*, return _c_(_x_).""",
    """              1. Let _e_ be _x_'s _endIndex_.""",
    """              1. Let _len_ be the number of elements in _s_.""",
    """              1. Let _f_ be _e_ + _direction_ × _len_.""",
    """              1. If _f_ < 0 or _f_ > _InputLength_, return ~failure~.""",
    """              1. Let _g_ be min(_e_, _f_).""",
    """              1. If there exists an integer _i_ between 0 (inclusive) and _len_ (exclusive) such that Canonicalize(_s_[_i_]) is not the same character value as Canonicalize(_Input_[_g_ + _i_]), return ~failure~.""",
    """              1. Let _y_ be the State (_f_, _cap_).""",
    """              1. Return _c_(_y_).""",
  )
}
