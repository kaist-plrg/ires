package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Set` extends Algo {
  val head = BuiltinHead(parseRef("""Set"""), List(Param("iterable", Optional)))
  val ids = List(
    "sec-set-iterable",
    "sec-set-constructor",
    "sec-set-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) throw TypeError else 16:{}
  |  1:app __x0__ = (OrdinaryCreateFromConstructor NewTarget "%Set.prototype%" (new ["SetData"]))
  |  1:let set = [? __x0__]
  |  2:set.SetData = (new [])
  |  3:if (|| (= iterable undefined) (= iterable null)) return set else 16:{}
  |  4:app __x1__ = (Get set "add")
  |  4:let adder = [? __x1__]
  |  5:app __x2__ = (IsCallable adder)
  |  5:if (= __x2__ false) throw TypeError else 16:{}
  |  6:app __x3__ = (GetIterator iterable)
  |  6:let iteratorRecord = [? __x3__]
  |  7:while true {
  |    8:app __x4__ = (IteratorStep iteratorRecord)
  |    8:let next = [? __x4__]
  |    9:if (= next false) return set else 16:{}
  |    10:app __x5__ = (IteratorValue next)
  |    10:let nextValue = [? __x5__]
  |    11:app __x6__ = (Call adder set (new [nextValue]))
  |    11:let status = __x6__
  |    12:app __x7__ = (IsAbruptCompletion status)
  |    12:if __x7__ {
  |      app __x8__ = (IteratorClose iteratorRecord status)
  |      return [? __x8__]
  |    } else 16:{}
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, throw a *TypeError* exception.""",
    """          1. Let _set_ be ? OrdinaryCreateFromConstructor(NewTarget, *"%Set.prototype%"*, « [[SetData]] »).""",
    """          1. Set _set_.[[SetData]] to a new empty List.""",
    """          1. If _iterable_ is either *undefined* or *null*, return _set_.""",
    """          1. Let _adder_ be ? Get(_set_, *"add"*).""",
    """          1. If IsCallable(_adder_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _iteratorRecord_ be ? GetIterator(_iterable_).""",
    """          1. Repeat,""",
    """            1. Let _next_ be ? IteratorStep(_iteratorRecord_).""",
    """            1. If _next_ is *false*, return _set_.""",
    """            1. Let _nextValue_ be ? IteratorValue(_next_).""",
    """            1. Let _status_ be Call(_adder_, _set_, « _nextValue_ »).""",
    """            1. If _status_ is an abrupt completion, return ? IteratorClose(_iteratorRecord_, _status_).""",
  )
}
