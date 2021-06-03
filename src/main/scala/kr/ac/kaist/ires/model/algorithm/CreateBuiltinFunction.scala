package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateBuiltinFunction` extends Algo {
  val head = NormalHead("CreateBuiltinFunction", List(Param("steps", Normal), Param("length", Normal), Param("name", Normal), Param("internalSlotsList", Normal), Param("realm", Optional), Param("prototype", Optional), Param("prefix", Optional)))
  val ids = List(
    "sec-createbuiltinfunction",
    "sec-built-in-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:if (|| (= realm absent) (= realm CONST_empty)) realm = REALM else 0:{}
  |  2:assert (is-instance-of realm RealmRecord)
  |  3:if (= prototype absent) prototype = realm.Intrinsics.INTRINSIC_Function_prototype else 0:{}
  |  4:??? "Let id:{func} be a new built - in function object that when called performs the action described by id:{steps} . The new function object has internal slots whose names are the elements of id:{internalSlotsList} , and an [ [ InitialName ] ] internal slot ."
  |  5:func.Realm = realm
  |  6:func.Prototype = prototype
  |  7:func.Extensible = true
  |  8:func.InitialName = null
  |  9:app __x0__ = (SetFunctionLength func length)
  |  9:[! __x0__]
  |  12:if (= prefix absent) {
  |    11:app __x1__ = (SetFunctionName func name)
  |    11:[! __x1__]
  |  } else {
  |    13:app __x2__ = (SetFunctionName func name prefix)
  |    13:[! __x2__]
  |  }
  |  14:return func
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _steps_ is either a set of algorithm steps or other definition of a function's behaviour provided in this specification.""",
    """        1. If _realm_ is not present or _realm_ is ~empty~, set _realm_ to the current Realm Record.""",
    """        1. Assert: _realm_ is a Realm Record.""",
    """        1. If _prototype_ is not present, set _prototype_ to _realm_.[[Intrinsics]].[[%Function.prototype%]].""",
    """        1. Let _func_ be a new built-in function object that when called performs the action described by _steps_. The new function object has internal slots whose names are the elements of _internalSlotsList_, and an [[InitialName]] internal slot.""",
    """        1. Set _func_.[[Realm]] to _realm_.""",
    """        1. Set _func_.[[Prototype]] to _prototype_.""",
    """        1. Set _func_.[[Extensible]] to *true*.""",
    """        1. Set _func_.[[InitialName]] to *null*.""",
    """        1. Perform ! SetFunctionLength(_func_, _length_).""",
    """        1. If _prefix_ is not present, then""",
    """          1. Perform ! SetFunctionName(_func_, _name_).""",
    """        1. Else,""",
    """          1. Perform ! SetFunctionName(_func_, _name_, _prefix_).""",
    """        1. Return _func_.""",
  )
}
