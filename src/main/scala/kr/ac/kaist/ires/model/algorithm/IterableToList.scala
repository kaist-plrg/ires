package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IterableToList` extends Algo {
  val head = NormalHead("IterableToList", List(Param("items", Normal), Param("method", Optional)))
  val ids = List(
    "sec-iterabletolist",
    "sec-operations-on-iterator-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  2:if (! (= method absent)) {
  |    1:app __x0__ = (GetIterator items CONST_sync method)
  |    1:let iteratorRecord = [? __x0__]
  |  } else {
  |    3:app __x1__ = (GetIterator items CONST_sync)
  |    3:let iteratorRecord = [? __x1__]
  |  }
  |  4:let values = (new [])
  |  5:let next = true
  |  6:while (! (= next false)) {
  |    7:app __x2__ = (IteratorStep iteratorRecord)
  |    7:next = [? __x2__]
  |    8:if (! (= next false)) {
  |      9:app __x3__ = (IteratorValue next)
  |      9:let nextValue = [? __x3__]
  |      10:append nextValue -> values
  |    } else 1:{}
  |  }
  |  11:return values
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _method_ is present, then""",
    """          1. Let _iteratorRecord_ be ? GetIterator(_items_, ~sync~, _method_).""",
    """        1. Else,""",
    """          1. Let _iteratorRecord_ be ? GetIterator(_items_, ~sync~).""",
    """        1. Let _values_ be a new empty List.""",
    """        1. Let _next_ be *true*.""",
    """        1. Repeat, while _next_ is not *false*,""",
    """          1. Set _next_ to ? IteratorStep(_iteratorRecord_).""",
    """          1. If _next_ is not *false*, then""",
    """            1. Let _nextValue_ be ? IteratorValue(_next_).""",
    """            1. Append _nextValue_ to the end of the List _values_.""",
    """        1. Return _values_.""",
  )
}
