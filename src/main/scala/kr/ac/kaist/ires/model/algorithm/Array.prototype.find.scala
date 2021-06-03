package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.find` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.find"""), List(Param("predicate", Normal), Param("thisArg", Optional)))
  val ids = List(
    "sec-array.prototype.find",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  2:app __x2__ = (IsCallable predicate)
  |  2:if (= __x2__ false) throw TypeError else 19:{}
  |  3:let k = 0i
  |  4:while (< k len) {
  |    5:app __x3__ = (ToString k)
  |    5:let Pk = [! __x3__]
  |    6:app __x4__ = (Get O Pk)
  |    6:let kValue = [? __x4__]
  |    7:app __x5__ = (Call predicate thisArg (new [kValue, k, O]))
  |    7:app __x6__ = (ToBoolean [? __x5__])
  |    7:let testResult = [! __x6__]
  |    8:if (= testResult true) return kValue else 19:{}
  |    9:k = (+ k 1i)
  |  }
  |  10:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. If IsCallable(_predicate_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _k_ be 0.""",
    """          1. Repeat, while _k_ < _len_,""",
    """            1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """            1. Let _kValue_ be ? Get(_O_, _Pk_).""",
    """            1. Let _testResult_ be ! ToBoolean(? Call(_predicate_, _thisArg_, « _kValue_, 𝔽(_k_), _O_ »)).""",
    """            1. If _testResult_ is *true*, return _kValue_.""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Return *undefined*.""",
  )
}
