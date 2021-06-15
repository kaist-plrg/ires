package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Generator.prototype.return` extends Algo {
  val head = BuiltinHead(parseRef("""Generator.prototype.return"""), List(Param("value", Normal)))
  val ids = List(
    "sec-generator.prototype.return",
    "sec-properties-of-generator-prototype",
    "sec-generator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let g = this
  |  1:let C = (new Completion("Type" -> CONST_return, "Value" -> value, "Target" -> CONST_empty))
  |  2:app __x0__ = (GeneratorResumeAbrupt g C CONST_empty)
  |  2:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _g_ be the *this* value.""",
    """          1. Let _C_ be Completion { [[Type]]: ~return~, [[Value]]: _value_, [[Target]]: ~empty~ }.""",
    """          1. Return ? GeneratorResumeAbrupt(_g_, _C_, ~empty~).""",
  )
}
