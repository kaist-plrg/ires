package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectDefineProperties` extends Algo {
  val head = NormalHead("ObjectDefineProperties", List(Param("O", Normal), Param("Properties", Normal)))
  val ids = List(
    "sec-objectdefineproperties",
    "sec-object.defineproperties",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:app __x0__ = (ToObject Properties)
  |  1:let props = [? __x0__]
  |  2:app __x1__ = (props.OwnPropertyKeys props)
  |  2:let keys = [? __x1__]
  |  3:let descriptors = (new [])
  |  4:let __x2__ = keys
  |  4:let __x3__ = 0i
  |  4:while (< __x3__ __x2__.length) {
  |    let nextKey = __x2__[__x3__]
  |    5:app __x4__ = (props.GetOwnProperty props nextKey)
  |    5:let propDesc = [? __x4__]
  |    6:if (&& (! (= propDesc undefined)) (= propDesc.Enumerable true)) {
  |      7:app __x5__ = (Get props nextKey)
  |      7:let descObj = [? __x5__]
  |      8:app __x6__ = (ToPropertyDescriptor descObj)
  |      8:let desc = [? __x6__]
  |      9:append (new [nextKey, desc]) -> descriptors
  |    } else 71:{}
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  10:let __x7__ = descriptors
  |  10:let __x8__ = 0i
  |  10:while (< __x8__ __x7__.length) {
  |    let pair = __x7__[__x8__]
  |    11:let P = pair[0i]
  |    12:let desc = pair[1i]
  |    13:app __x9__ = (DefinePropertyOrThrow O P desc)
  |    13:[? __x9__]
  |    __x8__ = (+ __x8__ 1i)
  |  }
  |  14:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: Type(_O_) is Object.""",
    """            1. Let _props_ be ? ToObject(_Properties_).""",
    """            1. Let _keys_ be ? _props_.[[OwnPropertyKeys]]().""",
    """            1. Let _descriptors_ be a new empty List.""",
    """            1. For each element _nextKey_ of _keys_, do""",
    """              1. Let _propDesc_ be ? _props_.[[GetOwnProperty]](_nextKey_).""",
    """              1. If _propDesc_ is not *undefined* and _propDesc_.[[Enumerable]] is *true*, then""",
    """                1. Let _descObj_ be ? Get(_props_, _nextKey_).""",
    """                1. Let _desc_ be ? ToPropertyDescriptor(_descObj_).""",
    """                1. Append the pair (a two element List) consisting of _nextKey_ and _desc_ to the end of _descriptors_.""",
    """            1. For each element _pair_ of _descriptors_, do""",
    """              1. Let _P_ be the first element of _pair_.""",
    """              1. Let _desc_ be the second element of _pair_.""",
    """              1. Perform ? DefinePropertyOrThrow(_O_, _P_, _desc_).""",
    """            1. Return _O_.""",
  )
}
