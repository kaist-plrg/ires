package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DetachArrayBuffer` extends Algo {
  val head = NormalHead("DetachArrayBuffer", List(Param("arrayBuffer", Normal), Param("key", Optional)))
  val ids = List(
    "sec-detacharraybuffer",
    "sec-abstract-operations-for-arraybuffer-objects",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (IsSharedArrayBuffer arrayBuffer)
  |  1:assert (= __x0__ false)
  |  2:if (= key absent) key = undefined else 0:{}
  |  3:app __x1__ = (SameValue arrayBuffer.ArrayBufferDetachKey key)
  |  3:if (= __x1__ false) throw TypeError else 0:{}
  |  4:arrayBuffer.ArrayBufferData = null
  |  5:arrayBuffer.ArrayBufferByteLength = 0i
  |  6:return null
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Type(_arrayBuffer_) is Object and it has [[ArrayBufferData]], [[ArrayBufferByteLength]], and [[ArrayBufferDetachKey]] internal slots.""",
    """          1. Assert: IsSharedArrayBuffer(_arrayBuffer_) is *false*.""",
    """          1. If _key_ is not present, set _key_ to *undefined*.""",
    """          1. If SameValue(_arrayBuffer_.[[ArrayBufferDetachKey]], _key_) is *false*, throw a *TypeError* exception.""",
    """          1. Set _arrayBuffer_.[[ArrayBufferData]] to *null*.""",
    """          1. Set _arrayBuffer_.[[ArrayBufferByteLength]] to 0.""",
    """          1. Return NormalCompletion(*null*).""",
  )
}
