package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.ArrayBuffer.prototype.slice` extends Algo {
  val head = BuiltinHead(parseRef("""ArrayBuffer.prototype.slice"""), List(Param("start", Normal), Param("end", Normal)))
  val ids = List(
    "sec-arraybuffer.prototype.slice",
    "sec-properties-of-the-arraybuffer-prototype-object",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:let O = this
  |  1:app __x0__ = (RequireInternalSlot O "ArrayBufferData")
  |  1:[? __x0__]
  |  2:app __x1__ = (IsSharedArrayBuffer O)
  |  2:if (= __x1__ true) throw TypeError else 21:{}
  |  3:app __x2__ = (IsDetachedBuffer O)
  |  3:if (= __x2__ true) throw TypeError else 21:{}
  |  4:let len = O.ArrayBufferByteLength
  |  5:app __x3__ = (ToIntegerOrInfinity start)
  |  5:let relativeStart = [? __x3__]
  |  8:if (= relativeStart -Infinity) let first = 0i else if (< relativeStart 0i) {
  |    app __x4__ = (max (+ len relativeStart) 0i)
  |    let first = __x4__
  |  } else {
  |    app __x5__ = (min relativeStart len)
  |    let first = __x5__
  |  }
  |  9:if (= end undefined) let relativeEnd = len else {
  |    app __x6__ = (ToIntegerOrInfinity end)
  |    let relativeEnd = [? __x6__]
  |  }
  |  12:if (= relativeEnd -Infinity) let final = 0i else if (< relativeEnd 0i) {
  |    app __x7__ = (max (+ len relativeEnd) 0i)
  |    let final = __x7__
  |  } else {
  |    app __x8__ = (min relativeEnd len)
  |    let final = __x8__
  |  }
  |  13:app __x9__ = (max (- final first) 0i)
  |  13:let newLen = __x9__
  |  14:app __x10__ = (SpeciesConstructor O INTRINSIC_ArrayBuffer)
  |  14:let ctor = [? __x10__]
  |  15:app __x11__ = (Construct ctor (new [newLen]))
  |  15:let new = [? __x11__]
  |  16:app __x12__ = (RequireInternalSlot new "ArrayBufferData")
  |  16:[? __x12__]
  |  17:app __x13__ = (IsSharedArrayBuffer new)
  |  17:if (= __x13__ true) throw TypeError else 21:{}
  |  18:app __x14__ = (IsDetachedBuffer new)
  |  18:if (= __x14__ true) throw TypeError else 21:{}
  |  19:app __x15__ = (SameValue new O)
  |  19:if (= __x15__ true) throw TypeError else 21:{}
  |  20:if (< new.ArrayBufferByteLength newLen) throw TypeError else 21:{}
  |  22:app __x16__ = (IsDetachedBuffer O)
  |  22:if (= __x16__ true) throw TypeError else 21:{}
  |  23:let fromBuf = O.ArrayBufferData
  |  24:let toBuf = new.ArrayBufferData
  |  25:app __x17__ = (CopyDataBlockBytes toBuf 0i fromBuf first newLen)
  |  25:__x17__
  |  26:return new
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_O_, [[ArrayBufferData]]).""",
    """          1. If IsSharedArrayBuffer(_O_) is *true*, throw a *TypeError* exception.""",
    """          1. If IsDetachedBuffer(_O_) is *true*, throw a *TypeError* exception.""",
    """          1. Let _len_ be _O_.[[ArrayBufferByteLength]].""",
    """          1. Let _relativeStart_ be ? ToIntegerOrInfinity(_start_).""",
    """          1. If _relativeStart_ is -∞, let _first_ be 0.""",
    """          1. Else if _relativeStart_ < 0, let _first_ be max(_len_ + _relativeStart_, 0).""",
    """          1. Else, let _first_ be min(_relativeStart_, _len_).""",
    """          1. If _end_ is *undefined*, let _relativeEnd_ be _len_; else let _relativeEnd_ be ? ToIntegerOrInfinity(_end_).""",
    """          1. If _relativeEnd_ is -∞, let _final_ be 0.""",
    """          1. Else if _relativeEnd_ < 0, let _final_ be max(_len_ + _relativeEnd_, 0).""",
    """          1. Else, let _final_ be min(_relativeEnd_, _len_).""",
    """          1. Let _newLen_ be max(_final_ - _first_, 0).""",
    """          1. Let _ctor_ be ? SpeciesConstructor(_O_, %ArrayBuffer%).""",
    """          1. Let _new_ be ? Construct(_ctor_, « 𝔽(_newLen_) »).""",
    """          1. Perform ? RequireInternalSlot(_new_, [[ArrayBufferData]]).""",
    """          1. If IsSharedArrayBuffer(_new_) is *true*, throw a *TypeError* exception.""",
    """          1. If IsDetachedBuffer(_new_) is *true*, throw a *TypeError* exception.""",
    """          1. If SameValue(_new_, _O_) is *true*, throw a *TypeError* exception.""",
    """          1. If _new_.[[ArrayBufferByteLength]] < _newLen_, throw a *TypeError* exception.""",
    """          1. NOTE: Side-effects of the above steps may have detached _O_.""",
    """          1. If IsDetachedBuffer(_O_) is *true*, throw a *TypeError* exception.""",
    """          1. Let _fromBuf_ be _O_.[[ArrayBufferData]].""",
    """          1. Let _toBuf_ be _new_.[[ArrayBufferData]].""",
    """          1. Perform CopyDataBlockBytes(_toBuf_, 0, _fromBuf_, _first_, _newLen_).""",
    """          1. Return _new_.""",
  )
}
