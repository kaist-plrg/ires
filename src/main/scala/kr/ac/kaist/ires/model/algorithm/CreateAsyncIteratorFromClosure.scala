package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateAsyncIteratorFromClosure` extends Algo {
  val head = NormalHead("CreateAsyncIteratorFromClosure", List(Param("closure", Normal), Param("generatorBrand", Normal), Param("generatorPrototype", Normal)))
  val ids = List(
    "sec-createasynciteratorfromclosure",
    "sec-asyncgenerator-abstract-operations",
    "sec-asyncgenerator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  1:let internalSlotsList = (new ["AsyncGeneratorState", "AsyncGeneratorContext", "AsyncGeneratorQueue", "GeneratorBrand"])
  |  2:app __x0__ = (OrdinaryObjectCreate generatorPrototype internalSlotsList)
  |  2:let generator = [! __x0__]
  |  3:generator.GeneratorBrand = generatorBrand
  |  4:generator.AsyncGeneratorState = undefined
  |  5:app __x1__ = (AsyncGeneratorStart generator closure)
  |  5:[! __x1__]
  |  6:return generator
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. NOTE: _closure_ can contain uses of the Await shorthand and uses of the Yield shorthand to yield an IteratorResult object.""",
    """          1. Let _internalSlotsList_ be « [[AsyncGeneratorState]], [[AsyncGeneratorContext]], [[AsyncGeneratorQueue]], [[GeneratorBrand]] ».""",
    """          1. Let _generator_ be ! OrdinaryObjectCreate(_generatorPrototype_, _internalSlotsList_).""",
    """          1. Set _generator_.[[GeneratorBrand]] to _generatorBrand_.""",
    """          1. Set _generator_.[[AsyncGeneratorState]] to *undefined*.""",
    """          1. Perform ! AsyncGeneratorStart(_generator_, _closure_).""",
    """          1. Return _generator_.""",
  )
}
