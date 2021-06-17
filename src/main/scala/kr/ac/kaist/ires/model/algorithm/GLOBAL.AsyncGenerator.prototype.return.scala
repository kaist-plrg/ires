package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.AsyncGenerator.prototype.return` extends Algo {
  val head = BuiltinHead(parseRef("""AsyncGenerator.prototype.return"""), List(Param("value", Normal)))
  val ids = List(
    "sec-asyncgenerator-prototype-return",
    "sec-properties-of-asyncgenerator-prototype",
    "sec-asyncgenerator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let generator = this
  |  1:let completion = (new Completion("Type" -> CONST_return, "Value" -> value, "Target" -> CONST_empty))
  |  2:app __x0__ = (AsyncGeneratorEnqueue generator completion CONST_empty)
  |  2:return [! __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _generator_ be the *this* value.""",
    """          1. Let _completion_ be Completion { [[Type]]: ~return~, [[Value]]: _value_, [[Target]]: ~empty~ }.""",
    """          1. Return ! AsyncGeneratorEnqueue(_generator_, _completion_, ~empty~).""",
  )
}
