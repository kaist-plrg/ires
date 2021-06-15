package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AddRestrictedFunctionProperties` extends Algo {
  val head = NormalHead("AddRestrictedFunctionProperties", List(Param("F", Normal), Param("realm", Normal)))
  val ids = List(
    "sec-addrestrictedfunctionproperties",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:let thrower = realm.Intrinsics.INTRINSIC_ThrowTypeError
  |  2:app __x0__ = (DefinePropertyOrThrow F "caller" (new PropertyDescriptor("Get" -> thrower, "Set" -> thrower, "Enumerable" -> false, "Configurable" -> true)))
  |  2:[! __x0__]
  |  3:app __x1__ = (DefinePropertyOrThrow F "arguments" (new PropertyDescriptor("Get" -> thrower, "Set" -> thrower, "Enumerable" -> false, "Configurable" -> true)))
  |  3:return [! __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _realm_.[[Intrinsics]].[[%ThrowTypeError%]] exists and has been initialized.""",
    """        1. Let _thrower_ be _realm_.[[Intrinsics]].[[%ThrowTypeError%]].""",
    """        1. Perform ! DefinePropertyOrThrow(_F_, *"caller"*, PropertyDescriptor { [[Get]]: _thrower_, [[Set]]: _thrower_, [[Enumerable]]: *false*, [[Configurable]]: *true* }).""",
    """        1. Return ! DefinePropertyOrThrow(_F_, *"arguments"*, PropertyDescriptor { [[Get]]: _thrower_, [[Set]]: _thrower_, [[Enumerable]]: *false*, [[Configurable]]: *true* }).""",
  )
}
