package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateRegExpStringIterator` extends Algo {
  val head = NormalHead("CreateRegExpStringIterator", List(Param("R", Normal), Param("S", Normal), Param("global", Normal), Param("fullUnicode", Normal)))
  val ids = List(
    "sec-createregexpstringiterator",
    "sec-regexp-string-iterator-objects",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof S) String)
  |  1:assert (= (typeof global) Boolean)
  |  2:assert (= (typeof fullUnicode) Boolean)
  |  3:??? "Let id:{closure} be a new Abstract Closure with no parameters that captures id:{R} , id:{S} , id:{global} , and id:{fullUnicode} and performs the following steps when called : in:{} out:{}"
  |  16:app __x0__ = (CreateIteratorFromClosure closure "%RegExpStringIteratorPrototype%" INTRINSIC_RegExpStringIteratorPrototype)
  |  16:return [! __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Type(_S_) is String.""",
    """          1. Assert: Type(_global_) is Boolean.""",
    """          1. Assert: Type(_fullUnicode_) is Boolean.""",
    """          1. Let _closure_ be a new Abstract Closure with no parameters that captures _R_, _S_, _global_, and _fullUnicode_ and performs the following steps when called:""",
    """            1. Repeat,""",
    """              1. Let _match_ be ? RegExpExec(_R_, _S_).""",
    """              1. If _match_ is *null*, return *undefined*.""",
    """              1. If _global_ is *false*, then""",
    """                1. Perform ? Yield(_match_).""",
    """                1. Return *undefined*.""",
    """              1. Let _matchStr_ be ? ToString(? Get(_match_, *"0"*)).""",
    """              1. If _matchStr_ is the empty String, then""",
    """                1. Let _thisIndex_ be ℝ(? ToLength(? Get(_R_, *"lastIndex"*))).""",
    """                1. Let _nextIndex_ be ! AdvanceStringIndex(_S_, _thisIndex_, _fullUnicode_).""",
    """                1. Perform ? Set(_R_, *"lastIndex"*, 𝔽(_nextIndex_), *true*).""",
    """              1. Perform ? Yield(_match_).""",
    """          1. Return ! CreateIteratorFromClosure(_closure_, *"%RegExpStringIteratorPrototype%"*, %RegExpStringIteratorPrototype%).""",
  )
}
