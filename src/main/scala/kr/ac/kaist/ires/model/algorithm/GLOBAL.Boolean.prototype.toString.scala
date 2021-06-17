package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Boolean.prototype.toString` extends Algo {
  val head = BuiltinHead(parseRef("""Boolean.prototype.toString"""), List())
  val ids = List(
    "sec-boolean.prototype.tostring",
    "sec-properties-of-the-boolean-prototype-object",
    "sec-boolean-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisBooleanValue this)
  |  0:let b = [? __x0__]
  |  1:if (= b true) return "true" else return "false"
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _b_ be ? thisBooleanValue(*this* value).""",
    """          1. If _b_ is *true*, return *"true"*; else return *"false"*.""",
  )
}
