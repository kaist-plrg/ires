package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CharacterSetMatcher` extends Algo {
  val head = NormalHead("CharacterSetMatcher", List(Param("A", Normal), Param("invert", Normal), Param("direction", Normal)))
  val ids = List(
    "sec-runtime-semantics-charactersetmatcher-abstract-operation",
    "sec-atom",
    "sec-pattern-semantics",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""??? "Return a new Matcher with parameters ( id:{x} , id:{c} ) that captures id:{A} , id:{invert} , and id:{direction} and performs the following steps when called : in:{} out:{}"""".stripMargin)
  val code = scala.Array[String](
    """            1. Return a new Matcher with parameters (_x_, _c_) that captures _A_, _invert_, and _direction_ and performs the following steps when called:""",
    """              1. Assert: _x_ is a State.""",
    """              1. Assert: _c_ is a Continuation.""",
    """              1. Let _e_ be _x_'s _endIndex_.""",
    """              1. Let _f_ be _e_ + _direction_.""",
    """              1. If _f_ < 0 or _f_ > _InputLength_, return ~failure~.""",
    """              1. Let _index_ be min(_e_, _f_).""",
    """              1. Let _ch_ be the character _Input_[_index_].""",
    """              1. Let _cc_ be Canonicalize(_ch_).""",
    """              1. If there exists a member _a_ of _A_ such that Canonicalize(_a_) is _cc_, let _found_ be *true*. Otherwise, let _found_ be *false*.""",
    """              1. If _invert_ is *false* and _found_ is *false*, return ~failure~.""",
    """              1. If _invert_ is *true* and _found_ is *true*, return ~failure~.""",
    """              1. Let _cap_ be _x_'s _captures_ List.""",
    """              1. Let _y_ be the State (_f_, _cap_).""",
    """              1. Return _c_(_y_).""",
  )
}
