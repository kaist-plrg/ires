package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateDataProperty` extends Algo {
  val head = NormalHead("CreateDataProperty", List(Param("O", Normal), Param("P", Normal), Param("V", Normal)))
  val ids = List(
    "sec-createdataproperty",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:app __x0__ = (IsPropertyKey P)
  |  1:assert (= __x0__ true)
  |  2:let newDesc = (new PropertyDescriptor("Value" -> V, "Writable" -> true, "Enumerable" -> true, "Configurable" -> true))
  |  3:app __x1__ = (O.DefineOwnProperty O P newDesc)
  |  3:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_O_) is Object.""",
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. Let _newDesc_ be the PropertyDescriptor { [[Value]]: _V_, [[Writable]]: *true*, [[Enumerable]]: *true*, [[Configurable]]: *true* }.""",
    """        1. Return ? _O_.[[DefineOwnProperty]](_P_, _newDesc_).""",
  )
}
