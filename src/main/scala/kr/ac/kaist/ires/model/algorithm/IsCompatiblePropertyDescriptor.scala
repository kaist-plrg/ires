package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsCompatiblePropertyDescriptor` extends Algo {
  val head = NormalHead("IsCompatiblePropertyDescriptor", List(Param("Extensible", Normal), Param("Desc", Normal), Param("Current", Normal)))
  val ids = List(
    "sec-iscompatiblepropertydescriptor",
    "sec-ordinary-object-internal-methods-and-internal-slots-defineownproperty-p-desc",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ValidateAndApplyPropertyDescriptor undefined undefined Extensible Desc Current)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ValidateAndApplyPropertyDescriptor(*undefined*, *undefined*, _Extensible_, _Desc_, _Current_).""",
  )
}
