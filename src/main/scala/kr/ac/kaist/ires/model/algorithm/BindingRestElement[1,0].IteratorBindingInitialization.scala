package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingRestElement[1,0].IteratorBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingRestElement", 1, 0, Rhs(List(Terminal("..."), NonTerminal("BindingPattern", List(""), false)), None), "IteratorBindingInitialization", List(Param("iteratorRecord", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratorbindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ArrayCreate 0i)
  |  0:let A = [! __x0__]
  |  1:let n = 0i
  |  2:while true {
  |    3:if (= iteratorRecord.Done false) {
  |      4:app __x1__ = (IteratorStep iteratorRecord)
  |      4:let next = __x1__
  |      5:app __x2__ = (IsAbruptCompletion next)
  |      5:if __x2__ iteratorRecord.Done = true else 1:{}
  |      6:[? next]
  |      7:if (= next false) iteratorRecord.Done = true else 1:{}
  |    } else 1:{}
  |    8:if (= iteratorRecord.Done true) {
  |      9:access __x3__ = (BindingPattern "BindingInitialization" A environment)
  |      9:return __x3__
  |    } else 1:{}
  |    10:app __x4__ = (IteratorValue next)
  |    10:let nextValue = __x4__
  |    11:app __x5__ = (IsAbruptCompletion nextValue)
  |    11:if __x5__ iteratorRecord.Done = true else 1:{}
  |    12:[? nextValue]
  |    13:app __x6__ = (ToString n)
  |    13:app __x7__ = (CreateDataPropertyOrThrow A [! __x6__] nextValue)
  |    13:[! __x7__]
  |    14:n = (+ n 1i)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _A_ be ! ArrayCreate(0).""",
    """        1. Let _n_ be 0.""",
    """        1. Repeat,""",
    """          1. If _iteratorRecord_.[[Done]] is *false*, then""",
    """            1. Let _next_ be IteratorStep(_iteratorRecord_).""",
    """            1. If _next_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """            1. ReturnIfAbrupt(_next_).""",
    """            1. If _next_ is *false*, set _iteratorRecord_.[[Done]] to *true*.""",
    """          1. If _iteratorRecord_.[[Done]] is *true*, then""",
    """            1. Return the result of performing BindingInitialization of |BindingPattern| with _A_ and _environment_ as the arguments.""",
    """          1. Let _nextValue_ be IteratorValue(_next_).""",
    """          1. If _nextValue_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """          1. ReturnIfAbrupt(_nextValue_).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_n_)), _nextValue_).""",
    """          1. Set _n_ to _n_ + 1.""",
  )
}
