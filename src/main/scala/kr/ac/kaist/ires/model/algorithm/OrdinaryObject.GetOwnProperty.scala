package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryObject.GetOwnProperty` extends Algo {
  val head = MethodHead("OrdinaryObject", "GetOwnProperty", Param("O", Normal), List(Param("P", Normal)))
  val ids = List(
    "sec-ordinary-object-internal-methods-and-internal-slots-getownproperty-p",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinaryGetOwnProperty O P)
  |  0:return [! __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ! OrdinaryGetOwnProperty(_O_, _P_).""",
  )
}
