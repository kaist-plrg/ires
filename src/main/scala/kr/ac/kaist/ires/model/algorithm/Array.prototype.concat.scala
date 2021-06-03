package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.concat` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.concat"""), List(Param("items", Variadic)))
  val ids = List(
    "sec-array.prototype.concat",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ArraySpeciesCreate O 0i)
  |  1:let A = [? __x1__]
  |  2:let n = 0i
  |  3:??? "Prepend id:{O} to id:{items} ."
  |  4:let __x2__ = items
  |  4:let __x3__ = 0i
  |  4:while (< __x3__ __x2__.length) {
  |    let E = __x2__[__x3__]
  |    5:app __x4__ = (IsConcatSpreadable E)
  |    5:let spreadable = [? __x4__]
  |    18:if (= spreadable true) {
  |      7:let k = 0i
  |      8:app __x5__ = (LengthOfArrayLike E)
  |      8:let len = [? __x5__]
  |      9:if (< (- (** 2.0 53i) 1i) (+ n len)) throw TypeError else 19:{}
  |      10:while (< k len) {
  |        11:app __x6__ = (ToString k)
  |        11:let P = [! __x6__]
  |        12:app __x7__ = (HasProperty E P)
  |        12:let exists = [? __x7__]
  |        13:if (= exists true) {
  |          14:app __x8__ = (Get E P)
  |          14:let subElement = [? __x8__]
  |          15:app __x9__ = (ToString n)
  |          15:app __x10__ = (CreateDataPropertyOrThrow A [! __x9__] subElement)
  |          15:[? __x10__]
  |        } else 19:{}
  |        16:n = (+ n 1i)
  |        17:k = (+ k 1i)
  |      }
  |    } else {
  |      20:if (! (< n (- (** 2.0 53i) 1i))) throw TypeError else 19:{}
  |      21:app __x11__ = (ToString n)
  |      21:app __x12__ = (CreateDataPropertyOrThrow A [! __x11__] E)
  |      21:[? __x12__]
  |      22:n = (+ n 1i)
  |    }
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  23:app __x13__ = (Set A "length" n true)
  |  23:[? __x13__]
  |  24:return A
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _A_ be ? ArraySpeciesCreate(_O_, 0).""",
    """          1. Let _n_ be 0.""",
    """          1. Prepend _O_ to _items_.""",
    """          1. For each element _E_ of _items_, do""",
    """            1. Let _spreadable_ be ? IsConcatSpreadable(_E_).""",
    """            1. If _spreadable_ is *true*, then""",
    """              1. Let _k_ be 0.""",
    """              1. Let _len_ be ? LengthOfArrayLike(_E_).""",
    """              1. If _n_ + _len_ > 2<sup>53</sup> - 1, throw a *TypeError* exception.""",
    """              1. Repeat, while _k_ < _len_,""",
    """                1. Let _P_ be ! ToString(𝔽(_k_)).""",
    """                1. Let _exists_ be ? HasProperty(_E_, _P_).""",
    """                1. If _exists_ is *true*, then""",
    """                  1. Let _subElement_ be ? Get(_E_, _P_).""",
    """                  1. Perform ? CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_n_)), _subElement_).""",
    """                1. Set _n_ to _n_ + 1.""",
    """                1. Set _k_ to _k_ + 1.""",
    """            1. Else,""",
    """              1. NOTE: _E_ is added as a single item rather than spread.""",
    """              1. If _n_ ≥ 2<sup>53</sup> - 1, throw a *TypeError* exception.""",
    """              1. Perform ? CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_n_)), _E_).""",
    """              1. Set _n_ to _n_ + 1.""",
    """          1. [id="step-array-proto-concat-set-length"] Perform ? Set(_A_, *"length"*, 𝔽(_n_), *true*).""",
    """          1. Return _A_.""",
  )
}
