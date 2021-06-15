package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncArrowBindingIdentifier[0,0].IteratorBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("AsyncArrowBindingIdentifier", 0, 0, Rhs(List(NonTerminal("BindingIdentifier", List(""), false)), None), "IteratorBindingInitialization", List(Param("iteratorRecord", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratorbindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= iteratorRecord.Done false)
  |  1:app __x0__ = (IteratorStep iteratorRecord)
  |  1:let next = __x0__
  |  2:app __x1__ = (IsAbruptCompletion next)
  |  2:if __x1__ iteratorRecord.Done = true else 1:{}
  |  3:[? next]
  |  5:if (= next false) iteratorRecord.Done = true else {
  |    6:app __x2__ = (IteratorValue next)
  |    6:let v = __x2__
  |    7:app __x3__ = (IsAbruptCompletion v)
  |    7:if __x3__ iteratorRecord.Done = true else 1:{}
  |    8:[? v]
  |  }
  |  9:if (= iteratorRecord.Done true) let v = undefined else 1:{}
  |  10:access __x4__ = (BindingIdentifier "BindingInitialization" v environment)
  |  10:return __x4__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _iteratorRecord_.[[Done]] is *false*.""",
    """        1. Let _next_ be IteratorStep(_iteratorRecord_).""",
    """        1. If _next_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """        1. ReturnIfAbrupt(_next_).""",
    """        1. If _next_ is *false*, set _iteratorRecord_.[[Done]] to *true*.""",
    """        1. Else,""",
    """          1. Let _v_ be IteratorValue(_next_).""",
    """          1. If _v_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """          1. ReturnIfAbrupt(_v_).""",
    """        1. If _iteratorRecord_.[[Done]] is *true*, let _v_ be *undefined*.""",
    """        1. Return the result of performing BindingInitialization for |BindingIdentifier| using _v_ and _environment_ as the arguments.""",
  )
}
