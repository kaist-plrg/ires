package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GeneratorYield` extends Algo {
  val head = NormalHead("GeneratorYield", List(Param("iterNextObj", Normal)))
  val ids = List(
    "sec-generatoryield",
    "sec-generator-abstract-operations",
    "sec-generator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  1:let genContext = CONTEXT
  |  3:let generator = genContext.Generator
  |  4:app __x0__ = (GetGeneratorKind)
  |  4:assert (= __x0__ CONST_sync)
  |  5:generator.GeneratorState = CONST_suspendedYield
  |  6:if (= EXECUTION_STACK[(- EXECUTION_STACK.length 1i)] genContext) {
  |    let __x1__ = (- EXECUTION_STACK.length 1i)
  |    (pop EXECUTION_STACK __x1__)
  |  } else {}
  |  6:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  7:??? "Set the code evaluation state of id:{genContext} such that when evaluation is resumed with a Completion id:{resumptionValue} the following steps will be performed : in:{} out:{}"
  |  10:return iterNextObj
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _iterNextObj_ is an Object that implements the <i>IteratorResult</i> interface.""",
    """          1. Let _genContext_ be the running execution context.""",
    """          1. Assert: _genContext_ is the execution context of a generator.""",
    """          1. Let _generator_ be the value of the Generator component of _genContext_.""",
    """          1. Assert: GetGeneratorKind() is ~sync~.""",
    """          1. Set _generator_.[[GeneratorState]] to ~suspendedYield~.""",
    """          1. Remove _genContext_ from the execution context stack and restore the execution context that is at the top of the execution context stack as the running execution context.""",
    """          1. Set the code evaluation state of _genContext_ such that when evaluation is resumed with a Completion _resumptionValue_ the following steps will be performed:""",
    """            1. Return _resumptionValue_.""",
    """            1. NOTE: This returns to the evaluation of the |YieldExpression| that originally called this abstract operation.""",
    """          1. Return NormalCompletion(_iterNextObj_).""",
    """          1. NOTE: This returns to the evaluation of the operation that had most previously resumed evaluation of _genContext_.""",
  )
}
