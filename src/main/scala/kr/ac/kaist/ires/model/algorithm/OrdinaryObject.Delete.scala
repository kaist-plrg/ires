package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryObject.Delete` extends Algo {
  val head = MethodHead("OrdinaryObject", "Delete", Param("O", Normal), List(Param("P", Normal)))
  val ids = List(
    "sec-ordinary-object-internal-methods-and-internal-slots-delete-p",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinaryDelete O P)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? OrdinaryDelete(_O_, _P_).""",
  )
}
