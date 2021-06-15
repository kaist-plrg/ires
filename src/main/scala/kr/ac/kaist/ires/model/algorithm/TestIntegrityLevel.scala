package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TestIntegrityLevel` extends Algo {
  val head = NormalHead("TestIntegrityLevel", List(Param("O", Normal), Param("level", Normal)))
  val ids = List(
    "sec-testintegritylevel",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:assert (|| (= level CONST_sealed) (= level CONST_frozen))
  |  2:app __x0__ = (IsExtensible O)
  |  2:let extensible = [? __x0__]
  |  3:if (= extensible true) return false else 4:{}
  |  5:app __x1__ = (O.OwnPropertyKeys O)
  |  5:let keys = [? __x1__]
  |  6:let __x2__ = keys
  |  6:let __x3__ = 0i
  |  6:while (< __x3__ __x2__.length) {
  |    let k = __x2__[__x3__]
  |    7:app __x4__ = (O.GetOwnProperty O k)
  |    7:let currentDesc = [? __x4__]
  |    8:if (! (= currentDesc undefined)) {
  |      9:if (= currentDesc.Configurable true) return false else 4:{}
  |      10:let __x5__ = true
  |      10:__x5__ = (= level CONST_frozen)
  |      10:if __x5__ {
  |        app __x6__ = (IsDataDescriptor currentDesc)
  |        __x5__ = (= __x6__ true)
  |      } else 4:{}
  |      10:if __x5__ if (= currentDesc.Writable true) return false else 4:{} else 4:{}
  |    } else 4:{}
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  12:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_O_) is Object.""",
    """        1. Assert: _level_ is either ~sealed~ or ~frozen~.""",
    """        1. Let _extensible_ be ? IsExtensible(_O_).""",
    """        1. If _extensible_ is *true*, return *false*.""",
    """        1. NOTE: If the object is extensible, none of its properties are examined.""",
    """        1. Let _keys_ be ? _O_.[[OwnPropertyKeys]]().""",
    """        1. For each element _k_ of _keys_, do""",
    """          1. Let _currentDesc_ be ? _O_.[[GetOwnProperty]](_k_).""",
    """          1. If _currentDesc_ is not *undefined*, then""",
    """            1. If _currentDesc_.[[Configurable]] is *true*, return *false*.""",
    """            1. If _level_ is ~frozen~ and IsDataDescriptor(_currentDesc_) is *true*, then""",
    """              1. If _currentDesc_.[[Writable]] is *true*, return *false*.""",
    """        1. Return *true*.""",
  )
}
