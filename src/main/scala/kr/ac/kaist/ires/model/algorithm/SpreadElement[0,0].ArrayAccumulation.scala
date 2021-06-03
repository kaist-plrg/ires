package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SpreadElement[0,0].ArrayAccumulation` extends Algo {
  val head = SyntaxDirectedHead("SpreadElement", 0, 0, Rhs(List(Terminal("..."), NonTerminal("AssignmentExpression", List(""), false)), None), "ArrayAccumulation", List(Param("array", Normal), Param("nextIndex", Normal)))
  val ids = List(
    "sec-runtime-semantics-arrayaccumulation",
    "sec-array-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AssignmentExpression "Evaluation")
  |  0:let spreadRef = __x0__
  |  1:app __x1__ = (GetValue spreadRef)
  |  1:let spreadObj = [? __x1__]
  |  2:app __x2__ = (GetIterator spreadObj)
  |  2:let iteratorRecord = [? __x2__]
  |  3:while true {
  |    4:app __x3__ = (IteratorStep iteratorRecord)
  |    4:let next = [? __x3__]
  |    5:if (= next false) return nextIndex else 2:{}
  |    6:app __x4__ = (IteratorValue next)
  |    6:let nextValue = [? __x4__]
  |    7:app __x5__ = (ToString nextIndex)
  |    7:app __x6__ = (CreateDataPropertyOrThrow array [! __x5__] nextValue)
  |    7:[! __x6__]
  |    8:nextIndex = (+ nextIndex 1i)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _spreadRef_ be the result of evaluating |AssignmentExpression|.""",
    """          1. Let _spreadObj_ be ? GetValue(_spreadRef_).""",
    """          1. Let _iteratorRecord_ be ? GetIterator(_spreadObj_).""",
    """          1. Repeat,""",
    """            1. Let _next_ be ? IteratorStep(_iteratorRecord_).""",
    """            1. If _next_ is *false*, return _nextIndex_.""",
    """            1. Let _nextValue_ be ? IteratorValue(_next_).""",
    """            1. Perform ! CreateDataPropertyOrThrow(_array_, ! ToString(𝔽(_nextIndex_)), _nextValue_).""",
    """            1. Set _nextIndex_ to _nextIndex_ + 1.""",
  )
}
