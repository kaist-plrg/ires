package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype[SYMBOL_iterator]` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype[SYMBOL_iterator]"""), List())
  val ids = List(
    "sec-string.prototype-@@iterator",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ToString O)
  |  1:let s = [? __x1__]
  |  2:??? "Let id:{closure} be a new Abstract Closure with no parameters that captures id:{s} and performs the following steps when called : in:{} out:{}"
  |  12:app __x2__ = (CreateIteratorFromClosure closure "%StringIteratorPrototype%" INTRINSIC_StringIteratorPrototype)
  |  12:return [! __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _s_ be ? ToString(_O_).""",
    """          1. Let _closure_ be a new Abstract Closure with no parameters that captures _s_ and performs the following steps when called:""",
    """            1. Let _position_ be 0.""",
    """            1. Let _len_ be the length of _s_.""",
    """            1. Repeat, while _position_ < _len_,""",
    """              1. Let _cp_ be ! CodePointAt(_s_, _position_).""",
    """              1. Let _nextIndex_ be _position_ + _cp_.[[CodeUnitCount]].""",
    """              1. Let _resultString_ be the substring of _s_ from _position_ to _nextIndex_.""",
    """              1. Set _position_ to _nextIndex_.""",
    """              1. Perform ? Yield(_resultString_).""",
    """            1. Return *undefined*.""",
    """          1. Return ! CreateIteratorFromClosure(_closure_, *"%StringIteratorPrototype%"*, %StringIteratorPrototype%).""",
  )
}
