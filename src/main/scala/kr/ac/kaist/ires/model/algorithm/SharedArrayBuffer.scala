package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SharedArrayBuffer` extends Algo {
  val head = BuiltinHead(parseRef("""SharedArrayBuffer"""), List(Param("length", Optional)))
  val ids = List(
    "sec-sharedarraybuffer-length",
    "sec-sharedarraybuffer-constructor",
    "sec-sharedarraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) throw TypeError else 0:{}
  |  1:app __x0__ = (ToIndex length)
  |  1:let byteLength = [? __x0__]
  |  2:app __x1__ = (AllocateSharedArrayBuffer NewTarget byteLength)
  |  2:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, throw a *TypeError* exception.""",
    """          1. Let _byteLength_ be ? ToIndex(_length_).""",
    """          1. Return ? AllocateSharedArrayBuffer(NewTarget, _byteLength_).""",
  )
}
