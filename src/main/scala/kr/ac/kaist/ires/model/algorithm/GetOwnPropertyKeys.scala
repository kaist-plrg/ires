package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetOwnPropertyKeys` extends Algo {
  val head = NormalHead("GetOwnPropertyKeys", List(Param("O", Normal), Param("type", Normal)))
  val ids = List(
    "sec-getownpropertykeys",
    "sec-object.getownpropertysymbols",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject O)
  |  0:let obj = [? __x0__]
  |  1:app __x1__ = (obj.OwnPropertyKeys obj)
  |  1:let keys = [? __x1__]
  |  2:let nameList = (new [])
  |  3:let __x2__ = keys
  |  3:let __x3__ = 0i
  |  3:while (< __x3__ __x2__.length) {
  |    let nextKey = __x2__[__x3__]
  |    4:if (&& (= (typeof nextKey) Symbol) (|| (= type CONST_symbol) (&& (= (typeof nextKey) String) (= type CONST_string)))) append nextKey -> nameList else 2:{}
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  6:app __x4__ = (CreateArrayFromList nameList)
  |  6:return __x4__
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _obj_ be ? ToObject(_O_).""",
    """            1. Let _keys_ be ? _obj_.[[OwnPropertyKeys]]().""",
    """            1. Let _nameList_ be a new empty List.""",
    """            1. For each element _nextKey_ of _keys_, do""",
    """              1. If Type(_nextKey_) is Symbol and _type_ is ~symbol~ or Type(_nextKey_) is String and _type_ is ~string~, then""",
    """                1. Append _nextKey_ as the last element of _nameList_.""",
    """            1. Return CreateArrayFromList(_nameList_).""",
  )
}
