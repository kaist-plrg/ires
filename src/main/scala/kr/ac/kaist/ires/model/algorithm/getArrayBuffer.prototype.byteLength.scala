package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::getArrayBuffer.prototype.byteLength` extends Algo {
  val head = BuiltinHead(parseRef("""getArrayBuffer.prototype.byteLength"""), List())
  val ids = List(
    "sec-get-arraybuffer.prototype.bytelength",
    "sec-properties-of-the-arraybuffer-prototype-object",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:let O = this
  |  1:app __x0__ = (RequireInternalSlot O "ArrayBufferData")
  |  1:[? __x0__]
  |  2:app __x1__ = (IsSharedArrayBuffer O)
  |  2:if (= __x1__ true) throw TypeError else 11:{}
  |  3:app __x2__ = (IsDetachedBuffer O)
  |  3:if (= __x2__ true) return 0i else 11:{}
  |  4:let length = O.ArrayBufferByteLength
  |  5:return length
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_O_, [[ArrayBufferData]]).""",
    """          1. If IsSharedArrayBuffer(_O_) is *true*, throw a *TypeError* exception.""",
    """          1. If IsDetachedBuffer(_O_) is *true*, return *+0*<sub>𝔽</sub>.""",
    """          1. Let _length_ be _O_.[[ArrayBufferByteLength]].""",
    """          1. Return 𝔽(_length_).""",
  )
}
