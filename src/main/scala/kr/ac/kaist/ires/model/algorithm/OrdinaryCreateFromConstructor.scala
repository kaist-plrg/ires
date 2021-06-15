package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryCreateFromConstructor` extends Algo {
  val head = NormalHead("OrdinaryCreateFromConstructor", List(Param("constructor", Normal), Param("intrinsicDefaultProto", Normal), Param("internalSlotsList", Optional)))
  val ids = List(
    "sec-ordinarycreatefromconstructor",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (GetPrototypeFromConstructor constructor intrinsicDefaultProto)
  |  1:let proto = [? __x0__]
  |  2:app __x1__ = (OrdinaryObjectCreate proto internalSlotsList)
  |  2:return [! __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _intrinsicDefaultProto_ is a String value that is this specification's name of an intrinsic object. The corresponding object must be an intrinsic that is intended to be used as the [[Prototype]] value of an object.""",
    """        1. Let _proto_ be ? GetPrototypeFromConstructor(_constructor_, _intrinsicDefaultProto_).""",
    """        1. Return ! OrdinaryObjectCreate(_proto_, _internalSlotsList_).""",
  )
}
