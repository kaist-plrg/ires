package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsSharedArrayBuffer` extends Algo {
  val head = NormalHead("IsSharedArrayBuffer", List(Param("obj", Normal)))
  val ids = List(
    "sec-issharedarraybuffer",
    "sec-abstract-operations-for-sharedarraybuffer-objects",
    "sec-sharedarraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  1:let bufferData = obj.ArrayBufferData
  |  2:if (= bufferData null) return false else 0:{}
  |  3:if (is-instance-of bufferData DataBlock) return false else 0:{}
  |  4:assert (is-instance-of bufferData SharedDataBlock)
  |  5:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Type(_obj_) is Object and it has an [[ArrayBufferData]] internal slot.""",
    """          1. Let _bufferData_ be _obj_.[[ArrayBufferData]].""",
    """          1. If _bufferData_ is *null*, return *false*.""",
    """          1. If _bufferData_ is a Data Block, return *false*.""",
    """          1. Assert: _bufferData_ is a Shared Data Block.""",
    """          1. Return *true*.""",
  )
}
