package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SharedArrayBuffer.prototype.slice` extends Algo {
  val head = BuiltinHead(parseRef("""SharedArrayBuffer.prototype.slice"""), List(Param("start", Normal), Param("end", Normal)))
  val ids = List(
    "sec-sharedarraybuffer.prototype.slice",
    "sec-properties-of-the-sharedarraybuffer-prototype-object",
    "sec-sharedarraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:let O = this
  |  1:app __x0__ = (RequireInternalSlot O "ArrayBufferData")
  |  1:[? __x0__]
  |  2:app __x1__ = (IsSharedArrayBuffer O)
  |  2:if (= __x1__ false) throw TypeError else 0:{}
  |  3:let len = O.ArrayBufferByteLength
  |  4:app __x2__ = (ToIntegerOrInfinity start)
  |  4:let relativeStart = [? __x2__]
  |  7:if (= relativeStart -Infinity) let first = 0i else if (< relativeStart 0i) {
  |    app __x3__ = (max (+ len relativeStart) 0i)
  |    let first = __x3__
  |  } else {
  |    app __x4__ = (min relativeStart len)
  |    let first = __x4__
  |  }
  |  8:if (= end undefined) let relativeEnd = len else {
  |    app __x5__ = (ToIntegerOrInfinity end)
  |    let relativeEnd = [? __x5__]
  |  }
  |  11:if (= relativeEnd -Infinity) let final = 0i else if (< relativeEnd 0i) {
  |    app __x6__ = (max (+ len relativeEnd) 0i)
  |    let final = __x6__
  |  } else {
  |    app __x7__ = (min relativeEnd len)
  |    let final = __x7__
  |  }
  |  12:app __x8__ = (max (- final first) 0i)
  |  12:let newLen = __x8__
  |  13:app __x9__ = (SpeciesConstructor O INTRINSIC_SharedArrayBuffer)
  |  13:let ctor = [? __x9__]
  |  14:app __x10__ = (Construct ctor (new [newLen]))
  |  14:let new = [? __x10__]
  |  15:app __x11__ = (RequireInternalSlot new "ArrayBufferData")
  |  15:[? __x11__]
  |  16:app __x12__ = (IsSharedArrayBuffer new)
  |  16:if (= __x12__ false) throw TypeError else 0:{}
  |  17:if (= new.ArrayBufferData O.ArrayBufferData) throw TypeError else 0:{}
  |  18:if (< new.ArrayBufferByteLength newLen) throw TypeError else 0:{}
  |  19:let fromBuf = O.ArrayBufferData
  |  20:let toBuf = new.ArrayBufferData
  |  21:app __x13__ = (CopyDataBlockBytes toBuf 0i fromBuf first newLen)
  |  21:__x13__
  |  22:return new
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_O_, [[ArrayBufferData]]).""",
    """          1. If IsSharedArrayBuffer(_O_) is *false*, throw a *TypeError* exception.""",
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
    """          1. Let _ctor_ be ? SpeciesConstructor(_O_, %SharedArrayBuffer%).""",
    """          1. Let _new_ be ? Construct(_ctor_, « 𝔽(_newLen_) »).""",
    """          1. Perform ? RequireInternalSlot(_new_, [[ArrayBufferData]]).""",
    """          1. If IsSharedArrayBuffer(_new_) is *false*, throw a *TypeError* exception.""",
    """          1. If _new_.[[ArrayBufferData]] and _O_.[[ArrayBufferData]] are the same Shared Data Block values, throw a *TypeError* exception.""",
    """          1. If _new_.[[ArrayBufferByteLength]] < _newLen_, throw a *TypeError* exception.""",
    """          1. Let _fromBuf_ be _O_.[[ArrayBufferData]].""",
    """          1. Let _toBuf_ be _new_.[[ArrayBufferData]].""",
    """          1. Perform CopyDataBlockBytes(_toBuf_, 0, _fromBuf_, _first_, _newLen_).""",
    """          1. Return _new_.""",
  )
}
