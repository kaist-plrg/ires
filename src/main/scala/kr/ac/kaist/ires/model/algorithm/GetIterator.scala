package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetIterator` extends Algo {
  val head = NormalHead("GetIterator", List(Param("obj", Normal), Param("hint", Optional), Param("method", Optional)))
  val ids = List(
    "sec-getiterator",
    "sec-operations-on-iterator-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (= hint absent) hint = CONST_sync else 1:{}
  |  1:assert (|| (= hint CONST_sync) (= hint CONST_async))
  |  2:if (= method absent) if (= hint CONST_async) {
  |    4:app __x0__ = (GetMethod obj SYMBOL_asyncIterator)
  |    4:method = [? __x0__]
  |    5:if (= method undefined) {
  |      6:app __x1__ = (GetMethod obj SYMBOL_iterator)
  |      6:let syncMethod = [? __x1__]
  |      7:app __x2__ = (GetIterator obj CONST_sync syncMethod)
  |      7:let syncIteratorRecord = [? __x2__]
  |      8:app __x3__ = (CreateAsyncFromSyncIterator syncIteratorRecord)
  |      8:return [! __x3__]
  |    } else 1:{}
  |  } else {
  |    app __x4__ = (GetMethod obj SYMBOL_iterator)
  |    method = [? __x4__]
  |  } else 1:{}
  |  10:app __x5__ = (Call method obj)
  |  10:let iterator = [? __x5__]
  |  11:if (! (= (typeof iterator) Object)) throw TypeError else 1:{}
  |  12:app __x6__ = (GetV iterator "next")
  |  12:let nextMethod = [? __x6__]
  |  13:let iteratorRecord = (new Record("Iterator" -> iterator, "NextMethod" -> nextMethod, "Done" -> false))
  |  14:return iteratorRecord
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _hint_ is not present, set _hint_ to ~sync~.""",
    """        1. Assert: _hint_ is either ~sync~ or ~async~.""",
    """        1. If _method_ is not present, then""",
    """          1. If _hint_ is ~async~, then""",
    """            1. Set _method_ to ? GetMethod(_obj_, @@asyncIterator).""",
    """            1. If _method_ is *undefined*, then""",
    """              1. Let _syncMethod_ be ? GetMethod(_obj_, @@iterator).""",
    """              1. Let _syncIteratorRecord_ be ? GetIterator(_obj_, ~sync~, _syncMethod_).""",
    """              1. Return ! CreateAsyncFromSyncIterator(_syncIteratorRecord_).""",
    """          1. Otherwise, set _method_ to ? GetMethod(_obj_, @@iterator).""",
    """        1. Let _iterator_ be ? Call(_method_, _obj_).""",
    """        1. If Type(_iterator_) is not Object, throw a *TypeError* exception.""",
    """        1. Let _nextMethod_ be ? GetV(_iterator_, *"next"*).""",
    """        1. Let _iteratorRecord_ be the Record { [[Iterator]]: _iterator_, [[NextMethod]]: _nextMethod_, [[Done]]: *false* }.""",
    """        1. Return _iteratorRecord_.""",
  )
}
