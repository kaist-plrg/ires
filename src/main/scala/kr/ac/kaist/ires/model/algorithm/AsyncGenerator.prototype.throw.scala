package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGenerator.prototype.throw` extends Algo {
  val head = BuiltinHead(parseRef("""AsyncGenerator.prototype.throw"""), List(Param("exception", Normal)))
  val ids = List(
    "sec-asyncgenerator-prototype-throw",
    "sec-properties-of-asyncgenerator-prototype",
    "sec-asyncgenerator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let generator = this
  |  1:app __x0__ = (ThrowCompletion exception)
  |  1:let completion = __x0__
  |  2:app __x1__ = (AsyncGeneratorEnqueue generator completion CONST_empty)
  |  2:return [! __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _generator_ be the *this* value.""",
    """          1. Let _completion_ be ThrowCompletion(_exception_).""",
    """          1. Return ! AsyncGeneratorEnqueue(_generator_, _completion_, ~empty~).""",
  )
}
