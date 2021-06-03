package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryObject.OwnPropertyKeys` extends Algo {
  val head = MethodHead("OrdinaryObject", "OwnPropertyKeys", Param("O", Normal), List())
  val ids = List(
    "sec-ordinary-object-internal-methods-and-internal-slots-ownpropertykeys",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinaryOwnPropertyKeys O)
  |  0:return [! __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ! OrdinaryOwnPropertyKeys(_O_).""",
  )
}
