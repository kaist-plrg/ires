package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Atomics.wait` extends Algo {
  val head = BuiltinHead(parseRef("""Atomics.wait"""), List(Param("typedArray", Normal), Param("index", Normal), Param("value", Normal), Param("timeout", Normal)))
  val ids = List(
    "sec-atomics.wait",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ValidateIntegerTypedArray typedArray true)
  |  0:let buffer = [? __x0__]
  |  1:app __x1__ = (IsSharedArrayBuffer buffer)
  |  1:if (= __x1__ false) throw TypeError else 22:{}
  |  2:app __x2__ = (ValidateAtomicAccess typedArray index)
  |  2:let indexedPosition = [? __x2__]
  |  3:let arrayTypeName = typedArray.TypedArrayName
  |  5:if (= arrayTypeName "BigInt64Array") {
  |    app __x3__ = (ToBigInt64 value)
  |    let v = [? __x3__]
  |  } else {
  |    app __x4__ = (ToInt32 value)
  |    let v = [? __x4__]
  |  }
  |  6:app __x5__ = (ToNumber timeout)
  |  6:let q = [? __x5__]
  |  7:if (|| (= q NaN) (= q Infinity)) let t = Infinity else if (= q -Infinity) let t = 0i else {
  |    app __x6__ = (max q 0i)
  |    let t = __x6__
  |  }
  |  8:app __x7__ = (AgentCanSuspend)
  |  8:let B = __x7__
  |  9:if (= B false) throw TypeError else 22:{}
  |  10:let block = buffer.ArrayBufferData
  |  11:app __x8__ = (GetWaiterList block indexedPosition)
  |  11:let WL = __x8__
  |  12:app __x9__ = (EnterCriticalSection WL)
  |  12:__x9__
  |  13:let elementType = CONST_Int8
  |  14:app __x10__ = (GetValueFromBuffer buffer indexedPosition elementType true CONST_SeqCst)
  |  14:let w = [! __x10__]
  |  15:if (! (== v w)) {
  |    16:app __x11__ = (LeaveCriticalSection WL)
  |    16:__x11__
  |    17:return "not-equal"
  |  } else 22:{}
  |  18:app __x12__ = (AgentSignifier)
  |  18:let W = __x12__
  |  19:app __x13__ = (AddWaiter WL W)
  |  19:__x13__
  |  20:app __x14__ = (SuspendAgent WL W t)
  |  20:let notified = __x14__
  |  23:if (= notified true) {} else {
  |    24:app __x15__ = (RemoveWaiter WL W)
  |    24:__x15__
  |  }
  |  25:app __x16__ = (LeaveCriticalSection WL)
  |  25:__x16__
  |  26:if (= notified true) return "ok" else 22:{}
  |  27:return "timed-out"
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _buffer_ be ? ValidateIntegerTypedArray(_typedArray_, *true*).""",
    """        1. If IsSharedArrayBuffer(_buffer_) is *false*, throw a *TypeError* exception.""",
    """        1. Let _indexedPosition_ be ? ValidateAtomicAccess(_typedArray_, _index_).""",
    """        1. Let _arrayTypeName_ be _typedArray_.[[TypedArrayName]].""",
    """        1. If _arrayTypeName_ is *"BigInt64Array"*, let _v_ be ? ToBigInt64(_value_).""",
    """        1. Otherwise, let _v_ be ? ToInt32(_value_).""",
    """        1. Let _q_ be ? ToNumber(_timeout_).""",
    """        1. If _q_ is *NaN* or *+∞*<sub>𝔽</sub>, let _t_ be +∞; else if _q_ is *-∞*<sub>𝔽</sub>, let _t_ be 0; else let _t_ be max(ℝ(_q_), 0).""",
    """        1. Let _B_ be AgentCanSuspend().""",
    """        1. If _B_ is *false*, throw a *TypeError* exception.""",
    """        1. Let _block_ be _buffer_.[[ArrayBufferData]].""",
    """        1. Let _WL_ be GetWaiterList(_block_, _indexedPosition_).""",
    """        1. Perform EnterCriticalSection(_WL_).""",
    """        1. Let _elementType_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _arrayTypeName_.""",
    """        1. Let _w_ be ! GetValueFromBuffer(_buffer_, _indexedPosition_, _elementType_, *true*, ~SeqCst~).""",
    """        1. If _v_ ≠ _w_, then""",
    """          1. Perform LeaveCriticalSection(_WL_).""",
    """          1. Return the String *"not-equal"*.""",
    """        1. Let _W_ be AgentSignifier().""",
    """        1. Perform AddWaiter(_WL_, _W_).""",
    """        1. Let _notified_ be SuspendAgent(_WL_, _W_, _t_).""",
    """        1. If _notified_ is *true*, then""",
    """          1. Assert: _W_ is not on the list of waiters in _WL_.""",
    """        1. Else,""",
    """          1. Perform RemoveWaiter(_WL_, _W_).""",
    """        1. Perform LeaveCriticalSection(_WL_).""",
    """        1. If _notified_ is *true*, return the String *"ok"*.""",
    """        1. Return the String *"timed-out"*.""",
  )
}
