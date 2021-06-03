package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryPreventExtensions` extends Algo {
  val head = NormalHead("OrdinaryPreventExtensions", List(Param("O", Normal)))
  val ids = List(
    "sec-ordinarypreventextensions",
    "sec-ordinary-object-internal-methods-and-internal-slots-preventextensions",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:O.Extensible = false
  |  1:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Set _O_.[[Extensible]] to *false*.""",
    """          1. Return *true*.""",
  )
}
