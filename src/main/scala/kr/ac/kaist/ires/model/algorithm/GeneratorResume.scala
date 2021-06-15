package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GeneratorResume` extends Algo {
  val head = NormalHead("GeneratorResume", List(Param("generator", Normal), Param("value", Normal), Param("generatorBrand", Normal)))
  val ids = List(
    "sec-generatorresume",
    "sec-generator-abstract-operations",
    "sec-generator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GeneratorValidate generator generatorBrand)
  |  0:let state = [? __x0__]
  |  1:if (= state CONST_completed) {
  |    app __x1__ = (CreateIterResultObject undefined true)
  |    return __x1__
  |  } else 9:{}
  |  2:assert (|| (= state CONST_suspendedStart) (= state CONST_suspendedYield))
  |  3:let genContext = generator.GeneratorContext
  |  4:let methodContext = CONTEXT
  |  5:CONTEXT = null
  |  6:generator.GeneratorState = CONST_executing
  |  7:append genContext -> EXECUTION_STACK
  |  7:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  8:??? "Resume the suspended evaluation of id:{genContext} using NormalCompletion ( id:{value} ) as the result of the operation that suspended it . Let id:{result} be the value returned by the resumed computation ."
  |  10:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _state_ be ? GeneratorValidate(_generator_, _generatorBrand_).""",
    """          1. If _state_ is ~completed~, return CreateIterResultObject(*undefined*, *true*).""",
    """          1. Assert: _state_ is either ~suspendedStart~ or ~suspendedYield~.""",
    """          1. Let _genContext_ be _generator_.[[GeneratorContext]].""",
    """          1. Let _methodContext_ be the running execution context.""",
    """          1. Suspend _methodContext_.""",
    """          1. Set _generator_.[[GeneratorState]] to ~executing~.""",
    """          1. Push _genContext_ onto the execution context stack; _genContext_ is now the running execution context.""",
    """          1. Resume the suspended evaluation of _genContext_ using NormalCompletion(_value_) as the result of the operation that suspended it. Let _result_ be the value returned by the resumed computation.""",
    """          1. Assert: When we return here, _genContext_ has already been removed from the execution context stack and _methodContext_ is the currently running execution context.""",
    """          1. Return Completion(_result_).""",
  )
}
