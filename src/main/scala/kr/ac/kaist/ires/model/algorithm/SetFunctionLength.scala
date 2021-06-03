package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SetFunctionLength` extends Algo {
  val head = NormalHead("SetFunctionLength", List(Param("F", Normal), Param("length", Normal)))
  val ids = List(
    "sec-setfunctionlength",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (DefinePropertyOrThrow F "length" (new PropertyDescriptor("Value" -> length, "Writable" -> false, "Enumerable" -> false, "Configurable" -> true)))
  |  1:return [! __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _F_ is an extensible object that does not have a *"length"* own property.""",
    """        1. Return ! DefinePropertyOrThrow(_F_, *"length"*, PropertyDescriptor { [[Value]]: 𝔽(_length_), [[Writable]]: *false*, [[Enumerable]]: *false*, [[Configurable]]: *true* }).""",
  )
}
