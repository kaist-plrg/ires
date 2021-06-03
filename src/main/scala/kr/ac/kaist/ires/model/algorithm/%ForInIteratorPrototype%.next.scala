package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::%ForInIteratorPrototype%.next` extends Algo {
  val head = NormalHead("%ForInIteratorPrototype%.next", List())
  val ids = List(
    "sec-%foriniteratorprototype%.next",
    "sec-%foriniteratorprototype%-object",
    "sec-for-in-iterator-objects",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let O = this
  |  1:assert (= (typeof O) Object)
  |  3:let object = O.Object
  |  4:let visited = O.VisitedKeys
  |  5:let remaining = O.RemainingKeys
  |  6:while true {
  |    7:if (= O.ObjectWasVisited false) {
  |      8:app __x0__ = (object.OwnPropertyKeys object)
  |      8:let keys = [? __x0__]
  |      9:let __x1__ = keys
  |      9:let __x2__ = 0i
  |      9:while (< __x2__ __x1__.length) {
  |        let key = __x1__[__x2__]
  |        10:if (= (typeof key) String) append key -> remaining else 2:{}
  |        __x2__ = (+ __x2__ 1i)
  |      }
  |      12:O.ObjectWasVisited = true
  |    } else 2:{}
  |    13:while (< 0i remaining.length) {
  |      14:let r = remaining[0i]
  |      15:let __x3__ = (pop remaining 0i)
  |      16:??? "If there does not exist an element id:{v} of id:{visited} such that SameValue ( id:{r} , id:{v} ) is value:{true} , then in:{} out:{}"
  |    }
  |    21:app __x4__ = (object.GetPrototypeOf object)
  |    21:object = [? __x4__]
  |    22:O.Object = object
  |    23:O.ObjectWasVisited = false
  |    24:if (= object null) {
  |      app __x5__ = (CreateIterResultObject undefined true)
  |      return __x5__
  |    } else 2:{}
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """              1. Let _O_ be the *this* value.""",
    """              1. Assert: Type(_O_) is Object.""",
    """              1. Assert: _O_ has all of the internal slots of a For-In Iterator Instance (<emu-xref href="#sec-properties-of-for-in-iterator-instances"></emu-xref>).""",
    """              1. Let _object_ be _O_.[[Object]].""",
    """              1. Let _visited_ be _O_.[[VisitedKeys]].""",
    """              1. Let _remaining_ be _O_.[[RemainingKeys]].""",
    """              1. Repeat,""",
    """                1. If _O_.[[ObjectWasVisited]] is *false*, then""",
    """                  1. Let _keys_ be ? _object_.[[OwnPropertyKeys]]().""",
    """                  1. For each element _key_ of _keys_, do""",
    """                    1. If Type(_key_) is String, then""",
    """                      1. Append _key_ to _remaining_.""",
    """                  1. Set _O_.[[ObjectWasVisited]] to *true*.""",
    """                1. Repeat, while _remaining_ is not empty,""",
    """                  1. Let _r_ be the first element of _remaining_.""",
    """                  1. Remove the first element from _remaining_.""",
    """                  1. If there does not exist an element _v_ of _visited_ such that SameValue(_r_, _v_) is *true*, then""",
    """                    1. Let _desc_ be ? _object_.[[GetOwnProperty]](_r_).""",
    """                    1. If _desc_ is not *undefined*, then""",
    """                      1. Append _r_ to _visited_.""",
    """                      1. If _desc_.[[Enumerable]] is *true*, return CreateIterResultObject(_r_, *false*).""",
    """                1. Set _object_ to ? _object_.[[GetPrototypeOf]]().""",
    """                1. Set _O_.[[Object]] to _object_.""",
    """                1. Set _O_.[[ObjectWasVisited]] to *false*.""",
    """                1. If _object_ is *null*, return CreateIterResultObject(*undefined*, *true*).""",
  )
}
