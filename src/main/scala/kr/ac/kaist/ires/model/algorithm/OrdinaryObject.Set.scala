package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryObject.Set` extends Algo {
  val head = MethodHead("OrdinaryObject", "Set", Param("O", Normal), List(Param("P", Normal), Param("V", Normal), Param("Receiver", Normal)))
  val ids = List(
    "sec-ordinary-object-internal-methods-and-internal-slots-set-p-v-receiver",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinarySet O P V Receiver)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? OrdinarySet(_O_, _P_, _V_, _Receiver_).""",
  )
}
