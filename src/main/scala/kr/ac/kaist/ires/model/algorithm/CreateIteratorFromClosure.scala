package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateIteratorFromClosure` extends Algo {
  val head = NormalHead("CreateIteratorFromClosure", List(Param("closure", Normal), Param("generatorBrand", Normal), Param("generatorPrototype", Normal)))
  val ids = List(
    "sec-createiteratorfromclosure",
    "sec-generator-abstract-operations",
    "sec-generator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  1:let internalSlotsList = (new ["GeneratorState", "GeneratorContext", "GeneratorBrand"])
  |  2:app __x0__ = (OrdinaryObjectCreate generatorPrototype internalSlotsList)
  |  2:let generator = [! __x0__]
  |  3:generator.GeneratorBrand = generatorBrand
  |  4:generator.GeneratorState = undefined
  |  5:app __x1__ = (GeneratorStart generator closure)
  |  5:[! __x1__]
  |  6:return generator
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. NOTE: _closure_ can contain uses of the Yield shorthand to yield an IteratorResult object.""",
    """          1. Let _internalSlotsList_ be « [[GeneratorState]], [[GeneratorContext]], [[GeneratorBrand]] ».""",
    """          1. Let _generator_ be ! OrdinaryObjectCreate(_generatorPrototype_, _internalSlotsList_).""",
    """          1. Set _generator_.[[GeneratorBrand]] to _generatorBrand_.""",
    """          1. Set _generator_.[[GeneratorState]] to *undefined*.""",
    """          1. Perform ! GeneratorStart(_generator_, _closure_).""",
    """          1. Return _generator_.""",
  )
}
