package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryObject.DefineOwnProperty` extends Algo {
  val head = MethodHead("OrdinaryObject", "DefineOwnProperty", Param("O", Normal), List(Param("P", Normal), Param("Desc", Normal)))
  val ids = List(
    "sec-ordinary-object-internal-methods-and-internal-slots-defineownproperty-p-desc",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinaryDefineOwnProperty O P Desc)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? OrdinaryDefineOwnProperty(_O_, _P_, _Desc_).""",
  )
}
