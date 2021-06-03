package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorValidate` extends Algo {
  val head = NormalHead("AsyncGeneratorValidate", List(Param("generator", Normal), Param("generatorBrand", Normal)))
  val ids = List(
    "sec-asyncgeneratorvalidate",
    "sec-asyncgenerator-abstract-operations",
    "sec-asyncgenerator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireInternalSlot generator "AsyncGeneratorContext")
  |  0:[? __x0__]
  |  1:app __x1__ = (RequireInternalSlot generator "AsyncGeneratorState")
  |  1:[? __x1__]
  |  2:app __x2__ = (RequireInternalSlot generator "AsyncGeneratorQueue")
  |  2:[? __x2__]
  |  3:if (! (= generator.GeneratorBrand generatorBrand)) throw TypeError else 10:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? RequireInternalSlot(_generator_, [[AsyncGeneratorContext]]).""",
    """          1. Perform ? RequireInternalSlot(_generator_, [[AsyncGeneratorState]]).""",
    """          1. Perform ? RequireInternalSlot(_generator_, [[AsyncGeneratorQueue]]).""",
    """          1. If _generator_.[[GeneratorBrand]] is not the same value as _generatorBrand_, throw a *TypeError* exception.""",
  )
}
