package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArgumentList[3,0].ArgumentListEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ArgumentList", 3, 0, Rhs(List(NonTerminal("ArgumentList", List(""), false), Terminal(","), Terminal("..."), NonTerminal("AssignmentExpression", List(""), false)), None), "ArgumentListEvaluation", List())
  val ids = List(
    "sec-runtime-semantics-argumentlistevaluation",
    "sec-argument-lists",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ArgumentList "ArgumentListEvaluation")
  |  0:let precedingArgs = [? __x0__]
  |  1:access __x1__ = (AssignmentExpression "Evaluation")
  |  1:let spreadRef = __x1__
  |  2:app __x2__ = (GetValue spreadRef)
  |  2:app __x3__ = (GetIterator [? __x2__])
  |  2:let iteratorRecord = [? __x3__]
  |  3:while true {
  |    4:app __x4__ = (IteratorStep iteratorRecord)
  |    4:let next = [? __x4__]
  |    5:if (= next false) return precedingArgs else 5:{}
  |    6:app __x5__ = (IteratorValue next)
  |    6:let nextArg = [? __x5__]
  |    7:append nextArg -> precedingArgs
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _precedingArgs_ be ? ArgumentListEvaluation of |ArgumentList|.""",
    """          1. Let _spreadRef_ be the result of evaluating |AssignmentExpression|.""",
    """          1. Let _iteratorRecord_ be ? GetIterator(? GetValue(_spreadRef_)).""",
    """          1. Repeat,""",
    """            1. Let _next_ be ? IteratorStep(_iteratorRecord_).""",
    """            1. If _next_ is *false*, return _precedingArgs_.""",
    """            1. Let _nextArg_ be ? IteratorValue(_next_).""",
    """            1. Append _nextArg_ as the last element of _precedingArgs_.""",
  )
}
