package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GeneratorResumeAbrupt` extends Algo {
  val head = NormalHead("GeneratorResumeAbrupt", List(Param("generator", Normal), Param("abruptCompletion", Normal), Param("generatorBrand", Normal)))
  val ids = List(
    "sec-generatorresumeabrupt",
    "sec-generator-abstract-operations",
    "sec-generator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GeneratorValidate generator generatorBrand)
  |  0:let state = [? __x0__]
  |  1:if (= state CONST_suspendedStart) {
  |    2:generator.GeneratorState = CONST_completed
  |    4:state = CONST_completed
  |  } else 16:{}
  |  5:if (= state CONST_completed) {
  |    6:if (= abruptCompletion.Type CONST_return) {
  |      7:app __x1__ = (CreateIterResultObject abruptCompletion.Value true)
  |      7:return __x1__
  |    } else 16:{}
  |    8:return abruptCompletion
  |  } else 16:{}
  |  9:assert (= state CONST_suspendedYield)
  |  10:let genContext = generator.GeneratorContext
  |  11:let methodContext = CONTEXT
  |  12:CONTEXT = null
  |  13:generator.GeneratorState = CONST_executing
  |  14:append genContext -> EXECUTION_STACK
  |  14:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  15:??? "Resume the suspended evaluation of id:{genContext} using id:{abruptCompletion} as the result of the operation that suspended it . Let id:{result} be the completion record returned by the resumed computation ."
  |  17:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _state_ be ? GeneratorValidate(_generator_, _generatorBrand_).""",
    """          1. If _state_ is ~suspendedStart~, then""",
    """            1. Set _generator_.[[GeneratorState]] to ~completed~.""",
    """            1. Once a generator enters the ~completed~ state it never leaves it and its associated execution context is never resumed. Any execution state associated with _generator_ can be discarded at this point.""",
    """            1. Set _state_ to ~completed~.""",
    """          1. If _state_ is ~completed~, then""",
    """            1. If _abruptCompletion_.[[Type]] is ~return~, then""",
    """              1. Return CreateIterResultObject(_abruptCompletion_.[[Value]], *true*).""",
    """            1. Return Completion(_abruptCompletion_).""",
    """          1. Assert: _state_ is ~suspendedYield~.""",
    """          1. Let _genContext_ be _generator_.[[GeneratorContext]].""",
    """          1. Let _methodContext_ be the running execution context.""",
    """          1. Suspend _methodContext_.""",
    """          1. Set _generator_.[[GeneratorState]] to ~executing~.""",
    """          1. Push _genContext_ onto the execution context stack; _genContext_ is now the running execution context.""",
    """          1. Resume the suspended evaluation of _genContext_ using _abruptCompletion_ as the result of the operation that suspended it. Let _result_ be the completion record returned by the resumed computation.""",
    """          1. Assert: When we return here, _genContext_ has already been removed from the execution context stack and _methodContext_ is the currently running execution context.""",
    """          1. Return Completion(_result_).""",
  )
}
