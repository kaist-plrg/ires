package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Array.prototype.some` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.some"""), List(Param("callbackfn", Normal), Param("thisArg", Optional)))
  val ids = List(
    "sec-array.prototype.some",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  2:app __x2__ = (IsCallable callbackfn)
  |  2:if (= __x2__ false) throw TypeError else 25:{}
  |  3:let k = 0i
  |  4:while (< k len) {
  |    5:app __x3__ = (ToString k)
  |    5:let Pk = [! __x3__]
  |    6:app __x4__ = (HasProperty O Pk)
  |    6:let kPresent = [? __x4__]
  |    7:if (= kPresent true) {
  |      8:app __x5__ = (Get O Pk)
  |      8:let kValue = [? __x5__]
  |      9:app __x6__ = (Call callbackfn thisArg (new [kValue, k, O]))
  |      9:app __x7__ = (ToBoolean [? __x6__])
  |      9:let testResult = [! __x7__]
  |      10:if (= testResult true) return true else 25:{}
  |    } else 25:{}
  |    11:k = (+ k 1i)
  |  }
  |  12:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. If IsCallable(_callbackfn_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _k_ be 0.""",
    """          1. Repeat, while _k_ < _len_,""",
    """            1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """            1. Let _kPresent_ be ? HasProperty(_O_, _Pk_).""",
    """            1. If _kPresent_ is *true*, then""",
    """              1. Let _kValue_ be ? Get(_O_, _Pk_).""",
    """              1. Let _testResult_ be ! ToBoolean(? Call(_callbackfn_, _thisArg_, « _kValue_, 𝔽(_k_), _O_ »)).""",
    """              1. If _testResult_ is *true*, return *true*.""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Return *false*.""",
  )
}
