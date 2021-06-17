package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.getSharedArrayBuffer.prototype.byteLength` extends Algo {
  val head = BuiltinHead(parseRef("""getSharedArrayBuffer.prototype.byteLength"""), List())
  val ids = List(
    "sec-get-sharedarraybuffer.prototype.bytelength",
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
  |  3:let length = O.ArrayBufferByteLength
  |  4:return length
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_O_, [[ArrayBufferData]]).""",
    """          1. If IsSharedArrayBuffer(_O_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _length_ be _O_.[[ArrayBufferByteLength]].""",
    """          1. Return 𝔽(_length_).""",
  )
}
