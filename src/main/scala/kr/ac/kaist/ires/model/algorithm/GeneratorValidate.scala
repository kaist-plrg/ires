package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GeneratorValidate` extends Algo {
  val head = NormalHead("GeneratorValidate", List(Param("generator", Normal), Param("generatorBrand", Normal)))
  val ids = List(
    "sec-generatorvalidate",
    "sec-generator-abstract-operations",
    "sec-generator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireInternalSlot generator "GeneratorState")
  |  0:[? __x0__]
  |  1:app __x1__ = (RequireInternalSlot generator "GeneratorBrand")
  |  1:[? __x1__]
  |  2:if (! (= generator.GeneratorBrand generatorBrand)) throw TypeError else 3:{}
  |  4:let state = generator.GeneratorState
  |  5:if (= state CONST_executing) throw TypeError else 3:{}
  |  6:return state
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? RequireInternalSlot(_generator_, [[GeneratorState]]).""",
    """          1. Perform ? RequireInternalSlot(_generator_, [[GeneratorBrand]]).""",
    """          1. If _generator_.[[GeneratorBrand]] is not the same value as _generatorBrand_, throw a *TypeError* exception.""",
    """          1. Assert: _generator_ also has a [[GeneratorContext]] internal slot.""",
    """          1. Let _state_ be _generator_.[[GeneratorState]].""",
    """          1. If _state_ is ~executing~, throw a *TypeError* exception.""",
    """          1. Return _state_.""",
  )
}
