package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IteratorStep` extends Algo {
  val head = NormalHead("IteratorStep", List(Param("iteratorRecord", Normal)))
  val ids = List(
    "sec-iteratorstep",
    "sec-operations-on-iterator-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IteratorNext iteratorRecord)
  |  0:let result = [? __x0__]
  |  1:app __x1__ = (IteratorComplete result)
  |  1:let done = [? __x1__]
  |  2:if (= done true) return false else 1:{}
  |  3:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _result_ be ? IteratorNext(_iteratorRecord_).""",
    """        1. Let _done_ be ? IteratorComplete(_result_).""",
    """        1. If _done_ is *true*, return *false*.""",
    """        1. Return _result_.""",
  )
}
