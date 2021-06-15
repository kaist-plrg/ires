package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.map` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.map"""), List(Param("callbackfn", Normal), Param("thisArg", Optional)))
  val ids = List(
    "sec-array.prototype.map",
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
  |  2:if (= __x2__ false) throw TypeError else 4:{}
  |  3:app __x3__ = (ArraySpeciesCreate O len)
  |  3:let A = [? __x3__]
  |  4:let k = 0i
  |  5:while (< k len) {
  |    6:app __x4__ = (ToString k)
  |    6:let Pk = [! __x4__]
  |    7:app __x5__ = (HasProperty O Pk)
  |    7:let kPresent = [? __x5__]
  |    8:if (= kPresent true) {
  |      9:app __x6__ = (Get O Pk)
  |      9:let kValue = [? __x6__]
  |      10:app __x7__ = (Call callbackfn thisArg (new [kValue, k, O]))
  |      10:let mappedValue = [? __x7__]
  |      11:app __x8__ = (CreateDataPropertyOrThrow A Pk mappedValue)
  |      11:[? __x8__]
  |    } else 4:{}
  |    12:k = (+ k 1i)
  |  }
  |  13:return A
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. If IsCallable(_callbackfn_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _A_ be ? ArraySpeciesCreate(_O_, _len_).""",
    """          1. Let _k_ be 0.""",
    """          1. Repeat, while _k_ < _len_,""",
    """            1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """            1. Let _kPresent_ be ? HasProperty(_O_, _Pk_).""",
    """            1. If _kPresent_ is *true*, then""",
    """              1. Let _kValue_ be ? Get(_O_, _Pk_).""",
    """              1. Let _mappedValue_ be ? Call(_callbackfn_, _thisArg_, « _kValue_, 𝔽(_k_), _O_ »).""",
    """              1. Perform ? CreateDataPropertyOrThrow(_A_, _Pk_, _mappedValue_).""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Return _A_.""",
  )
}
