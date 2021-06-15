package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StringExoticObject.OwnPropertyKeys` extends Algo {
  val head = MethodHead("StringExoticObject", "OwnPropertyKeys", Param("O", Normal), List())
  val ids = List(
    "sec-string-exotic-objects-ownpropertykeys",
    "sec-string-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let keys = (new [])
  |  1:let str = O.StringData
  |  2:assert (= (typeof str) String)
  |  3:let len = str.length
  |  4:let i = (+ 0i 0i)
  |  4:let __x0__ = (+ len 0i)
  |  4:while (< i __x0__) {
  |    app __x1__ = (ToString i)
  |    append [! __x1__] -> keys
  |  }
  |  6:??? "For each own property key id:{P} of id:{O} such that id:{P} is an array index and ! ToIntegerOrInfinity ( id:{P} ) ≥ id:{len} , in ascending numeric index order , do in:{} out:{}"
  |  8:??? "For each own property key id:{P} of id:{O} such that Type ( id:{P} ) is String and id:{P} is not an array index , in ascending chronological order of property creation , do in:{} out:{}"
  |  10:??? "For each own property key id:{P} of id:{O} such that Type ( id:{P} ) is Symbol , in ascending chronological order of property creation , do in:{} out:{}"
  |  12:return keys
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _keys_ be a new empty List.""",
    """          1. Let _str_ be _O_.[[StringData]].""",
    """          1. Assert: Type(_str_) is String.""",
    """          1. Let _len_ be the length of _str_.""",
    """          1. For each integer _i_ starting with 0 such that _i_ < _len_, in ascending order, do""",
    """            1. Add ! ToString(𝔽(_i_)) as the last element of _keys_.""",
    """          1. For each own property key _P_ of _O_ such that _P_ is an array index and ! ToIntegerOrInfinity(_P_) ≥ _len_, in ascending numeric index order, do""",
    """            1. Add _P_ as the last element of _keys_.""",
    """          1. For each own property key _P_ of _O_ such that Type(_P_) is String and _P_ is not an array index, in ascending chronological order of property creation, do""",
    """            1. Add _P_ as the last element of _keys_.""",
    """          1. For each own property key _P_ of _O_ such that Type(_P_) is Symbol, in ascending chronological order of property creation, do""",
    """            1. Add _P_ as the last element of _keys_.""",
    """          1. Return _keys_.""",
  )
}
