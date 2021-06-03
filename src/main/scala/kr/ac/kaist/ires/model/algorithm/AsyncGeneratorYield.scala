package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorYield` extends Algo {
  val head = NormalHead("AsyncGeneratorYield", List(Param("value", Normal)))
  val ids = List(
    "sec-asyncgeneratoryield",
    "sec-asyncgenerator-abstract-operations",
    "sec-asyncgenerator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let genContext = CONTEXT
  |  2:let generator = genContext.Generator
  |  3:app __x0__ = (GetGeneratorKind)
  |  3:assert (= __x0__ CONST_async)
  |  4:app __x1__ = (Await value)
  |  4:value = [? __x1__]
  |  5:generator.AsyncGeneratorState = CONST_suspendedYield
  |  6:if (= EXECUTION_STACK[(- EXECUTION_STACK.length 1i)] genContext) {
  |    let __x2__ = (- EXECUTION_STACK.length 1i)
  |    (pop EXECUTION_STACK __x2__)
  |  } else {}
  |  6:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  7:??? "Set the code evaluation state of id:{genContext} such that when evaluation is resumed with a Completion id:{resumptionValue} the following steps will be performed : in:{} out:{}"
  |  14:app __x3__ = (AsyncGeneratorResolve generator value false)
  |  14:return [! __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _genContext_ be the running execution context.""",
    """          1. Assert: _genContext_ is the execution context of a generator.""",
    """          1. Let _generator_ be the value of the Generator component of _genContext_.""",
    """          1. Assert: GetGeneratorKind() is ~async~.""",
    """          1. Set _value_ to ? Await(_value_).""",
    """          1. Set _generator_.[[AsyncGeneratorState]] to ~suspendedYield~.""",
    """          1. Remove _genContext_ from the execution context stack and restore the execution context that is at the top of the execution context stack as the running execution context.""",
    """          1. Set the code evaluation state of _genContext_ such that when evaluation is resumed with a Completion _resumptionValue_ the following steps will be performed:""",
    """            1. If _resumptionValue_.[[Type]] is not ~return~, return Completion(_resumptionValue_).""",
    """            1. Let _awaited_ be Await(_resumptionValue_.[[Value]]).""",
    """            1. If _awaited_.[[Type]] is ~throw~, return Completion(_awaited_).""",
    """            1. Assert: _awaited_.[[Type]] is ~normal~.""",
    """            1. Return Completion { [[Type]]: ~return~, [[Value]]: _awaited_.[[Value]], [[Target]]: ~empty~ }.""",
    """            1. NOTE: When one of the above steps returns, it returns to the evaluation of the |YieldExpression| production that originally called this abstract operation.""",
    """          1. Return ! AsyncGeneratorResolve(_generator_, _value_, *false*).""",
    """          1. NOTE: This returns to the evaluation of the operation that had most previously resumed evaluation of _genContext_.""",
  )
}
