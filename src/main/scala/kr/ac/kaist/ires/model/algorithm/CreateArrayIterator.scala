package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateArrayIterator` extends Algo {
  val head = NormalHead("CreateArrayIterator", List(Param("array", Normal), Param("kind", Normal)))
  val ids = List(
    "sec-createarrayiterator",
    "sec-array-iterator-objects",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof array) Object)
  |  1:assert (|| (|| (= kind CONST_keyPLUSvalue) (= kind CONST_key)) (= kind CONST_value))
  |  2:??? "Let id:{closure} be a new Abstract Closure with no parameters that captures id:{kind} and id:{array} and performs the following steps when called : in:{} out:{}"
  |  20:app __x0__ = (CreateIteratorFromClosure closure "%ArrayIteratorPrototype%" INTRINSIC_ArrayIteratorPrototype)
  |  20:return [! __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Type(_array_) is Object.""",
    """          1. Assert: _kind_ is ~key+value~, ~key~, or ~value~.""",
    """          1. Let _closure_ be a new Abstract Closure with no parameters that captures _kind_ and _array_ and performs the following steps when called:""",
    """            1. Let _index_ be 0.""",
    """            1. Repeat,""",
    """              1. If _array_ has a [[TypedArrayName]] internal slot, then""",
    """                1. If IsDetachedBuffer(_array_.[[ViewedArrayBuffer]]) is *true*, throw a *TypeError* exception.""",
    """                1. Let _len_ be _array_.[[ArrayLength]].""",
    """              1. Else,""",
    """                1. Let _len_ be ? LengthOfArrayLike(_array_).""",
    """              1. If _index_ ≥ _len_, return *undefined*.""",
    """              1. If _kind_ is ~key~, perform ? Yield(𝔽(_index_)).""",
    """              1. Else,""",
    """                1. Let _elementKey_ be ! ToString(𝔽(_index_)).""",
    """                1. Let _elementValue_ be ? Get(_array_, _elementKey_).""",
    """                1. If _kind_ is ~value~, perform ? Yield(_elementValue_).""",
    """                1. Else,""",
    """                  1. Assert: _kind_ is ~key+value~.""",
    """                  1. Perform ? Yield(! CreateArrayFromList(« 𝔽(_index_), _elementValue_ »)).""",
    """              1. Set _index_ to _index_ + 1.""",
    """          1. Return ! CreateIteratorFromClosure(_closure_, *"%ArrayIteratorPrototype%"*, %ArrayIteratorPrototype%).""",
  )
}
