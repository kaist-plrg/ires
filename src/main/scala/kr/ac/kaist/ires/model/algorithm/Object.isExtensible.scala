package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.isExtensible` extends Algo {
  val head = BuiltinHead(parseRef("""Object.isExtensible"""), List(Param("O", Normal)))
  val ids = List(
    "sec-object.isextensible",
    "sec-properties-of-the-object-constructor",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof O) Object)) return false else 2:{}
  |  1:app __x0__ = (IsExtensible O)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_O_) is not Object, return *false*.""",
    """          1. Return ? IsExtensible(_O_).""",
  )
}
