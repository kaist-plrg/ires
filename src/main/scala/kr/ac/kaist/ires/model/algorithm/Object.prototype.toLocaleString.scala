package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.prototype.toLocaleString` extends Algo {
  val head = BuiltinHead(parseRef("""Object.prototype.toLocaleString"""), List(Param("reserved1", Optional), Param("reserved2", Optional)))
  val ids = List(
    "sec-object.prototype.tolocalestring",
    "sec-properties-of-the-object-prototype-object",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:let O = this
  |  1:app __x0__ = (Invoke O "toString")
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be the *this* value.""",
    """          1. Return ? Invoke(_O_, *"toString"*).""",
  )
}
