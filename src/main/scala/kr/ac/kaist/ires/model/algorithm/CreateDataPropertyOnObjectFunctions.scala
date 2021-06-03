package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateDataPropertyOnObjectFunctions` extends Algo {
  val head = NormalHead("CreateDataPropertyOnObjectFunctions", List())
  val ids = List(
    "sec-create-data-property-on-object-functions",
    "sec-object.fromentries",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:let O = this
  |  1:assert (= (typeof O) Object)
  |  3:app __x0__ = (ToPropertyKey key)
  |  3:let propertyKey = [? __x0__]
  |  4:app __x1__ = (CreateDataPropertyOrThrow O propertyKey value)
  |  4:[! __x1__]
  |  5:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _O_ be the *this* value.""",
    """            1. Assert: Type(_O_) is Object.""",
    """            1. Assert: _O_ is an extensible ordinary object.""",
    """            1. Let _propertyKey_ be ? ToPropertyKey(_key_).""",
    """            1. Perform ! CreateDataPropertyOrThrow(_O_, _propertyKey_, _value_).""",
    """            1. Return *undefined*.""",
  )
}
