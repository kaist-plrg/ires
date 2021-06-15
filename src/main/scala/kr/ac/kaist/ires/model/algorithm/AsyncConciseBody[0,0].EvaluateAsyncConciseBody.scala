package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncConciseBody[0,0].EvaluateAsyncConciseBody` extends Algo {
  val head = SyntaxDirectedHead("AsyncConciseBody", 0, 0, Rhs(List(NonTerminal("ExpressionBody", List(""), false)), None), "EvaluateAsyncConciseBody", List(Param("functionObject", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-runtime-semantics-evaluateasyncconcisebody",
    "sec-async-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (NewPromiseCapability INTRINSIC_Promise)
  |  0:let promiseCapability = [! __x0__]
  |  1:app __x1__ = (FunctionDeclarationInstantiation functionObject argumentsList)
  |  1:let declResult = __x1__
  |  4:app __x2__ = (IsAbruptCompletion declResult)
  |  4:if (! __x2__) {
  |    3:app __x3__ = (AsyncFunctionStart promiseCapability ExpressionBody)
  |    3:[! __x3__]
  |  } else {
  |    5:app __x4__ = (Call promiseCapability.Reject undefined (new [declResult.Value]))
  |    5:[! __x4__]
  |  }
  |  6:return (new Completion("Type" -> CONST_return, "Value" -> promiseCapability.Promise, "Target" -> CONST_empty))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _promiseCapability_ be ! NewPromiseCapability(%Promise%).""",
    """        1. Let _declResult_ be FunctionDeclarationInstantiation(_functionObject_, _argumentsList_).""",
    """        1. If _declResult_ is not an abrupt completion, then""",
    """          1. Perform ! AsyncFunctionStart(_promiseCapability_, |ExpressionBody|).""",
    """        1. Else,""",
    """          1. Perform ! Call(_promiseCapability_.[[Reject]], *undefined*, « _declResult_.[[Value]] »).""",
    """        1. Return Completion { [[Type]]: ~return~, [[Value]]: _promiseCapability_.[[Promise]], [[Target]]: ~empty~ }.""",
  )
}
