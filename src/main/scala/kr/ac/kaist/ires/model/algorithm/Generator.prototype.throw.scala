package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Generator.prototype.throw` extends Algo {
  val head = BuiltinHead(parseRef("""Generator.prototype.throw"""), List(Param("exception", Normal)))
  val ids = List(
    "sec-generator.prototype.throw",
    "sec-properties-of-generator-prototype",
    "sec-generator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let g = this
  |  1:app __x0__ = (ThrowCompletion exception)
  |  1:let C = __x0__
  |  2:app __x1__ = (GeneratorResumeAbrupt g C CONST_empty)
  |  2:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _g_ be the *this* value.""",
    """          1. Let _C_ be ThrowCompletion(_exception_).""",
    """          1. Return ? GeneratorResumeAbrupt(_g_, _C_, ~empty~).""",
  )
}
