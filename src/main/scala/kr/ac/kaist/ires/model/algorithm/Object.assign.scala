package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.assign` extends Algo {
  val head = BuiltinHead(parseRef("""Object.assign"""), List(Param("target", Normal), Param("sources", Variadic)))
  val ids = List(
    "sec-object.assign",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject target)
  |  0:let to = [? __x0__]
  |  1:if (= argumentsList.length 1i) return to else 71:{}
  |  2:let __x1__ = sources
  |  2:let __x2__ = 0i
  |  2:while (< __x2__ __x1__.length) {
  |    let nextSource = __x1__[__x2__]
  |    3:if (! (|| (= nextSource undefined) (= nextSource null))) {
  |      4:app __x3__ = (ToObject nextSource)
  |      4:let from = [! __x3__]
  |      5:app __x4__ = (from.OwnPropertyKeys from)
  |      5:let keys = [? __x4__]
  |      6:let __x5__ = keys
  |      6:let __x6__ = 0i
  |      6:while (< __x6__ __x5__.length) {
  |        let nextKey = __x5__[__x6__]
  |        7:app __x7__ = (from.GetOwnProperty from nextKey)
  |        7:let desc = [? __x7__]
  |        8:if (&& (! (= desc undefined)) (= desc.Enumerable true)) {
  |          9:app __x8__ = (Get from nextKey)
  |          9:let propValue = [? __x8__]
  |          10:app __x9__ = (Set to nextKey propValue true)
  |          10:[? __x9__]
  |        } else 71:{}
  |        __x6__ = (+ __x6__ 1i)
  |      }
  |    } else 71:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  11:return to
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _to_ be ? ToObject(_target_).""",
    """          1. If only one argument was passed, return _to_.""",
    """          1. For each element _nextSource_ of _sources_, do""",
    """            1. If _nextSource_ is neither *undefined* nor *null*, then""",
    """              1. Let _from_ be ! ToObject(_nextSource_).""",
    """              1. Let _keys_ be ? _from_.[[OwnPropertyKeys]]().""",
    """              1. For each element _nextKey_ of _keys_, do""",
    """                1. Let _desc_ be ? _from_.[[GetOwnProperty]](_nextKey_).""",
    """                1. If _desc_ is not *undefined* and _desc_.[[Enumerable]] is *true*, then""",
    """                  1. Let _propValue_ be ? Get(_from_, _nextKey_).""",
    """                  1. Perform ? Set(_to_, _nextKey_, _propValue_, *true*).""",
    """          1. Return _to_.""",
  )
}
