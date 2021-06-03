package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayLiteral[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ArrayLiteral", 1, 0, Rhs(List(Terminal("["), NonTerminal("ElementList", List(""), false), Terminal("]")), None), "Evaluation", List())
  val ids = List(
    "sec-array-initializer-runtime-semantics-evaluation",
    "sec-array-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ArrayCreate 0i)
  |  0:let array = [! __x0__]
  |  1:access __x1__ = (ElementList "ArrayAccumulation" array 0i)
  |  1:let len = __x1__
  |  2:[? len]
  |  3:return array
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _array_ be ! ArrayCreate(0).""",
    """          1. Let _len_ be the result of performing ArrayAccumulation for |ElementList| with arguments _array_ and 0.""",
    """          1. ReturnIfAbrupt(_len_).""",
    """          1. Return _array_.""",
  )
}
