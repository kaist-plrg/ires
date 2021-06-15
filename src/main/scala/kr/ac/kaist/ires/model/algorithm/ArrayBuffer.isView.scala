package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayBuffer.isView` extends Algo {
  val head = BuiltinHead(parseRef("""ArrayBuffer.isView"""), List(Param("arg", Normal)))
  val ids = List(
    "sec-arraybuffer.isview",
    "sec-properties-of-the-arraybuffer-constructor",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof arg) Object)) return false else 11:{}
  |  1:if (! (= arg.ViewedArrayBuffer absent)) return true else 11:{}
  |  2:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_arg_) is not Object, return *false*.""",
    """          1. If _arg_ has a [[ViewedArrayBuffer]] internal slot, return *true*.""",
    """          1. Return *false*.""",
  )
}
