package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Catch[0,0].CatchClauseEvaluation` extends Algo {
  val head = SyntaxDirectedHead("Catch", 0, 0, Rhs(List(Terminal("catch"), Terminal("("), NonTerminal("CatchParameter", List(""), false), Terminal(")"), NonTerminal("Block", List(""), false)), None), "CatchClauseEvaluation", List(Param("thrownValue", Normal)))
  val ids = List(
    "sec-runtime-semantics-catchclauseevaluation",
    "sec-try-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let oldEnv = CONTEXT.LexicalEnvironment
  |  1:app __x0__ = (NewDeclarativeEnvironment oldEnv)
  |  1:let catchEnv = __x0__
  |  2:access __x1__ = (CatchParameter "BoundNames")
  |  2:let __x2__ = __x1__
  |  2:let __x3__ = 0i
  |  2:while (< __x3__ __x2__.length) {
  |    let argName = __x2__[__x3__]
  |    3:app __x4__ = (catchEnv.CreateMutableBinding catchEnv argName false)
  |    3:[! __x4__]
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  4:CONTEXT.LexicalEnvironment = catchEnv
  |  5:access __x5__ = (CatchParameter "BindingInitialization" thrownValue catchEnv)
  |  5:let status = __x5__
  |  6:app __x6__ = (IsAbruptCompletion status)
  |  6:if __x6__ {
  |    7:CONTEXT.LexicalEnvironment = oldEnv
  |    8:return status
  |  } else 0:{}
  |  9:access __x7__ = (Block "Evaluation")
  |  9:let B = __x7__
  |  10:CONTEXT.LexicalEnvironment = oldEnv
  |  11:return B
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _oldEnv_ be the running execution context's LexicalEnvironment.""",
    """        1. Let _catchEnv_ be NewDeclarativeEnvironment(_oldEnv_).""",
    """        1. For each element _argName_ of the BoundNames of |CatchParameter|, do""",
    """          1. Perform ! _catchEnv_.CreateMutableBinding(_argName_, *false*).""",
    """        1. Set the running execution context's LexicalEnvironment to _catchEnv_.""",
    """        1. Let _status_ be BindingInitialization of |CatchParameter| with arguments _thrownValue_ and _catchEnv_.""",
    """        1. If _status_ is an abrupt completion, then""",
    """          1. Set the running execution context's LexicalEnvironment to _oldEnv_.""",
    """          1. Return Completion(_status_).""",
    """        1. Let _B_ be the result of evaluating |Block|.""",
    """        1. Set the running execution context's LexicalEnvironment to _oldEnv_.""",
    """        1. Return Completion(_B_).""",
  )
}
