package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetThisValue` extends Algo {
  val head = NormalHead("GetThisValue", List(Param("V", Normal)))
  val ids = List(
    "sec-getthisvalue",
    "sec-reference-record-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyReference V)
  |  0:assert (= __x0__ true)
  |  1:app __x1__ = (IsSuperReference V)
  |  1:if (= __x1__ true) return V.ThisValue else return V.Base
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyReference(_V_) is *true*.""",
    """          1. If IsSuperReference(_V_) is *true*, return _V_.[[ThisValue]]; otherwise return _V_.[[Base]].""",
  )
}
