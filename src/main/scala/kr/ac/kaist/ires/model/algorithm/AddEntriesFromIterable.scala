package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AddEntriesFromIterable` extends Algo {
  val head = NormalHead("AddEntriesFromIterable", List(Param("target", Normal), Param("iterable", Normal), Param("adder", Normal)))
  val ids = List(
    "sec-add-entries-from-iterable",
    "sec-map-constructor",
    "sec-map-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsCallable adder)
  |  0:if (= __x0__ false) throw TypeError else 1:{}
  |  2:app __x1__ = (GetIterator iterable)
  |  2:let iteratorRecord = [? __x1__]
  |  3:while true {
  |    4:app __x2__ = (IteratorStep iteratorRecord)
  |    4:let next = [? __x2__]
  |    5:if (= next false) return target else 1:{}
  |    6:app __x3__ = (IteratorValue next)
  |    6:let nextItem = [? __x3__]
  |    7:if (! (= (typeof nextItem) Object)) {
  |      8:app __x4__ = (ThrowCompletion (new OrdinaryObject()))
  |      8:let error = __x4__
  |      9:app __x5__ = (IteratorClose iteratorRecord error)
  |      9:return [? __x5__]
  |    } else 1:{}
  |    10:app __x6__ = (Get nextItem "0")
  |    10:let k = __x6__
  |    11:app __x7__ = (IsAbruptCompletion k)
  |    11:if __x7__ {
  |      app __x8__ = (IteratorClose iteratorRecord k)
  |      return [? __x8__]
  |    } else 1:{}
  |    12:app __x9__ = (Get nextItem "1")
  |    12:let v = __x9__
  |    13:app __x10__ = (IsAbruptCompletion v)
  |    13:if __x10__ {
  |      app __x11__ = (IteratorClose iteratorRecord v)
  |      return [? __x11__]
  |    } else 1:{}
  |    14:app __x12__ = (Call adder target (new [k.Value, v.Value]))
  |    14:let status = __x12__
  |    15:app __x13__ = (IsAbruptCompletion status)
  |    15:if __x13__ {
  |      app __x14__ = (IteratorClose iteratorRecord status)
  |      return [? __x14__]
  |    } else 1:{}
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If IsCallable(_adder_) is *false*, throw a *TypeError* exception.""",
    """          1. Assert: _iterable_ is present, and is neither *undefined* nor *null*.""",
    """          1. Let _iteratorRecord_ be ? GetIterator(_iterable_).""",
    """          1. Repeat,""",
    """            1. Let _next_ be ? IteratorStep(_iteratorRecord_).""",
    """            1. If _next_ is *false*, return _target_.""",
    """            1. Let _nextItem_ be ? IteratorValue(_next_).""",
    """            1. If Type(_nextItem_) is not Object, then""",
    """              1. Let _error_ be ThrowCompletion(a newly created *TypeError* object).""",
    """              1. Return ? IteratorClose(_iteratorRecord_, _error_).""",
    """            1. Let _k_ be Get(_nextItem_, *"0"*).""",
    """            1. If _k_ is an abrupt completion, return ? IteratorClose(_iteratorRecord_, _k_).""",
    """            1. Let _v_ be Get(_nextItem_, *"1"*).""",
    """            1. If _v_ is an abrupt completion, return ? IteratorClose(_iteratorRecord_, _v_).""",
    """            1. Let _status_ be Call(_adder_, _target_, « _k_.[[Value]], _v_.[[Value]] »).""",
    """            1. If _status_ is an abrupt completion, return ? IteratorClose(_iteratorRecord_, _status_).""",
  )
}
