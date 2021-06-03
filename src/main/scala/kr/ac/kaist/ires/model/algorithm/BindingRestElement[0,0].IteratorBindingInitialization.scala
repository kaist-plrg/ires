package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingRestElement[0,0].IteratorBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingRestElement", 0, 0, Rhs(List(Terminal("..."), NonTerminal("BindingIdentifier", List(""), false)), None), "IteratorBindingInitialization", List(Param("iteratorRecord", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratorbindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "StringValue")
  |  0:app __x1__ = (ResolveBinding __x0__ environment)
  |  0:let lhs = [? __x1__]
  |  1:app __x2__ = (ArrayCreate 0i)
  |  1:let A = [! __x2__]
  |  2:let n = 0i
  |  3:while true {
  |    4:if (= iteratorRecord.Done false) {
  |      5:app __x3__ = (IteratorStep iteratorRecord)
  |      5:let next = __x3__
  |      6:app __x4__ = (IsAbruptCompletion next)
  |      6:if __x4__ iteratorRecord.Done = true else 1:{}
  |      7:[? next]
  |      8:if (= next false) iteratorRecord.Done = true else 1:{}
  |    } else 1:{}
  |    9:if (= iteratorRecord.Done true) {
  |      10:if (= environment undefined) {
  |        app __x5__ = (PutValue lhs A)
  |        return [? __x5__]
  |      } else 1:{}
  |      11:app __x6__ = (InitializeReferencedBinding lhs A)
  |      11:return __x6__
  |    } else 1:{}
  |    12:app __x7__ = (IteratorValue next)
  |    12:let nextValue = __x7__
  |    13:app __x8__ = (IsAbruptCompletion nextValue)
  |    13:if __x8__ iteratorRecord.Done = true else 1:{}
  |    14:[? nextValue]
  |    15:app __x9__ = (ToString n)
  |    15:app __x10__ = (CreateDataPropertyOrThrow A [! __x9__] nextValue)
  |    15:[! __x10__]
  |    16:n = (+ n 1i)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _lhs_ be ? ResolveBinding(StringValue of |BindingIdentifier|, _environment_).""",
    """        1. Let _A_ be ! ArrayCreate(0).""",
    """        1. Let _n_ be 0.""",
    """        1. Repeat,""",
    """          1. If _iteratorRecord_.[[Done]] is *false*, then""",
    """            1. Let _next_ be IteratorStep(_iteratorRecord_).""",
    """            1. If _next_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """            1. ReturnIfAbrupt(_next_).""",
    """            1. If _next_ is *false*, set _iteratorRecord_.[[Done]] to *true*.""",
    """          1. If _iteratorRecord_.[[Done]] is *true*, then""",
    """            1. If _environment_ is *undefined*, return ? PutValue(_lhs_, _A_).""",
    """            1. Return InitializeReferencedBinding(_lhs_, _A_).""",
    """          1. Let _nextValue_ be IteratorValue(_next_).""",
    """          1. If _nextValue_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """          1. ReturnIfAbrupt(_nextValue_).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_n_)), _nextValue_).""",
    """          1. Set _n_ to _n_ + 1.""",
  )
}
