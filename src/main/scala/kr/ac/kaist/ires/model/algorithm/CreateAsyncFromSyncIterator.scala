package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateAsyncFromSyncIterator` extends Algo {
  val head = NormalHead("CreateAsyncFromSyncIterator", List(Param("syncIteratorRecord", Normal)))
  val ids = List(
    "sec-createasyncfromsynciterator",
    "sec-async-from-sync-iterator-objects",
    "sec-iteration",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinaryObjectCreate INTRINSIC_AsyncFromSyncIteratorPrototype (new ["SyncIteratorRecord"]))
  |  0:let asyncIterator = [! __x0__]
  |  1:asyncIterator.SyncIteratorRecord = syncIteratorRecord
  |  2:app __x1__ = (Get asyncIterator "next")
  |  2:let nextMethod = [! __x1__]
  |  3:let iteratorRecord = (new Record("Iterator" -> asyncIterator, "NextMethod" -> nextMethod, "Done" -> false))
  |  4:return iteratorRecord
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _asyncIterator_ be ! OrdinaryObjectCreate(%AsyncFromSyncIteratorPrototype%, « [[SyncIteratorRecord]] »).""",
    """          1. Set _asyncIterator_.[[SyncIteratorRecord]] to _syncIteratorRecord_.""",
    """          1. Let _nextMethod_ be ! Get(_asyncIterator_, *"next"*).""",
    """          1. Let _iteratorRecord_ be the Record { [[Iterator]]: _asyncIterator_, [[NextMethod]]: _nextMethod_, [[Done]]: *false* }.""",
    """          1. Return _iteratorRecord_.""",
  )
}
