package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsExtensible` extends Algo {
  val head = NormalHead("IsExtensible", List(Param("O", Normal)))
  val ids = List(
    "sec-isextensible-o",
    "sec-testing-and-comparison-operations",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof O) Object)
  |  1:app __x0__ = (O.IsExtensible O)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_O_) is Object.""",
    """        1. Return ? _O_.[[IsExtensible]]().""",
  )
}
