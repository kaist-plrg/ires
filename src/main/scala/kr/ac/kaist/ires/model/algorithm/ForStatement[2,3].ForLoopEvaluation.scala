package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForStatement[2,3].ForLoopEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ForStatement", 2, 3, Rhs(List(Terminal("for"), Terminal("("), NonTerminal("LexicalDeclaration", List(""), false), NonTerminal("Expression", List(""), true), Terminal(";"), NonTerminal("Expression", List(""), true), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "ForLoopEvaluation", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-runtime-semantics-forloopevaluation",
    "sec-for-statement",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let oldEnv = CONTEXT.LexicalEnvironment
  |  1:app __x0__ = (NewDeclarativeEnvironment oldEnv)
  |  1:let loopEnv = __x0__
  |  2:access __x1__ = (LexicalDeclaration "IsConstantDeclaration")
  |  2:let isConst = __x1__
  |  3:access __x2__ = (LexicalDeclaration "BoundNames")
  |  3:let boundNames = __x2__
  |  4:let __x3__ = boundNames
  |  4:let __x4__ = 0i
  |  4:while (< __x4__ __x3__.length) {
  |    let dn = __x3__[__x4__]
  |    7:if (= isConst true) {
  |      6:app __x5__ = (loopEnv.CreateImmutableBinding loopEnv dn true)
  |      6:[! __x5__]
  |    } else {
  |      8:app __x6__ = (loopEnv.CreateMutableBinding loopEnv dn false)
  |      8:[! __x6__]
  |    }
  |    __x4__ = (+ __x4__ 1i)
  |  }
  |  9:CONTEXT.LexicalEnvironment = loopEnv
  |  10:access __x7__ = (LexicalDeclaration "Evaluation")
  |  10:let forDcl = __x7__
  |  11:app __x8__ = (IsAbruptCompletion forDcl)
  |  11:if __x8__ {
  |    12:CONTEXT.LexicalEnvironment = oldEnv
  |    13:return forDcl
  |  } else 10:{}
  |  14:if (= isConst false) let perIterationLets = boundNames else let perIterationLets = (new [])
  |  15:app __x9__ = (ForBodyEvaluation Expression0 Expression1 Statement perIterationLets labelSet)
  |  15:let bodyResult = __x9__
  |  16:CONTEXT.LexicalEnvironment = oldEnv
  |  17:return bodyResult
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _oldEnv_ be the running execution context's LexicalEnvironment.""",
    """          1. Let _loopEnv_ be NewDeclarativeEnvironment(_oldEnv_).""",
    """          1. Let _isConst_ be IsConstantDeclaration of |LexicalDeclaration|.""",
    """          1. Let _boundNames_ be the BoundNames of |LexicalDeclaration|.""",
    """          1. For each element _dn_ of _boundNames_, do""",
    """            1. If _isConst_ is *true*, then""",
    """              1. Perform ! _loopEnv_.CreateImmutableBinding(_dn_, *true*).""",
    """            1. Else,""",
    """              1. Perform ! _loopEnv_.CreateMutableBinding(_dn_, *false*).""",
    """          1. Set the running execution context's LexicalEnvironment to _loopEnv_.""",
    """          1. Let _forDcl_ be the result of evaluating |LexicalDeclaration|.""",
    """          1. If _forDcl_ is an abrupt completion, then""",
    """            1. Set the running execution context's LexicalEnvironment to _oldEnv_.""",
    """            1. Return Completion(_forDcl_).""",
    """          1. If _isConst_ is *false*, let _perIterationLets_ be _boundNames_; otherwise let _perIterationLets_ be « ».""",
    """          1. Let _bodyResult_ be ForBodyEvaluation(the first |Expression|, the second |Expression|, |Statement|, _perIterationLets_, _labelSet_).""",
    """          1. Set the running execution context's LexicalEnvironment to _oldEnv_.""",
    """          1. Return Completion(_bodyResult_).""",
  )
}
