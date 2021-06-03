package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object` extends Algo {
  val head = BuiltinHead(parseRef("""Object"""), List(Param("value", Optional)))
  val ids = List(
    "sec-object-value",
    "sec-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (! (|| (= NewTarget undefined) (= NewTarget CONTEXT.Function))) {
  |    1:app __x0__ = (OrdinaryCreateFromConstructor NewTarget "%Object.prototype%")
  |    1:return [? __x0__]
  |  } else 71:{}
  |  2:if (|| (= value undefined) (= value null)) {
  |    app __x1__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |    return [! __x1__]
  |  } else 71:{}
  |  3:app __x2__ = (ToObject value)
  |  3:return [! __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is neither *undefined* nor the active function, then""",
    """            1. Return ? OrdinaryCreateFromConstructor(NewTarget, *"%Object.prototype%"*).""",
    """          1. If _value_ is *undefined* or *null*, return ! OrdinaryObjectCreate(%Object.prototype%).""",
    """          1. Return ! ToObject(_value_).""",
  )
}
