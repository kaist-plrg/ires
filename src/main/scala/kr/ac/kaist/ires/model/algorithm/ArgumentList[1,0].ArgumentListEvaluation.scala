package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArgumentList[1,0].ArgumentListEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ArgumentList", 1, 0, Rhs(List(Terminal("..."), NonTerminal("AssignmentExpression", List(""), false)), None), "ArgumentListEvaluation", List())
  val ids = List(
    "sec-runtime-semantics-argumentlistevaluation",
    "sec-argument-lists",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:let list = (new [])
  |  1:access __x0__ = (AssignmentExpression "Evaluation")
  |  1:let spreadRef = __x0__
  |  2:app __x1__ = (GetValue spreadRef)
  |  2:let spreadObj = [? __x1__]
  |  3:app __x2__ = (GetIterator spreadObj)
  |  3:let iteratorRecord = [? __x2__]
  |  4:while true {
  |    5:app __x3__ = (IteratorStep iteratorRecord)
  |    5:let next = [? __x3__]
  |    6:if (= next false) return list else 5:{}
  |    7:app __x4__ = (IteratorValue next)
  |    7:let nextArg = [? __x4__]
  |    8:append nextArg -> list
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _list_ be a new empty List.""",
    """          1. Let _spreadRef_ be the result of evaluating |AssignmentExpression|.""",
    """          1. Let _spreadObj_ be ? GetValue(_spreadRef_).""",
    """          1. Let _iteratorRecord_ be ? GetIterator(_spreadObj_).""",
    """          1. Repeat,""",
    """            1. Let _next_ be ? IteratorStep(_iteratorRecord_).""",
    """            1. If _next_ is *false*, return _list_.""",
    """            1. Let _nextArg_ be ? IteratorValue(_next_).""",
    """            1. Append _nextArg_ as the last element of _list_.""",
  )
}
