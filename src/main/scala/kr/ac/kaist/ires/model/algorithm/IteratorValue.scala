package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IteratorValue` extends Algo {
  val head = NormalHead("IteratorValue", List(Param("iterResult", Normal)))
  val ids = List(
    "sec-iteratorvalue",
    "sec-operations-on-iterator-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof iterResult) Object)
  |  1:app __x0__ = (Get iterResult "value")
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_iterResult_) is Object.""",
    """        1. Return ? Get(_iterResult_, *"value"*).""",
  )
}
