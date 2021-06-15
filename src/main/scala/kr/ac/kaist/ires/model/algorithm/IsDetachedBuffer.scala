package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsDetachedBuffer` extends Algo {
  val head = NormalHead("IsDetachedBuffer", List(Param("arrayBuffer", Normal)))
  val ids = List(
    "sec-isdetachedbuffer",
    "sec-abstract-operations-for-arraybuffer-objects",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  1:if (= arrayBuffer.ArrayBufferData null) return true else 0:{}
  |  2:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Type(_arrayBuffer_) is Object and it has an [[ArrayBufferData]] internal slot.""",
    """          1. If _arrayBuffer_.[[ArrayBufferData]] is *null*, return *true*.""",
    """          1. Return *false*.""",
  )
}
