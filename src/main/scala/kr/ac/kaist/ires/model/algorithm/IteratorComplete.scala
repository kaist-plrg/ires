package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IteratorComplete` extends Algo {
  val head = NormalHead("IteratorComplete", List(Param("iterResult", Normal)))
  val ids = List(
    "sec-iteratorcomplete",
    "sec-operations-on-iterator-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof iterResult) Object)
  |  1:app __x0__ = (Get iterResult "done")
  |  1:app __x1__ = (ToBoolean [? __x0__])
  |  1:return [! __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_iterResult_) is Object.""",
    """        1. Return ! ToBoolean(? Get(_iterResult_, *"done"*)).""",
  )
}
