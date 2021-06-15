package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LengthOfArrayLike` extends Algo {
  val head = NormalHead("LengthOfArrayLike", List(Param("obj", Normal)))
  val ids = List(
    "sec-lengthofarraylike",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof obj) Object)
  |  1:app __x0__ = (Get obj "length")
  |  1:app __x1__ = (ToLength [? __x0__])
  |  1:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_obj_) is Object.""",
    """        1. Return ℝ(? ToLength(? Get(_obj_, *"length"*))).""",
  )
}
