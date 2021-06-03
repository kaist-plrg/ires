package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingElement[1,1].IteratorBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingElement", 1, 1, Rhs(List(NonTerminal("BindingPattern", List(""), false), NonTerminal("Initializer", List(""), true)), None), "IteratorBindingInitialization", List(Param("iteratorRecord", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratorbindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (= iteratorRecord.Done false) {
  |    1:app __x0__ = (IteratorStep iteratorRecord)
  |    1:let next = __x0__
  |    2:app __x1__ = (IsAbruptCompletion next)
  |    2:if __x1__ iteratorRecord.Done = true else 1:{}
  |    3:[? next]
  |    5:if (= next false) iteratorRecord.Done = true else {
  |      6:app __x2__ = (IteratorValue next)
  |      6:let v = __x2__
  |      7:app __x3__ = (IsAbruptCompletion v)
  |      7:if __x3__ iteratorRecord.Done = true else 1:{}
  |      8:[? v]
  |    }
  |  } else 1:{}
  |  9:if (= iteratorRecord.Done true) let v = undefined else 1:{}
  |  10:if (&& (! (= Initializer absent)) (= v undefined)) {
  |    11:access __x4__ = (Initializer "Evaluation")
  |    11:let defaultValue = __x4__
  |    12:app __x5__ = (GetValue defaultValue)
  |    12:v = [? __x5__]
  |  } else 1:{}
  |  13:access __x6__ = (BindingPattern "BindingInitialization" v environment)
  |  13:return __x6__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _iteratorRecord_.[[Done]] is *false*, then""",
    """          1. Let _next_ be IteratorStep(_iteratorRecord_).""",
    """          1. If _next_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """          1. ReturnIfAbrupt(_next_).""",
    """          1. If _next_ is *false*, set _iteratorRecord_.[[Done]] to *true*.""",
    """          1. Else,""",
    """            1. Let _v_ be IteratorValue(_next_).""",
    """            1. If _v_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """            1. ReturnIfAbrupt(_v_).""",
    """        1. If _iteratorRecord_.[[Done]] is *true*, let _v_ be *undefined*.""",
    """        1. If |Initializer| is present and _v_ is *undefined*, then""",
    """          1. Let _defaultValue_ be the result of evaluating |Initializer|.""",
    """          1. Set _v_ to ? GetValue(_defaultValue_).""",
    """        1. Return the result of performing BindingInitialization of |BindingPattern| with _v_ and _environment_ as the arguments.""",
  )
}
