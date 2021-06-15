package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncIteratorClose` extends Algo {
  val head = NormalHead("AsyncIteratorClose", List(Param("iteratorRecord", Normal), Param("completion", Normal)))
  val ids = List(
    "sec-asynciteratorclose",
    "sec-operations-on-iterator-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof iteratorRecord.Iterator) Object)
  |  1:assert (is-completion completion)
  |  2:let iterator = iteratorRecord.Iterator
  |  3:app __x0__ = (GetMethod iterator "return")
  |  3:let innerResult = __x0__
  |  4:if (= innerResult.Type CONST_normal) {
  |    5:let return = innerResult.Value
  |    6:if (= return undefined) return completion else 1:{}
  |    7:app __x1__ = (Call return iterator)
  |    7:innerResult = __x1__
  |    8:if (= innerResult.Type CONST_normal) {
  |      app __x2__ = (Await innerResult.Value)
  |      innerResult = __x2__
  |    } else 1:{}
  |  } else 1:{}
  |  9:if (= completion.Type CONST_throw) return completion else 1:{}
  |  10:if (= innerResult.Type CONST_throw) return innerResult else 1:{}
  |  11:if (! (= (typeof innerResult.Value) Object)) throw TypeError else 1:{}
  |  12:return completion
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Type(_iteratorRecord_.[[Iterator]]) is Object.""",
    """        1. Assert: _completion_ is a Completion Record.""",
    """        1. Let _iterator_ be _iteratorRecord_.[[Iterator]].""",
    """        1. Let _innerResult_ be GetMethod(_iterator_, *"return"*).""",
    """        1. If _innerResult_.[[Type]] is ~normal~, then""",
    """          1. Let _return_ be _innerResult_.[[Value]].""",
    """          1. If _return_ is *undefined*, return Completion(_completion_).""",
    """          1. Set _innerResult_ to Call(_return_, _iterator_).""",
    """          1. If _innerResult_.[[Type]] is ~normal~, set _innerResult_ to Await(_innerResult_.[[Value]]).""",
    """        1. If _completion_.[[Type]] is ~throw~, return Completion(_completion_).""",
    """        1. If _innerResult_.[[Type]] is ~throw~, return Completion(_innerResult_).""",
    """        1. If Type(_innerResult_.[[Value]]) is not Object, throw a *TypeError* exception.""",
    """        1. Return Completion(_completion_).""",
  )
}
