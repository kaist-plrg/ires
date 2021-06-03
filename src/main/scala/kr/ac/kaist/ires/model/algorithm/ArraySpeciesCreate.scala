package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArraySpeciesCreate` extends Algo {
  val head = NormalHead("ArraySpeciesCreate", List(Param("originalArray", Normal), Param("length", Normal)))
  val ids = List(
    "sec-arrayspeciescreate",
    "sec-array-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsArray originalArray)
  |  0:let isArray = [? __x0__]
  |  1:if (= isArray false) {
  |    app __x1__ = (ArrayCreate length)
  |    return [? __x1__]
  |  } else 8:{}
  |  2:app __x2__ = (Get originalArray "constructor")
  |  2:let C = [? __x2__]
  |  3:app __x3__ = (IsConstructor C)
  |  3:if (= __x3__ true) {
  |    4:let thisRealm = REALM
  |    5:app __x4__ = (GetFunctionRealm C)
  |    5:let realmC = [? __x4__]
  |    6:if (! (= thisRealm realmC)) {
  |      7:app __x5__ = (SameValue C realmC.Intrinsics.INTRINSIC_Array)
  |      7:if (= __x5__ true) C = undefined else 8:{}
  |    } else 8:{}
  |  } else 8:{}
  |  8:if (= (typeof C) Object) {
  |    9:app __x6__ = (Get C SYMBOL_species)
  |    9:C = [? __x6__]
  |    10:if (= C null) C = undefined else 8:{}
  |  } else 8:{}
  |  11:if (= C undefined) {
  |    app __x7__ = (ArrayCreate length)
  |    return [? __x7__]
  |  } else 8:{}
  |  12:app __x8__ = (IsConstructor C)
  |  12:if (= __x8__ false) throw TypeError else 8:{}
  |  13:app __x9__ = (Construct C (new [length]))
  |  13:return [? __x9__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _isArray_ be ? IsArray(_originalArray_).""",
    """          1. If _isArray_ is *false*, return ? ArrayCreate(_length_).""",
    """          1. Let _C_ be ? Get(_originalArray_, *"constructor"*).""",
    """          1. If IsConstructor(_C_) is *true*, then""",
    """            1. Let _thisRealm_ be the current Realm Record.""",
    """            1. Let _realmC_ be ? GetFunctionRealm(_C_).""",
    """            1. If _thisRealm_ and _realmC_ are not the same Realm Record, then""",
    """              1. If SameValue(_C_, _realmC_.[[Intrinsics]].[[%Array%]]) is *true*, set _C_ to *undefined*.""",
    """          1. If Type(_C_) is Object, then""",
    """            1. Set _C_ to ? Get(_C_, @@species).""",
    """            1. If _C_ is *null*, set _C_ to *undefined*.""",
    """          1. If _C_ is *undefined*, return ? ArrayCreate(_length_).""",
    """          1. If IsConstructor(_C_) is *false*, throw a *TypeError* exception.""",
    """          1. Return ? Construct(_C_, « 𝔽(_length_) »).""",
  )
}
