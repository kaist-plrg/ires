package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IntegerIndexedExoticObject.OwnPropertyKeys` extends Algo {
  val head = MethodHead("IntegerIndexedExoticObject", "OwnPropertyKeys", Param("O", Normal), List())
  val ids = List(
    "sec-integer-indexed-exotic-objects-ownpropertykeys",
    "sec-integer-indexed-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let keys = (new [])
  |  2:app __x0__ = (IsDetachedBuffer O.ViewedArrayBuffer)
  |  2:if (= __x0__ false) {
  |    3:let i = (+ 0i 0i)
  |    3:let __x1__ = (+ O.ArrayLength 0i)
  |    3:while (< i __x1__) {
  |      app __x2__ = (ToString i)
  |      append [! __x2__] -> keys
  |    }
  |  } else 1:{}
  |  5:??? "For each own property key id:{P} of id:{O} such that Type ( id:{P} ) is String and id:{P} is not an integer index , in ascending chronological order of property creation , do in:{} out:{}"
  |  7:??? "For each own property key id:{P} of id:{O} such that Type ( id:{P} ) is Symbol , in ascending chronological order of property creation , do in:{} out:{}"
  |  9:return keys
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _keys_ be a new empty List.""",
    """          1. Assert: _O_ is an Integer-Indexed exotic object.""",
    """          1. If IsDetachedBuffer(_O_.[[ViewedArrayBuffer]]) is *false*, then""",
    """            1. For each integer _i_ starting with 0 such that _i_ < _O_.[[ArrayLength]], in ascending order, do""",
    """              1. Add ! ToString(𝔽(_i_)) as the last element of _keys_.""",
    """          1. For each own property key _P_ of _O_ such that Type(_P_) is String and _P_ is not an integer index, in ascending chronological order of property creation, do""",
    """            1. Add _P_ as the last element of _keys_.""",
    """          1. For each own property key _P_ of _O_ such that Type(_P_) is Symbol, in ascending chronological order of property creation, do""",
    """            1. Add _P_ as the last element of _keys_.""",
    """          1. Return _keys_.""",
  )
}
