package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryObject.Get` extends Algo {
  val head = MethodHead("OrdinaryObject", "Get", Param("O", Normal), List(Param("P", Normal), Param("Receiver", Normal)))
  val ids = List(
    "sec-ordinary-object-internal-methods-and-internal-slots-get-p-receiver",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinaryGet O P Receiver)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? OrdinaryGet(_O_, _P_, _Receiver_).""",
  )
}
