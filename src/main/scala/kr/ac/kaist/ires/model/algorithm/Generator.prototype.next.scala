package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Generator.prototype.next` extends Algo {
  val head = BuiltinHead(parseRef("""Generator.prototype.next"""), List(Param("value", Normal)))
  val ids = List(
    "sec-generator.prototype.next",
    "sec-properties-of-generator-prototype",
    "sec-generator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let g = this
  |  1:app __x0__ = (GeneratorResume g value CONST_empty)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _g_ be the *this* value.""",
    """          1. Return ? GeneratorResume(_g_, _value_, ~empty~).""",
  )
}
