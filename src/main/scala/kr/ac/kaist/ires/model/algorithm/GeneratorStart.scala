package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GeneratorStart` extends Algo {
  val head = NormalHead("GeneratorStart", List(Param("generator", Normal), Param("generatorBody", Normal)))
  val ids = List(
    "sec-generatorstart",
    "sec-generator-abstract-operations",
    "sec-generator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:assert (= generator.GeneratorState undefined)
  |  1:let genContext = CONTEXT
  |  2:genContext.Generator = generator
  |  3:??? "Set the code evaluation state of id:{genContext} such that when evaluation is resumed for that execution context the following steps will be performed : in:{} out:{}"
  |  19:generator.GeneratorContext = genContext
  |  20:generator.GeneratorState = CONST_suspendedStart
  |  21:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: The value of _generator_.[[GeneratorState]] is *undefined*.""",
    """          1. Let _genContext_ be the running execution context.""",
    """          1. Set the Generator component of _genContext_ to _generator_.""",
    """          1. Set the code evaluation state of _genContext_ such that when evaluation is resumed for that execution context the following steps will be performed:""",
    """            1. If _generatorBody_ is a Parse Node, then""",
    """              1. Let _result_ be the result of evaluating _generatorBody_.""",
    """            1. Else,""",
    """              1. Assert: _generatorBody_ is an Abstract Closure with no parameters.""",
    """              1. Let _result_ be _generatorBody_().""",
    """            1. Assert: If we return here, the generator either threw an exception or performed either an implicit or explicit return.""",
    """            1. Remove _genContext_ from the execution context stack and restore the execution context that is at the top of the execution context stack as the running execution context.""",
    """            1. Set _generator_.[[GeneratorState]] to ~completed~.""",
    """            1. Once a generator enters the ~completed~ state it never leaves it and its associated execution context is never resumed. Any execution state associated with _generator_ can be discarded at this point.""",
    """            1. If _result_.[[Type]] is ~normal~, let _resultValue_ be *undefined*.""",
    """            1. Else if _result_.[[Type]] is ~return~, let _resultValue_ be _result_.[[Value]].""",
    """            1. Else,""",
    """              1. Assert: _result_.[[Type]] is ~throw~.""",
    """              1. Return Completion(_result_).""",
    """            1. Return CreateIterResultObject(_resultValue_, *true*).""",
    """          1. Set _generator_.[[GeneratorContext]] to _genContext_.""",
    """          1. Set _generator_.[[GeneratorState]] to ~suspendedStart~.""",
    """          1. Return NormalCompletion(*undefined*).""",
  )
}
