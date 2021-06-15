package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CloneArrayBuffer` extends Algo {
  val head = NormalHead("CloneArrayBuffer", List(Param("srcBuffer", Normal), Param("srcByteOffset", Normal), Param("srcLength", Normal), Param("cloneConstructor", Normal)))
  val ids = List(
    "sec-clonearraybuffer",
    "sec-abstract-operations-for-arraybuffer-objects",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (IsConstructor cloneConstructor)
  |  1:assert (= __x0__ true)
  |  2:app __x1__ = (AllocateArrayBuffer cloneConstructor srcLength)
  |  2:let targetBuffer = [? __x1__]
  |  3:app __x2__ = (IsDetachedBuffer srcBuffer)
  |  3:if (= __x2__ true) throw TypeError else 0:{}
  |  4:let srcBlock = srcBuffer.ArrayBufferData
  |  5:let targetBlock = targetBuffer.ArrayBufferData
  |  6:app __x3__ = (CopyDataBlockBytes targetBlock 0i srcBlock srcByteOffset srcLength)
  |  6:__x3__
  |  7:return targetBuffer
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Type(_srcBuffer_) is Object and it has an [[ArrayBufferData]] internal slot.""",
    """          1. Assert: IsConstructor(_cloneConstructor_) is *true*.""",
    """          1. Let _targetBuffer_ be ? AllocateArrayBuffer(_cloneConstructor_, _srcLength_).""",
    """          1. If IsDetachedBuffer(_srcBuffer_) is *true*, throw a *TypeError* exception.""",
    """          1. Let _srcBlock_ be _srcBuffer_.[[ArrayBufferData]].""",
    """          1. Let _targetBlock_ be _targetBuffer_.[[ArrayBufferData]].""",
    """          1. Perform CopyDataBlockBytes(_targetBlock_, 0, _srcBlock_, _srcByteOffset_, _srcLength_).""",
    """          1. Return _targetBuffer_.""",
  )
}
