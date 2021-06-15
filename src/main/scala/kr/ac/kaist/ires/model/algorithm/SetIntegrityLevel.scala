package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SetIntegrityLevel` extends Algo {
  val head = NormalHead("SetIntegrityLevel", List(Param("O", Normal), Param("level", Normal)))
  val ids = List(
    "sec-setintegritylevel",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:assert (|| (= level CONST_sealed) (= level CONST_frozen))
  |  2:app __x0__ = (O.PreventExtensions O)
  |  2:let status = [? __x0__]
  |  3:if (= status false) return false else 4:{}
  |  4:app __x1__ = (O.OwnPropertyKeys O)
  |  4:let keys = [? __x1__]
  |  8:if (= level CONST_sealed) {
  |    6:let __x2__ = keys
  |    6:let __x3__ = 0i
  |    6:while (< __x3__ __x2__.length) {
  |      let k = __x2__[__x3__]
  |      7:app __x4__ = (DefinePropertyOrThrow O k (new PropertyDescriptor("Configurable" -> false)))
  |      7:[? __x4__]
  |      __x3__ = (+ __x3__ 1i)
  |    }
  |  } else {
  |    9:assert (= level CONST_frozen)
  |    10:let __x5__ = keys
  |    10:let __x6__ = 0i
  |    10:while (< __x6__ __x5__.length) {
  |      let k = __x5__[__x6__]
  |      11:app __x7__ = (O.GetOwnProperty O k)
  |      11:let currentDesc = [? __x7__]
  |      12:if (! (= currentDesc undefined)) {
  |        15:app __x8__ = (IsAccessorDescriptor currentDesc)
  |        15:if (= __x8__ true) let desc = (new PropertyDescriptor("Configurable" -> false)) else let desc = (new PropertyDescriptor("Configurable" -> false, "Writable" -> false))
  |        17:app __x9__ = (DefinePropertyOrThrow O k desc)
  |        17:[? __x9__]
  |      } else 4:{}
  |      __x6__ = (+ __x6__ 1i)
  |    }
  |  }
  |  18:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_O_) is Object.""",
    """        1. Assert: _level_ is either ~sealed~ or ~frozen~.""",
    """        1. Let _status_ be ? _O_.[[PreventExtensions]]().""",
    """        1. If _status_ is *false*, return *false*.""",
    """        1. Let _keys_ be ? _O_.[[OwnPropertyKeys]]().""",
    """        1. If _level_ is ~sealed~, then""",
    """          1. For each element _k_ of _keys_, do""",
    """            1. Perform ? DefinePropertyOrThrow(_O_, _k_, PropertyDescriptor { [[Configurable]]: *false* }).""",
    """        1. Else,""",
    """          1. Assert: _level_ is ~frozen~.""",
    """          1. For each element _k_ of _keys_, do""",
    """            1. Let _currentDesc_ be ? _O_.[[GetOwnProperty]](_k_).""",
    """            1. If _currentDesc_ is not *undefined*, then""",
    """              1. If IsAccessorDescriptor(_currentDesc_) is *true*, then""",
    """                1. Let _desc_ be the PropertyDescriptor { [[Configurable]]: *false* }.""",
    """              1. Else,""",
    """                1. Let _desc_ be the PropertyDescriptor { [[Configurable]]: *false*, [[Writable]]: *false* }.""",
    """              1. Perform ? DefinePropertyOrThrow(_O_, _k_, _desc_).""",
    """        1. Return *true*.""",
  )
}
