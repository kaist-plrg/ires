package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryObject.SetPrototypeOf` extends Algo {
  val head = MethodHead("OrdinaryObject", "SetPrototypeOf", Param("O", Normal), List(Param("V", Normal)))
  val ids = List(
    "sec-ordinary-object-internal-methods-and-internal-slots-setprototypeof-v",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinarySetPrototypeOf O V)
  |  0:return [! __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ! OrdinarySetPrototypeOf(_O_, _V_).""",
  )
}
