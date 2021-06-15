package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AllocateSharedArrayBuffer` extends Algo {
  val head = NormalHead("AllocateSharedArrayBuffer", List(Param("constructor", Normal), Param("byteLength", Normal)))
  val ids = List(
    "sec-allocatesharedarraybuffer",
    "sec-abstract-operations-for-sharedarraybuffer-objects",
    "sec-sharedarraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (OrdinaryCreateFromConstructor constructor "%SharedArrayBuffer.prototype%" (new ["ArrayBufferData", "ArrayBufferByteLength"]))
  |  0:let obj = [? __x0__]
  |  1:app __x1__ = (CreateSharedByteDataBlock byteLength)
  |  1:let block = [? __x1__]
  |  2:obj.ArrayBufferData = block
  |  3:obj.ArrayBufferByteLength = byteLength
  |  4:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _obj_ be ? OrdinaryCreateFromConstructor(_constructor_, *"%SharedArrayBuffer.prototype%"*, « [[ArrayBufferData]], [[ArrayBufferByteLength]] »).""",
    """          1. Let _block_ be ? CreateSharedByteDataBlock(_byteLength_).""",
    """          1. Set _obj_.[[ArrayBufferData]] to _block_.""",
    """          1. Set _obj_.[[ArrayBufferByteLength]] to _byteLength_.""",
    """          1. Return _obj_.""",
  )
}
