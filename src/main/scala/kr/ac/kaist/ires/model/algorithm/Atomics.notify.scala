package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Atomics.notify` extends Algo {
  val head = BuiltinHead(parseRef("""Atomics.notify"""), List(Param("typedArray", Normal), Param("index", Normal), Param("count", Normal)))
  val ids = List(
    "sec-atomics.notify",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ValidateIntegerTypedArray typedArray true)
  |  0:let buffer = [? __x0__]
  |  1:app __x1__ = (ValidateAtomicAccess typedArray index)
  |  1:let indexedPosition = [? __x1__]
  |  3:if (= count undefined) let c = Infinity else {
  |    4:app __x2__ = (ToIntegerOrInfinity count)
  |    4:let intCount = [? __x2__]
  |    5:app __x3__ = (max intCount 0i)
  |    5:let c = __x3__
  |  }
  |  6:let block = buffer.ArrayBufferData
  |  7:let arrayTypeName = typedArray.TypedArrayName
  |  8:app __x4__ = (IsSharedArrayBuffer buffer)
  |  8:if (= __x4__ false) return 0i else 22:{}
  |  9:app __x5__ = (GetWaiterList block indexedPosition)
  |  9:let WL = __x5__
  |  10:let n = 0i
  |  11:app __x6__ = (EnterCriticalSection WL)
  |  11:__x6__
  |  12:app __x7__ = (RemoveWaiters WL c)
  |  12:let S = __x7__
  |  13:while (< 0i S.length) {
  |    14:let W = S[0i]
  |    15:(pop S 0i)
  |    16:app __x8__ = (NotifyWaiter WL W)
  |    16:__x8__
  |    17:n = (+ n 1i)
  |  }
  |  18:app __x9__ = (LeaveCriticalSection WL)
  |  18:__x9__
  |  19:return n
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _buffer_ be ? ValidateIntegerTypedArray(_typedArray_, *true*).""",
    """        1. Let _indexedPosition_ be ? ValidateAtomicAccess(_typedArray_, _index_).""",
    """        1. If _count_ is *undefined*, let _c_ be +∞.""",
    """        1. Else,""",
    """          1. Let _intCount_ be ? ToIntegerOrInfinity(_count_).""",
    """          1. Let _c_ be max(_intCount_, 0).""",
    """        1. Let _block_ be _buffer_.[[ArrayBufferData]].""",
    """        1. Let _arrayTypeName_ be _typedArray_.[[TypedArrayName]].""",
    """        1. If IsSharedArrayBuffer(_buffer_) is *false*, return *+0*<sub>𝔽</sub>.""",
    """        1. Let _WL_ be GetWaiterList(_block_, _indexedPosition_).""",
    """        1. Let _n_ be 0.""",
    """        1. Perform EnterCriticalSection(_WL_).""",
    """        1. Let _S_ be RemoveWaiters(_WL_, _c_).""",
    """        1. Repeat, while _S_ is not an empty List,""",
    """          1. Let _W_ be the first agent in _S_.""",
    """          1. Remove _W_ from the front of _S_.""",
    """          1. Perform NotifyWaiter(_WL_, _W_).""",
    """          1. Set _n_ to _n_ + 1.""",
    """        1. Perform LeaveCriticalSection(_WL_).""",
    """        1. Return 𝔽(_n_).""",
  )
}
