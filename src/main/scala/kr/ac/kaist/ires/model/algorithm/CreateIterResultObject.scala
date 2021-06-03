package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateIterResultObject` extends Algo {
  val head = NormalHead("CreateIterResultObject", List(Param("value", Normal), Param("done", Normal)))
  val ids = List(
    "sec-createiterresultobject",
    "sec-operations-on-iterator-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof done) Boolean)
  |  1:app __x0__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |  1:let obj = [! __x0__]
  |  2:app __x1__ = (CreateDataPropertyOrThrow obj "value" value)
  |  2:[! __x1__]
  |  3:app __x2__ = (CreateDataPropertyOrThrow obj "done" done)
  |  3:[! __x2__]
  |  4:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_done_) is Boolean.""",
    """        1. Let _obj_ be ! OrdinaryObjectCreate(%Object.prototype%).""",
    """        1. Perform ! CreateDataPropertyOrThrow(_obj_, *"value"*, _value_).""",
    """        1. Perform ! CreateDataPropertyOrThrow(_obj_, *"done"*, _done_).""",
    """        1. Return _obj_.""",
  )
}
